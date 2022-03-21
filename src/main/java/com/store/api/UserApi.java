package com.store.api;

import com.store.entity.Product;
import com.store.entity.User;
import com.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping("/isAuthenticated")
    public void isAuthenticated() {
        if (!userService.isAuthenticated()) {
            throw new SessionAuthenticationException("User is not authenticated");
        }
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("cart")
    public List<Product> getUserCart() {
        return userService.getUserCurrentUser().getProductList();
    }

    @PutMapping("cart/{id}")
    public void addItemToCart(@PathVariable Long id) {
        userService.addProductToCart(id);
    }

    @DeleteMapping("cart/{id}")
    public void removeItemFromCart(@PathVariable Long id) {
        userService.removeProductFromCart(id);
    }

    @ExceptionHandler(SessionAuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException() {}

}
