package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.ProductService;
import com.jtspringproject.JtSpringProject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @GetMapping("/buy")
    public String buy() {
        return "buy";
    }

    @GetMapping("/")
    public String userLogin() {
        return "userLogin";
    }

    @PostMapping("/userloginvalidate")
    public ModelAndView userLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        User user = this.userService.checkLogin(username, password);

        if (user != null && username.equals(user.getUsername())) {
            response.addCookie(new Cookie("username", user.getUsername()));
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("user", user);
            List<Product> products = this.productService.getProducts();

            if (products.isEmpty()) {
                modelAndView.addObject("msg", "No products are available");
            } else {
                modelAndView.addObject("products", products);
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("userLogin");
            modelAndView.addObject("msg", "Please enter correct username and password");
            return modelAndView;
        }
    }

    @GetMapping("/user/products")
    public ModelAndView getProducts() {
        ModelAndView modelAndView = new ModelAndView("uproduct");
        List<Product> products = this.productService.getProducts();

        if (products.isEmpty()) {
            modelAndView.addObject("msg", "No products are available");
        } else {
            modelAndView.addObject("products", products);
        }

        return modelAndView;
    }

    @PostMapping("/newuserregister")
    public ModelAndView newUserRegister(@ModelAttribute User user) {
        boolean exists = this.userService.checkUserExists(user.getUsername());

        if (!exists) {
            user.setRole("ROLE_NORMAL");
            this.userService.addUser(user);
            return new ModelAndView("userLogin");
        } else {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
            return modelAndView;
        }
    }

    @GetMapping("/profileDisplay")
    public String profileDisplay(Model model, HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            String username = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        username = cookie.getValue();
                        break;
                    }
                }
            }

            if (username != null) {
                User user = userService.getUserByUsername(username);

                if (user != null) {
                    model.addAttribute("userid", user.getId());
                    model.addAttribute("username", user.getUsername());
                    model.addAttribute("email", user.getEmail());
                    model.addAttribute("password", user.getPassword());
                    model.addAttribute("address", user.getAddress());
                } else {
                    model.addAttribute("msg", "User not found");
                }
            } else {
                model.addAttribute("msg", "Username not found in cookies");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "An error occurred while retrieving user details");
        }
        return "updateProfile";
    }

    // For learning purposes
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("author", "jay gajera");
        model.addAttribute("id", 40);

        List<String> friends = new ArrayList<>();
        model.addAttribute("f", friends);
        friends.add("xyz");
        friends.add("abc");

        return "test";
    }

    @GetMapping("/test2")
    public ModelAndView test2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "jay gajera 17");
        modelAndView.addObject("id", 40);
        modelAndView.setViewName("test2");

        List<Integer> marks = new ArrayList<>();
        marks.add(10);
        marks.add(25);
        modelAndView.addObject("marks", marks);

        return modelAndView;
    }

    // You can add cart-related methods here
}
