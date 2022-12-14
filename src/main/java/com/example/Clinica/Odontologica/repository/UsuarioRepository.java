package com.example.Clinica.Odontologica.repository;

import com.example.Clinica.Odontologica.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    Optional<UsuarioModel> findByLogin(String login);

    Optional<UsuarioModel> findByUsername(String username);
}
