package com.example.Clinica.Odontologica.repository;

import com.example.Clinica.Odontologica.model.DentistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DentistaRepository extends JpaRepository<DentistaModel, Long> {

    @Query("Select dentista d from Dentista where d.nome = ?1 ")
    Optional<DentistaModel> findDentistaByNome(String nome);
}
