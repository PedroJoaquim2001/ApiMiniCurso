package com.CusoUnivem.Curso.repository;

import com.CusoUnivem.Curso.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
    @Override
    Optional<ProductModel> findById(UUID uuid);

}
