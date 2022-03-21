package com.store.service;

import com.store.entity.Product;
import com.store.entity.Role;
import com.store.entity.User;
import com.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));

        return save(user);
    }

    public boolean isAuthenticated() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal instanceof org.springframework.security.core.userdetails.User;
    }

    public User getUserCurrentUser() {
        if (!isAuthenticated()) {
            throw new SessionAuthenticationException("User is not authenticated");
        }
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void addProductToCart(Long productId) {
        User currentUser = getUserCurrentUser();
        Product product = productService.getById(productId);
        currentUser.getProductList().add(product);
    }

    public void removeProductFromCart(Long productId) {
        User currentUser = getUserCurrentUser();
        currentUser.getProductList().stream().filter(p -> p.getId().equals(productId)).findFirst().ifPresent(p -> {
            currentUser.getProductList().remove(p);
            save(currentUser);
        });
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
