package com.store.controller;

import com.store.entity.Product;
import com.store.entity.User;
import com.store.service.ProductService;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("profile/cart-product")
    public ModelAndView cartProduct(Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.getUser(principal.getName());
        mv.addObject("user", user);
        int total = findSum(user);
        mv.addObject("total", total);
        return mv;
    }

    private int findSum(User user) {
        List<Product> productList = user.getProductList();
        int sum =0;
        for (Product p : productList) {
            sum += p.getPrice();
        }
        return sum;
    }

    @GetMapping("addToCart/{id}")
    public String addToCart(@PathVariable("id")Long productId, Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.getUser(principal.getName());
        Product product = productService.getById(productId).get();

        List<Product> productList = user.getProductList();
        productList.add(product);
        user.setProductList(productList);

        List<User> userList = product.getUserList();
        userList.add(user);
        product.setUserList(userList);

        userService.save(user);
        productService.save(product);

        int total = findSum(user);
        mv.addObject("total", total);

        mv.addObject("user", user);

        return "redirect:/{id}";
    }

    @GetMapping("removeToCart/{id}")
    public ModelAndView removeToCart(@PathVariable("id")Long productId, Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.getUser(principal.getName());
        Product product = productService.getById(productId).get();

        List<Product> productList = user.getProductList();
        for (Product prod : productList) {
            if (product.getId() == prod.getId()) {
                productList.remove(prod);
                break;
            }
        }
        user.setProductList(productList);

        List<User> userList = product.getUserList();
        for (User usr : userList) {
            if (usr.getId() == user.getId()) {
                userList.remove(usr);
                break;
            }
        }
        product.setUserList(userList);

        productService.save(product);
        userService.save(user);

        int total = findSum(user);
        mv.addObject("total", total);

        mv.addObject("user", user);

        return mv;
    }
}
