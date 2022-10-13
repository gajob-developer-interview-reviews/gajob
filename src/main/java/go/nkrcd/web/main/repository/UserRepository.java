package go.nkrcd.web.main.repository;

import go.nkrcd.web.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthId(String oauthId);

    Optional<User> findByOauthIdAndDelYn(String oauthId, String n);
}
