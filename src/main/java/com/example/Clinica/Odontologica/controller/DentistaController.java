package com.example.Clinica.Odontologica.controller;

import com.example.Clinica.Odontologica.dto.DentistaDto;
import com.example.Clinica.Odontologica.model.DentistaModel;
import com.example.Clinica.Odontologica.repository.impl.DentistaDaoH2;
import com.example.Clinica.Odontologica.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    private DentistaService dentistaService = new DentistaService(new DentistaDaoH2());

    @GetMapping
    public List<DentistaDto> buscarTodos(){
        return dentistaService.buscarTodosDentistas();
    }

    @PostMapping
    public ResponseEntity<DentistaModel> registrarDentista(@RequestBody DentistaModel dentista){
        return ResponseEntity.ok(dentistaService.salvarDentista(dentista));
    }

    @PutMapping
    public ResponseEntity<DentistaModel> atualizarDentista(@RequestBody DentistaModel dentista){
        ResponseEntity<DentistaModel> response = null;

        if( dentista.getId() > 0 && dentistaService.buscarDentista(dentista.getId()) != null)
            response = ResponseEntity.ok(dentistaService.atualizarDentista(dentista));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaModel> buscarDentista(@PathVariable Long id){
        return ResponseEntity.ok(dentistaService.buscarDentista(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDentista(@PathVariable Long id){
        ResponseEntity responseEntity = null;
        if(dentistaService.deletarDentista(id))
            responseEntity = ResponseEntity.status(HttpStatus.OK).build();
        else
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return responseEntity;
    }
}
