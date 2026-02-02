package com.ecommerce;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
  @GetMapping("")
  public String product(Model model) {
    model.addAttribute("adminSection", "order");
    return "admin";
  }

  @GetMapping("/checkout")
  public String checkout(Model model, HttpSession session) {
    User loggedUser = (User) session.getAttribute("loggedUser");
    if(loggedUser == null) {
      return "redirect:/signin";
    }
    model.addAttribute("loggedUser", loggedUser);

    ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
    if(cart.getItems().isEmpty()) {
      return "redirect:/";
    }
    model.addAttribute("shoppingCart", cart);

    return "checkout";
  }

  @PostMapping("/success")
  public String finish(Model model, HttpSession session) {
    User loggedUser = (User) session.getAttribute("loggedUser");
    if(loggedUser == null) {
      return "redirect:/signin";
    }
    model.addAttribute("loggedUser", loggedUser);

    ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
    if(cart.getItems().isEmpty()) {
      return "redirect:/";
    }
    model.addAttribute("shoppingCart", cart);
    return "order-success";
  }
}
