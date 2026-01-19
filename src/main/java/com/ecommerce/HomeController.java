package com.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("title", "A Sua Loja Online");
    model.addAttribute("description", "Descubra produtos incríveis com os melhores preços e entrega rápida. Tecnologia e qualidade em um só lugar.");
    
    return "index";
  }
}
