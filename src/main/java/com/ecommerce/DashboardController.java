package com.ecommerce;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
  @GetMapping("/dashboard")
  public String dashboard(Model model, HttpSession session) {
    if(session.getAttribute("loggedUser") == null) {
      return "redirect:/signin";
    }

    model.addAttribute("adminSection", "dashboard");
    return "admin";
  }
}
