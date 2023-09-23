package com.CusoUnivem.Curso.service;

import com.CusoUnivem.Curso.repository.ProductRepository;
import jakarta.transaction.Transactional;
import com.CusoUnivem.Curso.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<ProductModel> findById(UUID id){
        return productRepository.findById (id);
    }

    public Page<ProductModel> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Transactional
    public ProductModel save(ProductModel productModel){
        return productRepository.save(productModel);
    }

    @Transactional
    public void delete(ProductModel productModel){
        productRepository.delete(productModel);
    }
}
