package com.ecommerce;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
  @Autowired
  OrderRepository orderRepository;

  // "/order"
  @GetMapping("")
  public String product(Model model, HttpSession session, @RequestParam(required = false) Order.OrderStatus statusFilter) {
    User loggedUser = (User) session.getAttribute("loggedUser");

    if(loggedUser == null) {
      return "redirect:/signin";
    }

    // Separa os pedidos pelo nível de acesso do usuário
    List<Order> orders;
    if(loggedUser.getRole() == User.Role.ADMIN) {
      orders = (List<Order>) orderRepository.findAll();
    } else {
      orders = orderRepository.findByUserId(loggedUser.getId());
    }

    // Aplica o filtro de status, se houver
    if(statusFilter != null) {
      orders = orders.stream()
              .filter(order -> order.getStatus() == statusFilter)
              .toList();
    }

    model.addAttribute("adminSection", "order");
    model.addAttribute("orders", orders);
    model.addAttribute("orderStatuses", Order.OrderStatus.values());
    model.addAttribute("statusFilter", statusFilter != null ? statusFilter : "ALL");

    return "admin";
  }

  @GetMapping("/checkout")
  public String checkout(HttpSession session, HttpServletResponse response) {
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    User loggedUser = (User) session.getAttribute("loggedUser");
    if(loggedUser == null) {
      return "redirect:/signin";
    }

    ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
    if(cart == null || cart.getItems().isEmpty()) {
      return "redirect:/";
    }

    return "checkout";
  }

  @PostMapping("/confirm")
  public String confirm(HttpSession session, RedirectAttributes redirectAttributes) {
    User loggedUser = (User) session.getAttribute("loggedUser");
    if(loggedUser == null) {
      return "redirect:/signin";
    }

    ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
    if(cart.getItems().isEmpty()) {
      return "redirect:/";
    }

    // Criando pedido
    // ShoppingCart -> Order
    // ShoppingCart.CartItem -> OrderItem
    Order order = new Order(loggedUser, cart.getTotal(), cart.getTotalQuantity(), Order.PaymentMethod.PIX);
    for(ShoppingCart.CartItem cartItem : cart.getItems()) {
      OrderItem orderItem = new OrderItem(
              cartItem.getProduct(),
              cartItem.getProduct().getPrice(),
              cartItem.getQuantity()
      );
      order.addItem(orderItem);
    }

    // Salvando pedido no banco de dados
    orderRepository.save(order);
    System.out.println("Pedido número " + order.getOrderNumber() + " criado com sucesso!");

    // Enviando pedido para a rota de sucesso
    redirectAttributes.addFlashAttribute("order", order);

    return "redirect:/order/success";
  }

  @GetMapping("/success")
  public String success(Model model, HttpSession session) {
    User loggedUser = (User) session.getAttribute("loggedUser");
    Order order = (Order) model.getAttribute("order");

    if(loggedUser == null) {
      return "redirect:/signin";
    }

    if(order == null) {
      return "redirect:/";
    }

    // Adicionando pedido ao contexto
    model.addAttribute("order", order);

    // Removendo carrinho da sessão
    session.removeAttribute("shoppingCart");

    return "order-success";
  }
}
