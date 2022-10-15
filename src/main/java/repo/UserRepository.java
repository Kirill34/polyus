package repo;

import model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User getUserByEmail(String email);
    public User getUserByPhoneNumber(String phoneNumber);
    public User getUserByEmailAndType(String email, User.UserType type);
    public User getUserByPhoneNumberAndType(String phoneNumber, User.UserType type);
    public long countByType(User.UserType type);

    public List<User> findAllByType(User.UserType type);


    @Query("SELECT u FROM User u WHERE u.type = 0 AND u NOT IN (SELECT u FROM User u INNER JOIN Request r on r.driver.id=u.id WHERE (r.status = 1 or r.status = 2) and (r.startDateTime between ?1 and ?2 or r.finishDateTime between ?1 and ?2 or ?1 between r.startDateTime and r.finishDateTime or ?2 between r.startDateTime and r.finishDateTime)) ")
    public List<User> findFreeDrivers(LocalDateTime startTime, LocalDateTime finishTime);

}
