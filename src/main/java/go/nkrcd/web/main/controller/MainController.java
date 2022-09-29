package go.nkrcd.web.main.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("clientId", clientId);
        return "login";
    }
}
