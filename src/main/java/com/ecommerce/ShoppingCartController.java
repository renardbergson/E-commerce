package com.ecommerce;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/cart") // faz todas as rodas a seguir começarem com o prefixo "/cart"
public class ShoppingCartController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/add")
    public String add(
            @RequestParam Long productId,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ){
        if(session.getAttribute("loggedUser") == null) {
            return "redirect:/signin";
        }

        Optional<Product> search = productRepository.findById(productId);
        if(search.isEmpty()) {
            System.out.println("Product with id " + productId + " not found.");
            return "redirect:/";
        }
        Product product = search.get();

        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        cart.addProduct(product);
        cart.printItems();

        return "redirect:/";
    }

    @GetMapping("/view")
    public String view(HttpSession session, Model model) {
        if(session.getAttribute("loggedUser") == null) {
            return "redirect:/signin";
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        cart.toggleView();

        return "redirect:/";
    }
}
