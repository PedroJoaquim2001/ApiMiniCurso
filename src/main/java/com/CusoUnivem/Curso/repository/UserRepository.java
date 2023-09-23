package com.CusoUnivem.Curso.repository;


import com.CusoUnivem.Curso.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    @Override
    Optional<UserModel> findById(UUID uuid);

    Optional<UserModel> findByEmail(String string);
}
