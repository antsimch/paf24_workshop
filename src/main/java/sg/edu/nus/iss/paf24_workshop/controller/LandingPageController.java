package sg.edu.nus.iss.paf24_workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LandingPageController {
    
    @GetMapping(path = "/")
    public String getLandingPage(HttpSession session) {
        session.invalidate();
        return "redirect:/api/orders";
    }
}
