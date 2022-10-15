package repo;

import model.VehicleModel;
import model.VehicleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleModelRepository extends CrudRepository<VehicleModel, Long> {
    public List<VehicleModel> findAllByType(VehicleType type);
    public List<VehicleModel> findAllByTypeId(Long type_id);
    public List<VehicleModel> findAllByLoadCapacityAfter(float loadCapacity);
    public List<VehicleModel> findAllByLoadWidthAfter(float loadWidth);
    public List<VehicleModel> findAllByLoadLengthAfter(float loadLength);
    public List<VehicleModel> findAllByLoadLengthAfterAndLoadWidthAfterAndLoadCapacityAfter(float loadLength, float loadWidth, float loadCapacity);
}
