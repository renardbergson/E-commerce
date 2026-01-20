package com.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
  @GetMapping("/product")
  public String product(Model model) {
    model.addAttribute("adminSection", "product");
    return "admin";
  }
}
