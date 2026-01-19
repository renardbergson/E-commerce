package com.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {
  @GetMapping("/signin")
  public String signin() {
    return "signin";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @GetMapping("/logout")
  public String logout() {
    return "redirect:/";
  }
}
