package repo;

import model.VehicleType;
import org.springframework.data.repository.CrudRepository;

public interface VehicleTypeRepository extends CrudRepository<VehicleType, Long> {
    public VehicleType findByName(String name);
}
