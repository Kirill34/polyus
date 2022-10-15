package controller;

import model.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import repo.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private OrderRepository orderRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private RequestRepository requestRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    UserRepository userRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleTypeRepository vehicleTypeRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleModelRepository vehicleModelRepository;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private VehicleExemplarRepository vehicleExemplarRepository;

    @PostMapping("/add")
    public Long addOrder(Long customerId, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startOfWork, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime finishOfWork, Long requiredModelId)
    {
        VehicleModel model = vehicleModelRepository.findById(requiredModelId).get();
        User customer = userRepository.findById(customerId).get();
        VehicleOrder vehicleOrder = new VehicleOrder(customer, startOfWork,finishOfWork,model);
        orderRepository.save(vehicleOrder);

        List<User> drivers = userRepository.findFreeDrivers(startOfWork,finishOfWork);

        if (drivers.isEmpty())
            return new Long(0);

        User driver0 = drivers.get(0);

        List<VehicleExemplar> exemplars = vehicleExemplarRepository.findFreeVehicleExemplars(startOfWork, finishOfWork, model);
        if (exemplars.isEmpty())
            return new Long(-1);

        Request request = new Request(driver0, customer, exemplars.get(0), startOfWork, finishOfWork, vehicleOrder);
        requestRepository.save(request);


        return vehicleOrder.getId();
    }

    @GetMapping("/ofCustomer/{id}")
    public List<VehicleOrder> getCustomerOrders(@PathVariable Long id)
    {
        List<VehicleOrder> vehicleOrders = orderRepository.findAllByCustomerId(id);
        return vehicleOrders;
    }

}
