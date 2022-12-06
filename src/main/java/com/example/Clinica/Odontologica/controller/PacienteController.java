package com.example.Clinica.Odontologica.controller;

import com.example.Clinica.Odontologica.dto.PacienteDto;
import com.example.Clinica.Odontologica.model.PacienteModel;
import com.example.Clinica.Odontologica.repository.impl.PacienteDaoH2;
import com.example.Clinica.Odontologica.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @GetMapping
    public List<PacienteDto> buscarTodos(){
        return pacienteService.buscarTodosPacientes();
    }

    @PostMapping
    public ResponseEntity<PacienteModel> registrarPaciente(@RequestBody PacienteModel paciente){
        return ResponseEntity.ok(pacienteService.salvarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<PacienteModel> atualizarPaciente(@RequestBody PacienteModel paciente){
        ResponseEntity<PacienteModel> response = null;

        if( paciente.getId() > 0 && pacienteService.buscarPaciente(paciente.getId()) != null)
            response = ResponseEntity.ok(pacienteService.atualizarPaciente(paciente));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> buscarPaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        ResponseEntity responseEntity = null;
        if(pacienteService.deletarPaciente(id))
            responseEntity = ResponseEntity.status(HttpStatus.OK).build();
        else
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return responseEntity;
    }

}
