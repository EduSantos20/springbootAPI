package com.example.springboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Repository.ProductRepository;
import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.models.ProductModel;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController // sempre usar quando vai implementar uma API HestFul
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  // implementando o metudos cruds
  @PostMapping("/products") // salvando produtos no meu banco
  public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRecordDto, productModel); //
    return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
  }

  @GetMapping("/products") // listando todos os produtos findAll = pega todos os produtos que tem no banco
  // de dados
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
  }

  @GetMapping("/products/{id}") // listando todos os produtos findById = pega apenas um produto especifico
  // de dados
  public ResponseEntity<Object> getOneProducts(@PathVariable(value = "id") UUID id) {
    Optional<ProductModel> product0 = productRepository.findById(id);
    // se o produto nõa existir
    if (product0.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found"); // produto não existe
    }
    return ResponseEntity.status(HttpStatus.OK).body(product0.get()); // retornado o produto
  }

  @PutMapping("products/{id}") // Atualizando produto pelo o ID especifico
  public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
      @RequestBody @Valid ProductRecordDto productRecordDto) {
    Optional<ProductModel> product0 = productRepository.findById(id);
    // se o produto nõa existir
    if (product0.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found"); // produto não existe
    }
    var productModel = product0.get(); // pegando o produto

    BeanUtils.copyProperties(productRecordDto, productModel); //
    return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
  }

  @DeleteMapping("products/{id}") // deletando produto pelo o ID especifico
  public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id,
      @RequestBody @Valid ProductRecordDto productRecordDto) {
    Optional<ProductModel> product0 = productRepository.findById(id);
    // se o produto nõa existir
    if (product0.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found"); // produto não existe
    }
    productRepository.delete(product0.get()); //pegando o produto e deletando
    return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");// produto deletado com sucesso
  }

}
