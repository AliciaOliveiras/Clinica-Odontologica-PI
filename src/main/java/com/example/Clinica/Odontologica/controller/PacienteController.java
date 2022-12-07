package com.example.Clinica.Odontologica.controller;

import com.example.Clinica.Odontologica.dto.PacienteDto;
import com.example.Clinica.Odontologica.exception.BadRequestException;
import com.example.Clinica.Odontologica.exception.ResourceNotFoundException;
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
    public ResponseEntity<PacienteModel> registrarPaciente(@RequestBody PacienteModel paciente) throws ResourceNotFoundException{
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
    public ResponseEntity<PacienteModel> buscarPaciente(@PathVariable Long id)throws ResourceNotFoundException {

        try {
            return ResponseEntity.ok(pacienteService.buscarPaciente(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o paciente que você quis buscar por id de número: " + id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarPaciente(@PathVariable Long id)throws ResourceNotFoundException {

        try {
            pacienteService.deletarPaciente(id);
            return ResponseEntity.ok("Deletado");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o paciente para deletar de id: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadRequest(BadRequestException ex){

        //essa classe vai pegar o erro para nós e reportar para o ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
