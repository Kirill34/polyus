package controller;

import model.User;
import model.VehicleExemplar;
import model.VehicleModel;
import model.VehicleType;
import org.hibernate.loader.plan.build.internal.LoadGraphLoadPlanBuildingStrategy;
import org.springframework.boot.Banner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repo.UserRepository;
import repo.VehicleExemplarRepository;
import repo.VehicleModelRepository;
import repo.VehicleTypeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleTypeRepository vehicleTypeRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleModelRepository vehicleModelRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleExemplarRepository vehicleExemplarRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private UserRepository userRepository;

    @PostMapping("/type/add")
    public Long addVehicleType(String type)
    {
        VehicleType vehicleType = new VehicleType(type);
        vehicleTypeRepository.save(vehicleType);
        return vehicleType.getId();
    }

    @PostMapping("/model/add")
    public Long addVehicleModel(String name, float power, float loadCapacity, float loadLength, float loadWidth, float maxSpeed, Long typeId, float fuelСonsumption100km, float fuelConsumptionHour)
    {
        VehicleType type = vehicleTypeRepository.findById(typeId).get();
        VehicleModel model = new VehicleModel(name,power,loadCapacity,loadLength,loadWidth,maxSpeed,type,fuelСonsumption100km,fuelConsumptionHour);
        vehicleModelRepository.save(model);
        return model.getId();
    }

    @GetMapping("/models/byType/{type_id}")
    public List<VehicleModel> getModelsByType(@PathVariable Long type_id)
    {
        return vehicleModelRepository.findAllByTypeId(type_id);
    }

    @GetMapping("/types")
    public List<VehicleType> getAllTypes()
    {
        List<VehicleType> vehicleTypeList = new ArrayList<>();
        Iterable<VehicleType> vehicleTypeIterable = vehicleTypeRepository.findAll();
        vehicleTypeIterable.forEach(new Consumer<VehicleType>() {
            @Override
            public void accept(VehicleType vehicleType) {
                vehicleTypeList.add(vehicleType);
            }
        });
        return vehicleTypeList;
    }

    @PostMapping("/exemplar/add")
    public Long addExemplar(Long modelId, String number, @DateTimeFormat(pattern = "dd.MM.yyyy") Date produceDate, @DateTimeFormat(pattern = "dd.MM.yyyy") Date lastCheckUpDate, @DateTimeFormat(pattern = "dd.MM.yyyy") Date nextCheckUpDate, Long managerId)
    {
        VehicleModel model = vehicleModelRepository.findById(modelId).get();
        User manager = userRepository.findById(managerId).get();
        VehicleExemplar exemplar= new VehicleExemplar(model, produceDate, lastCheckUpDate, nextCheckUpDate, number, 0, 0, manager);
        vehicleExemplarRepository.save(exemplar);
        return exemplar.getId();
    }

    @GetMapping("/exemplarsOfManager/{manager_id}")
    public List<VehicleExemplar> getExemplars(@PathVariable Long manager_id)
    {
        List<VehicleExemplar> exemplars = vehicleExemplarRepository.findAllByManagerId(manager_id);
        return exemplars;
    }

    @GetMapping("/exemplars/forCheckup/ofManager/{manager_id}")
    public List<VehicleExemplar> getExemplarsForCheckUp(@PathVariable Long manager_id)
    {
        List<VehicleExemplar> exemplars = vehicleExemplarRepository.findAllByNextDateCheckUpDateBeforeAndManagerId(new Date(), manager_id);
        return exemplars;
    }

    @PostMapping("/exemplar/{id}/setLocation")
    public void setVehicleExemplarLocation(@PathVariable Long id, double latitude, double longitude)
    {
        VehicleExemplar exemplar = vehicleExemplarRepository.findById(id).get();
        exemplar.setLatitude(latitude);
        exemplar.setLongitude(longitude);
    }

    @GetMapping("/exemplar/{id}")
    public VehicleExemplar getExemplar(@PathVariable Long id)
    {
        VehicleExemplar exemplar = vehicleExemplarRepository.findById(id).get();
        return exemplar;
    }


}
