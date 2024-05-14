package com.vascomm.demo.repository;

import com.vascomm.demo.model.Product;
import com.vascomm.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(String id);
}
