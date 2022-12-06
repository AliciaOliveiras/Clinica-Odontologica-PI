package com.example.Clinica.Odontologica.repository.impl;

import com.example.Clinica.Odontologica.model.ConsultaModel;
import com.example.Clinica.Odontologica.repository.IDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ConsultaDaoH2 implements IDao<ConsultaModel> {

    final static Logger log = Logger.getLogger(ConsultaDaoH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";
    @Override
    public ConsultaModel salvar(ConsultaModel consultaModel) {
        log.debug("Salvando um dentista");
        Connection connection = null;
        PreparedStatement preparedStatement = null;



        return null;
    }

    @Override
    public ConsultaModel buscar(Long id) {
        return null;
    }

    @Override
    public Boolean deletar(Long id) {
        return null;
    }

    @Override
    public List<ConsultaModel> buscarTodos() {
        return null;
    }

    @Override
    public ConsultaModel atualizar(ConsultaModel consultaModel) {
        return null;
    }
}
