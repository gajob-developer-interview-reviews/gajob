package go.nkrcd.web.mypage.repository;

import go.nkrcd.web.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MypageRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.oauthId = :oauthId and u.delYn = 'N'")
    User findProfile(@Param("oauthId")String oauthId);

}
