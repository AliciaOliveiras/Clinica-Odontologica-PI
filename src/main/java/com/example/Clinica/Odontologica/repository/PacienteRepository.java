package com.example.Clinica.Odontologica.repository;

import com.example.Clinica.Odontologica.model.ConsultaModel;
import com.example.Clinica.Odontologica.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    @Query("Select paciente p from Paciente where p.nome = ?1 ")

    Optional<PacienteModel> findPacienteByNome(String nome);

    PacienteModel findById_Id(Long id);

}