package com.ecommerce;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/user")
  public String dashboard(
          Model model,
          @RequestParam(required = false) User.Status statusFilter,
          HttpSession session
  ) {
    if(session.getAttribute("loggedUser") == null) {
      return "redirect:/signin";
    }

    if(!model.containsAttribute("adminSection")) {
      model.addAttribute("adminSection", "user");
    }

    if(!model.containsAttribute("user")) {
      model.addAttribute("user", new User());
    }

    Iterable<User> users;
    if(statusFilter != null) {
      users = userRepository.findByStatus(statusFilter);
    } else {
      users = userRepository.findAll();
    }

    model.addAttribute("users", users);
    model.addAttribute("userStatusList", User.Status.values());
    model.addAttribute("statusFilter", statusFilter != null ? statusFilter.name() : "ALL");

    return "admin";
  }

  @GetMapping("/user/edit/{id}")
  public String edit(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    System.out.println("Searching for user with id: " + id);
    Optional<User> user = userRepository.findById(id);
    if(user.isPresent()) {
      System.out.println("User found: " + user.get());
      redirectAttributes.addFlashAttribute("user", user.get());
    } else {
      System.out.println("User not found!");
    }
    return "redirect:/user";
  }

  @GetMapping("/user/delete/{id}")
  public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    Optional<User> user = userRepository.findById(id);
    // expressão lambda simples usada em metodo da classe Optional, substituindo IF tradicional
    user.ifPresent(u -> {
      redirectAttributes.addFlashAttribute("user", u);
      redirectAttributes.addFlashAttribute("adminSection", "deleteUser");
    });
    return "redirect:/user";
  }

  @DeleteMapping("/user/delete/confirm/{id}")
  public String deleteConfirm(@PathVariable Long id) {
    userRepository.deleteById(id);
    System.out.println("User deleted successfully with id: " + id);
    return "redirect:/user";
  }

  @PostMapping("/signup")
  public String signup(@ModelAttribute User user) {
    System.out.println("Attempting to create user: " + user);
    // set o atributo "status" e retorna a instância já atualizada
    userRepository.save(user.active());
    System.out.println("User created successfully: " + user);
    return "redirect:/signin";
  }

  @PostMapping("user/save")
  public String save(@ModelAttribute User user) {
    System.out.println("Attempting to save user: " + user);
    userRepository.save(user);
    return "redirect:/user";
  }
}
