package repo;

import model.User;
import model.VehicleExemplar;
import model.VehicleModel;
import model.VehicleType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface VehicleExemplarRepository extends CrudRepository<VehicleExemplar, Long> {
    public VehicleExemplar getByNumber(String number);
    public List<VehicleExemplar> findAllByNextDateCheckUpDateBefore(Date nextDateCheckUpDate);
    public List<VehicleExemplar> findAllByNextDateCheckUpDateBeforeAndManager(Date nextDateCheckUpDate, User manager);
    public List<VehicleExemplar> findAllByNextDateCheckUpDateBeforeAndManagerId(Date nextDateCheckUpDate, Long manager_id);
    public List<VehicleExemplar> findAllByModel(VehicleModel model);
    public List<VehicleExemplar> findAllByModelId(Long model_id);
    public List<VehicleExemplar> findAllByModelType(VehicleType model_type);
    public List<VehicleExemplar> findAllByManagerId(Long manager_id);
    public List<VehicleExemplar> findAllByManager(User manager);
    public List<VehicleExemplar> findAllByManagerAndStatus(User manager, VehicleExemplar.Status status);
    public List<VehicleExemplar> findAllByManagerIdAndStatus(Long manager_id, VehicleExemplar.Status status);
    public long countByManagerAndStatus(User manager, VehicleExemplar.Status status);
    public long countByManagerIdAndStatus(Long manager_id, VehicleExemplar.Status status);
    public long countByManagerId(Long manager_id);
    public long countByModelId(Long model_id);
    public long countByModelTypeId(Long model_type_id);

    @Query("SELECT e FROM VehicleExemplar e WHERE e.model= ?3 AND e NOT IN (SELECT e FROM VehicleExemplar e INNER JOIN Request r on r.vehicleExemplar.id=e.id WHERE (r.status = 1 or r.status = 2) and (r.startDateTime between ?1 and ?2 or r.finishDateTime between ?1 and ?2)) ")
    public List<VehicleExemplar> findFreeVehicleExemplars(LocalDateTime startTime, LocalDateTime finishTime, VehicleModel model);


    @Query("SELECT e FROM VehicleExemplar e WHERE e.model.loadCapacity >= ?3 AND e NOT IN (SELECT e FROM VehicleExemplar e INNER JOIN Request r on r.vehicleExemplar.id=e.id WHERE (r.status = 1 or r.status = 2) and (r.startDateTime between ?1 and ?2 or r.finishDateTime between ?1 and ?2)) ORDER BY e.model.fuelConsumptionHour ASC")
    public List<VehicleExemplar> findFreeVehicleExemplarsByCapacity(LocalDateTime startTime, LocalDateTime finishTime, float minCapacity);


}
