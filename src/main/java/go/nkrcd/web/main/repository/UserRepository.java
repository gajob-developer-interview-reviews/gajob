package go.nkrcd.web.main.repository;

import go.nkrcd.web.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthId(String oauthId);

    Optional<User> findByOauthIdAndDelYn(String oauthId, String n);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user u set u.del_yn = 'Y', u.del_dt = :delDt " +
            "where u.u_id = :uid " +
            "and u.del_yn = 'N'", nativeQuery = true)
    int joinOut(@Param("uid") Long uid, @Param("delDt") LocalDateTime delDt);

    @Query("select u from User u where u.oauthId = :oauthId and u.delYn = 'N'")
    User findProfile(@Param("oauthId")String oauthId);
}
