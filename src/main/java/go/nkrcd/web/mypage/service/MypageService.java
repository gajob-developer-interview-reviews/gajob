package go.nkrcd.web.mypage.service;

import go.nkrcd.web.main.model.User;
import go.nkrcd.web.mypage.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {
    @Autowired
    MypageRepository mypageRepository;

    public User findProfile(String oauthId) {
        return mypageRepository.findProfile(oauthId);
    }
}
