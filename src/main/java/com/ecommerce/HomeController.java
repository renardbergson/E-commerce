package com.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
  @GetMapping("/")
  public String home(Model model, @RequestParam(required = false) ProductCategory categoryFilter) {
    model.addAttribute("categories", ProductCategory.values());
    model.addAttribute("categoryFilter", categoryFilter != null ? categoryFilter : "ALL");
    return "index";
  }
}
