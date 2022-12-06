package com.example.Clinica.Odontologica.service;

import com.example.Clinica.Odontologica.dto.ConsultaDto;
import com.example.Clinica.Odontologica.dto.DentistaDto;
import com.example.Clinica.Odontologica.model.ConsultaModel;
import com.example.Clinica.Odontologica.model.DentistaModel;
import com.example.Clinica.Odontologica.repository.IDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ConsultaService {

    private IDao<ConsultaModel> consultaDao;

    public ConsultaService(IDao<ConsultaModel> consultaIDao) {
        this.consultaDao = consultaIDao;
    }

    public ConsultaModel salvarConsulta(ConsultaModel consulta){
        return consultaDao.salvar(consulta);
    }

    public ConsultaModel buscarConsulta(Long id){
        return consultaDao.buscar(id);
    }

    public List<ConsultaDto> buscarTodasConsultas(){
        ObjectMapper mapper = new ObjectMapper();
        List<ConsultaModel> consultas = consultaDao.buscarTodos();
        List<ConsultaDto> consultaDTOS = new ArrayList<>();
        for(ConsultaModel consulta:consultas){
            ConsultaDto consultaDto = mapper.convertValue(consulta, ConsultaDto.class);
            consultaDTOS.add(consultaDto);
        }
        return consultaDTOS;
    }

    public ConsultaModel atualizarConsulta(ConsultaModel consulta){
        return consultaDao.atualizar(consulta);
    }
}
