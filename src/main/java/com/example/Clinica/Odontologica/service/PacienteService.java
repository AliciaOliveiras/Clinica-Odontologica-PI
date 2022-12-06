package com.example.Clinica.Odontologica.service;

import com.example.Clinica.Odontologica.dto.PacienteDto;
import com.example.Clinica.Odontologica.model.PacienteModel;
import com.example.Clinica.Odontologica.repository.IDao;
import com.example.Clinica.Odontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PacienteService {

    private IDao<PacienteModel> pacienteDao;

    public PacienteService(IDao<PacienteModel> pacienteIDao) {
        this.pacienteDao = pacienteIDao;
    }

    public PacienteModel salvarPaciente(PacienteModel paciente){
        paciente.setDataAlta(new Date());
        return pacienteDao.salvar(paciente);
    }

    public PacienteModel buscarPaciente(Long id){
        return pacienteDao.buscar(id);
    }

    public List<PacienteDto> buscarTodosPacientes(){
        ObjectMapper mapper = new ObjectMapper();
        List<PacienteModel> pacientes = pacienteDao.buscarTodos();
        List<PacienteDto> pacienteDTOS = new ArrayList<>();
        for(PacienteModel paciente:pacientes){
            PacienteDto pacienteDto = mapper.convertValue(paciente, PacienteDto.class);
            pacienteDTOS.add(pacienteDto);
        }
        return pacienteDTOS;
    }

    public Boolean deletarPaciente(Long id){
        return pacienteDao.deletar(id);
    }

    public PacienteModel atualizarPaciente(PacienteModel paciente){
        return pacienteDao.atualizar(paciente);
    }

    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteModel buscarPorNome(String nome){
        return pacienteRepository.findPacienteByNome(nome).get();
    }
}

