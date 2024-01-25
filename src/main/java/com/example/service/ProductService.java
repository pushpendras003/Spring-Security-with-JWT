package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.Product;
import com.example.model.User;
import com.example.repo.UserRepo;

import jakarta.annotation.PostConstruct;


@Service
public class ProductService {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	List<Product> productList=null;
	
	@PostConstruct
	public void loadDB() {
        productList = IntStream.rangeClosed(1, 100)
            .mapToObj(i -> {
                Product product = new Product();
                product.setProductId(i);
                product.setName("product " + i);
                product.setQty(new Random().nextInt(10));
                product.setPrice(new Random().nextInt(5000));
                return product;
            })
            .collect(Collectors.toList());
    }
	
	public List<Product> getProduct(){
		return productList;
	}
	
	public Product getProduct(int id) {
		return productList.stream().filter(product->product.getProductId()==id).findAny().orElseThrow(()->new RuntimeException("product "+id+" not found"));
	}
	
	public String addUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
		return "user saved";
	}
	
	public Iterable<User> getUser(){
		return repo.findAll();
	}
	
}
