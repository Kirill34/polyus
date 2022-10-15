package repo;

import model.User;
import model.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {
    public boolean existsByUserIdAndToken(Long id, String token);
    public UserToken getByUserIdAndToken(Long id, String token);
}
