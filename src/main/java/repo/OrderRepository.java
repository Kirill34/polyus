package repo;

import model.VehicleOrder;
import model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<VehicleOrder,Long> {
    public List<VehicleOrder> findAllByCustomerId(Long customer_id);
    public List<VehicleOrder> findAllByCustomer(User customer);
    public long countByCustomerAndStatus(User customer, VehicleOrder.Status status);
    public long countByCustomerIdAndStatus(Long customer_id, VehicleOrder.Status status);
}
