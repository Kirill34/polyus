package repo;

import model.Request;
import model.User;
import model.VehicleExemplar;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RequestRepository extends CrudRepository<Request, Long> {
    //public Optional<Request> findById(Long id);
    //public Request getById(Long id);
    public List<Request> findAllByCustomer(User customer);
    public List<Request> findAllByCustomerId(Long customer_id);
    public List<Request> findAllByCustomerIdAndStatus(Long customer_id, Request.Status status);
    public List<Request> findAllByVehicleExemplarManagerIdAndStatus(Long vehicleExemplar_manager_id, Request.Status status);
    public List<Request> findAllByVehicleExemplarManagerId(Long vehicleExemplar_manager_id);
    public List<Request> findAllByDriver(User driver);
    public List<Request> findAllByDriverId(Long driver_id);
    public List<Request> findAllByDriverAndStatus(User driver, Request.Status status);
    public List<Request> findAllByDriverIdAndStatus(Long driver_id, Request.Status status);
    public List<Request> findAllByVehicleExemplar(VehicleExemplar vehicleExemplar);
    public List<Request> findAllByVehicleExemplarId(Long vehicleExemplar_id);
    public long countByDriver(User driver);
    public long countByDriverAndStatus(User driver, Request.Status status);
    public long countByDriverIdAndStatus(Long driver_id, Request.Status status);
    public List<Request> findAllByVehicleExemplarIdAndStartDateTimeBeforeAndFinishDateTimeAfter(Long vehicleExemplar_id, LocalDateTime startDateTime, LocalDateTime finishDateTime);
    public List<Request> findAllByStatus(Request.Status status);
}
