package com.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/user")
  public String user(Model model) {
    model.addAttribute("adminSection", "user");
    return "admin";
  }

  @PostMapping("/signup")
  public String signup(@ModelAttribute User user) {
    System.out.println("Attempting to create user: " + user);
    // set o atributo "status" e retorna a instância já atualizada
    userRepository.save(user.active());
    System.out.println("User created successfully: " + user);
    return "redirect:/signin";
  }
}
