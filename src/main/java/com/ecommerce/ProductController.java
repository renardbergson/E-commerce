package com.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
  @GetMapping("/product")
  public String product(Model model) {
    model.addAttribute("adminSection", "product");
    model.addAttribute("product", new Product());
    return "admin";
  }

  @PostMapping("/product/save")
  public String saveProduct(@ModelAttribute Product product) {
    System.out.println("Product saved: " + product);
    return "redirect:/product";
  }
}
