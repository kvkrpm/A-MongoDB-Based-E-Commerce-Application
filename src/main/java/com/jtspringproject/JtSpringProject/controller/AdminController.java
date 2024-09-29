package com.jtspringproject.JtSpringProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.CategoryService;
import com.jtspringproject.JtSpringProject.services.ProductService;
import com.jtspringproject.JtSpringProject.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    private boolean isLoggedIn = false;
    private String currentUsername = "";

    @RequestMapping(value = {"/", "/logout"})
    public String logout() {
        isLoggedIn = false;
        currentUsername = "";
        return "userLogin"; // Ensure this matches the Thymeleaf template name
    }

    @GetMapping("/index")
    public String index(Model model) {
        if (!isLoggedIn) {
            return "userLogin";
        }
        model.addAttribute("username", currentUsername);
        return "index"; // Ensure this matches the Thymeleaf template name
    }

    @GetMapping("/login")
    public String login() {
        return "adminLogin"; // Ensure this matches the Thymeleaf template name
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        if (isLoggedIn) {
            return "adminHome"; // Ensure this matches the Thymeleaf template name
        }
        return "redirect:/admin/login";
    }

    @PostMapping("/loginvalidate")
    public ModelAndView loginValidate(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.checkLogin(username, password);
        if (user == null || !"ROLE_ADMIN".equals(user.getRole())) {
            ModelAndView mv = new ModelAndView("adminLogin");
            mv.addObject("msg", "Invalid username or password");
            return mv;
        }

        isLoggedIn = true;
        currentUsername = username;
        ModelAndView mv = new ModelAndView("adminHome");
        mv.addObject("admin", user);
        return mv;
    }


    @GetMapping("/categories")
    public ModelAndView getCategories() {
        if (!isLoggedIn) {
            return new ModelAndView("adminLogin"); // Ensure this matches the Thymeleaf template name
        }
        List<Category> categories = categoryService.getCategories();
        ModelAndView mView = new ModelAndView("categories"); // Ensure this matches the Thymeleaf template name
        mView.addObject("categories", categories);
        return mView;
    }

    @PostMapping("/categories")
    public String addCategory(@RequestParam("categoryname") String categoryName) {
        categoryService.addCategory(categoryName);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete")
    public String deleteCategory(@RequestParam("id") String id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/update")
    public String updateCategory(@RequestParam("categoryid") String id, @RequestParam("categoryname") String categoryName) {
        categoryService.updateCategory(id, categoryName);
        return "redirect:/admin/categories";
    }

    @GetMapping("/products")
    public ModelAndView getProducts() {
        if (!isLoggedIn) {
            return new ModelAndView("adminLogin"); // Ensure this matches the Thymeleaf template name
        }
        List<Product> products = productService.getProducts();
        ModelAndView mView = new ModelAndView("products"); // Ensure this matches the Thymeleaf template name
        mView.addObject("products", products);
        return mView;
    }

    @GetMapping("/products/add")
    public ModelAndView addProductForm() {
        List<Category> categories = categoryService.getCategories();
        ModelAndView mView = new ModelAndView("productsAdd"); // Ensure this matches the Thymeleaf template name
        mView.addObject("categories", categories);
        return mView;
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam("name") String name, @RequestParam("categoryid") String categoryId, @RequestParam("price") double price, @RequestParam("weight") double weight, @RequestParam("quantity") int quantity, @RequestParam("description") String description, @RequestParam("productImage") String productImage) {
        Category category = categoryService.getCategory(categoryId);
        Product product = new Product(name, category, price, weight, quantity, description, productImage);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public ModelAndView updateProductForm(@PathVariable("id") String id) {
        Product product = productService.getProduct(id);
        List<Category> categories = categoryService.getCategories();
        ModelAndView mView = new ModelAndView("productsUpdate"); // Ensure this matches the Thymeleaf template name
        mView.addObject("categories", categories);
        mView.addObject("product", product);
        return mView;
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("categoryid") String categoryId, @RequestParam("price") double price, @RequestParam("weight") double weight, @RequestParam("quantity") int quantity, @RequestParam("description") String description, @RequestParam("productImage") String productImage) {
        Product product = productService.getProduct(id);
        if (product != null) {
            product.setName(name);
            product.setCategory(categoryService.getCategory(categoryId));
            product.setPrice(price);
            product.setWeight(weight);
            product.setQuantity(quantity);
            product.setDescription(description);
            product.setImage(productImage);
            productService.updateProduct(id, product);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete")
    public String deleteProduct(@RequestParam("id") String id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/customers")
    public ModelAndView getCustomerDetail() {
        if (!isLoggedIn) {
            return new ModelAndView("adminLogin"); // Ensure this matches the Thymeleaf template name
        }
        List<User> users = userService.getUsers();
        ModelAndView mView = new ModelAndView("displayCustomers"); // Ensure this matches the Thymeleaf template name
        mView.addObject("customers", users);
        return mView;
    }

    @GetMapping("/profileDisplay")
    public String profileDisplay(Model model) {
        User user = userService.getUserByUsername(currentUsername);
        if (user != null) {
            model.addAttribute("userid", user.getId());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("address", user.getAddress());
        }
        return "updateProfile"; // Ensure this matches the Thymeleaf template name
    }

    @PostMapping("/updateuser")
    public String updateUserProfile(@RequestParam("userid") String userId, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("address") String address) {
        User user = userService.getUserByUsername(currentUsername);
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setAddress(address);
            userService.updateUser(user);
            currentUsername = username;
        }
        return "redirect:/index";
    }
}
