// MainController.java
package com.nexastore.controller;

import com.nexastore.model.User;
import com.nexastore.repository.ProductRepository;
import com.nexastore.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public MainController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("products", productRepository.findAll());

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInName", loggedInUser.getUsername());
            model.addAttribute("loggedInId", loggedInUser.getId());
        }

        return "index";
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@RequestParam String username,
                                    @RequestParam String email,
                                    @RequestParam String password) {
        User user = new User(username, email, password);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String password,
                                   HttpSession session) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            return ResponseEntity.ok(Map.of(
                    "status", "ok",
                    "username", user.getUsername(),
                    "id", String.valueOf(user.getId())
            ));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("status", "fail"));
    }
}