package com.example.springboot.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.models.ProductModel;

@Repository //Beans gerenciavél 
public interface ProductRepository extends JpaRepository<ProductModel, UUID>{
  // passa a identidade que o JPA vai implementar
}
