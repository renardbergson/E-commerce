package com.ecommerce;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
  @Autowired
  private ProductRepository productRepository;

  @GetMapping("/")
  public String home(
          Model model,
          @RequestParam(required = false) ProductCategory categoryFilter,
          HttpSession session
  ){
    // O metodo findAll() na interface CrudRepository retorna um generics do tipo
    // Iterable<T>, por isso, mantivemos o mesmo tipo na variável "products"
    Iterable<Product> products;
    if(categoryFilter != null) {
      products = productRepository.findByCategory(categoryFilter);
    } else {
      products = productRepository.findAll();
    }
    User loggedUser = (User) session.getAttribute("loggedUser");
    if(loggedUser != null) {
      System.out.println("Usuário logado: " + loggedUser);
      model.addAttribute("loggedUser", loggedUser);
    }
    model.addAttribute("products", products);
    model.addAttribute("categories", ProductCategory.values());
    model.addAttribute("categoryFilter", categoryFilter != null ? categoryFilter : "ALL");
    return "index";
  }
}
