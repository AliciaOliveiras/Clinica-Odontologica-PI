package com.example.Clinica.Odontologica.service;

import com.example.Clinica.Odontologica.dto.DentistaDto;
import com.example.Clinica.Odontologica.model.DentistaModel;
import com.example.Clinica.Odontologica.repository.DentistaRepository;
import com.example.Clinica.Odontologica.repository.IDao;
import com.example.Clinica.Odontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DentistaService {

    private IDao<DentistaModel> dentistaDao;

    public DentistaService(IDao<DentistaModel> dentistaIDao) {
        this.dentistaDao = dentistaIDao;
    }

    public DentistaModel salvarDentista(DentistaModel dentista){
        return dentistaDao.salvar(dentista);
    }

    public DentistaModel buscarDentista(Long id){
        return dentistaDao.buscar(id);
    }

    public List<DentistaDto> buscarTodosDentistas(){
        ObjectMapper mapper = new ObjectMapper();
        List<DentistaModel> dentistas = dentistaDao.buscarTodos();
        List<DentistaDto> dentistaDTOS = new ArrayList<>();
        for(DentistaModel dentista:dentistas){
            DentistaDto dentistaDto = mapper.convertValue(dentista, DentistaDto.class);
            dentistaDTOS.add(dentistaDto);
        }
        return dentistaDTOS;
    }

    public Boolean deletarDentista(Long id){
        return dentistaDao.deletar(id);
    }

    public DentistaModel atualizarDentista(DentistaModel dentista){
        return dentistaDao.atualizar(dentista);
    }

    private DentistaRepository dentistaRepository;

    @Autowired
    public DentistaService(DentistaRepository dentistaRepository){
        this.dentistaRepository = dentistaRepository;
    }

    public DentistaModel buscarPorNome(String nome){
        return dentistaRepository.findDentistaByNome(nome).get();
    }
}
