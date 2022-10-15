package repo;

import model.NoticeForManager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<NoticeForManager,Long> {
    public List<NoticeForManager> findAllByForManagerIdAndRead(Long forManager_id, boolean read);
}
