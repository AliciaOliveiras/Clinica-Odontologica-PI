package com.example.Clinica.Odontologica.repository.impl;

import com.example.Clinica.Odontologica.model.DentistaModel;
import com.example.Clinica.Odontologica.repository.IDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistaDaoH2 implements IDao<DentistaModel> {

    final static Logger log = Logger.getLogger(DentistaDaoH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";


    @Override
    public DentistaModel salvar(DentistaModel dentista) {
        log.debug("Salvando um dentista");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO dentistas(nome,sobrenome,matriculaDentista) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dentista.getNome());
            preparedStatement.setString(2, dentista.getSobrenome());
            preparedStatement.setInt(3, dentista.getMatriculaDentista());

            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                dentista.setId(keys.getLong(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return dentista;
    }

    @Override
    public DentistaModel buscar(Long id) {
        log.debug("Buscando ao dentista com id = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DentistaModel dentista = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT id,nome,sobrenome,matriculaDentista FROM dentistas where id = ?");
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long id2 = result.getLong("id");
                String nome = result.getString("nome");
                String sobrenome = result.getString("sobrenome");
                int matriculaDentista = result.getInt("matriculaDentista");

                dentista = new DentistaModel(id2, nome, sobrenome, matriculaDentista);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
        return dentista;
    }

    @Override
    public Boolean deletar(Long id) {
        log.debug("Deletando dentista com id : " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM dentistas where id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
        return null;
    }

    @Override
    public List<DentistaModel> buscarTodos() {
        log.debug("Listar todos os dentistas disponíveis.");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<DentistaModel> dentistas = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT *  FROM dentistas");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idDentista = result.getLong("id");
                String nome = result.getString("nome");
                String sobrenome = result.getString("sobrenome");
                int matriculaDentista = result.getInt("matriculaDentista");

                DentistaModel dentista = new DentistaModel(idDentista, nome, sobrenome, matriculaDentista);
                dentistas.add(dentista);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }

        return dentistas;
    }

    @Override
    public DentistaModel atualizar(DentistaModel dentista) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE dentistas SET nome = ?, sobrenome = ?,matriculaDentista = ?  WHERE id = ?");

            preparedStatement.setString(1, dentista.getNome());
            preparedStatement.setString(2, dentista.getSobrenome());
            preparedStatement.setInt(3, dentista.getMatriculaDentista());
            preparedStatement.setLong(4, dentista.getId());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            log.error(throwables);
        }
        return dentista;
    }}