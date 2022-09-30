package go.nkrcd.web.main.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "login";
    }
}
