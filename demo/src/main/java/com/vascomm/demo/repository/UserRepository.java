package com.vascomm.demo.repository;

import com.vascomm.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(String id);
}
