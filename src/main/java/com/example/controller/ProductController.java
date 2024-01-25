package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.dto.Product;
import com.example.model.User;
import com.example.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	
    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
    	System.out.println("in w");
        return "Welcome this endpoint is not secure";
    }
    
    @GetMapping("/new")
    public String welcome1() {
    	System.out.println("in w");
        return "inside new";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User userInfo){
    	
    	System.out.println("in here");
        service.addUser(userInfo);
        return "added";
    }
    
    @GetMapping("/user")
    public Iterable<User> getAll(){
    	return service.getUser();
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProduct();
        
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }
}