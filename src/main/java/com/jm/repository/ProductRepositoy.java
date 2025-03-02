package com.jm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jm.entity.Product;

public interface ProductRepositoy extends JpaRepository<Product, Integer>{

}
