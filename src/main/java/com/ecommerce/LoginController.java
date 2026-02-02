package com.ecommerce;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/signin")
  public String signin() {
    return "signin";
  }

  @PostMapping("/signin")
  public String signin(
          @RequestParam  String email,
          @RequestParam String password,
          RedirectAttributes redirectAttributes,
          HttpSession session
  ){
    System.out.println("attempting to signin for: " + email + " - " + password);
    String errorMessage = "Usuário ou senha inválidos";
    String successMessage = "Usuário autenticado com sucesso!";

    // "Optional" porque a interface CrudRepository retorna um generics do tipo
    // Optional<T>para o metodo findById, então seguimos a mesma linha de pensamento
    Optional<User> search = userRepository.findByEmail(email);
    if(search.isEmpty()) {
      System.out.println(errorMessage);
      redirectAttributes.addFlashAttribute("error", errorMessage);
      return "redirect:/signin";
    }

    String userPass = search.get().getPassword();
    if(!userPass.equals(password)) {
      System.out.println(errorMessage);
      redirectAttributes.addFlashAttribute("error", errorMessage);
      return "redirect:/signin";
    }

    System.out.println(successMessage + " => " + search.get());

    redirectAttributes.addFlashAttribute("success", successMessage);
    session.setAttribute("loggedUser", search.get());
    session.setAttribute("shoppingCart", new ShoppingCart());

    return "redirect:/";
  }

  @GetMapping("/signup")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "signup";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    System.out.println("User logged out successfully!");
    return "redirect:/";
  }
}
