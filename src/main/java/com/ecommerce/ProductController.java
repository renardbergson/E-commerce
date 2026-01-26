package com.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProductController {
  @Autowired
  private ProductRepository productRepository;

  @GetMapping("/product")
  public String product(Model model, @RequestParam(required = false) ProductCategory categoryFilter) {
    if(!model.containsAttribute("adminSection")) {
      model.addAttribute("adminSection", "product");
    }
    if(!model.containsAttribute("product")) {
      model.addAttribute("product", new Product());
    }
    // O metodo findAll() na interface CrudRepository retorna um generics do tipo
    // Iterable<T>, por isso, mantivemos o mesmo tipo na variável "products"
    Iterable<Product> products;
    if(categoryFilter != null) {
      products = productRepository.findByCategory(categoryFilter);
    } else {
      products = productRepository.findAll();
    }
    model.addAttribute("categories", ProductCategory.values());
    model.addAttribute("products", products);
    model.addAttribute("categoryFilter", categoryFilter != null ? categoryFilter.name() : "ALL");
    return "admin";
  }

  @PostMapping("/product/save")
  public String saveProduct(@ModelAttribute Product product) {
    // já inclui a lógica para verificar quando se trata de um create ou update
    // para isso, depende de o id (definido como chave primária) vir no formulário
    productRepository.save(product);
    return "redirect:/product";
  }

  @GetMapping("/product/edit/{id}")
  public String editProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    Optional<Product> product = productRepository.findById(id);
    if(product.isPresent()) {
      // - addAttribute para quando retornamos uma view diretamente
      // - addFlashAttribute usado com redirect, evita passar dados pela URL
      redirectAttributes.addFlashAttribute("product", product.get());
    } else {
      System.out.println("Product not found with id: " + id);
    }
    return "redirect:/product";
  }

  @GetMapping("/product/delete/{id}")
  public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    Optional<Product> product = productRepository.findById(id);
    if(product.isPresent()) {
      redirectAttributes.addFlashAttribute("product", product.get());
      redirectAttributes.addFlashAttribute("adminSection", "deleteProduct");
    }
    return "redirect:/product";
  }

  @GetMapping("/product/delete/confirm/{id}")
  public String deleteProductConfirm(@PathVariable Long id) {
    productRepository.deleteById(id);
    return "redirect:/product";
  }
}
