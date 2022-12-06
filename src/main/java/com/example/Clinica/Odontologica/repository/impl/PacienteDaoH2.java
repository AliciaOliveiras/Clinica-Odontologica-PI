package com.example.Clinica.Odontologica.repository.impl;

import com.example.Clinica.Odontologica.model.PacienteModel;
import com.example.Clinica.Odontologica.repository.IDao;
import com.example.Clinica.Odontologica.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements IDao<PacienteModel> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    @Override
    public PacienteModel salvar(PacienteModel paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            preparedStatement = connection.prepareStatement("INSERT INTO pacientes(nome,sobrenome,endereco,rg,dataAlta) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getSobrenome());
            preparedStatement.setString(3, paciente.getEndereco());
            preparedStatement.setInt(4, paciente.getRg());
            preparedStatement.setDate(5, com.example.Clinica.Odontologica.util.Util.utilDateToSqlDate(paciente.getDataAlta()));

            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                paciente.setId(keys.getLong(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }

    @Override
    public PacienteModel buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PacienteModel paciente = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT id,nome,sobrenome,endereco,rg,dataAlta  FROM pacientes where id = ?");
            preparedStatement.setLong(1,id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idPaciente = result.getLong("id");
                String nome = result.getString("nome");
                String sobrenome = result.getString("sobrenome");
                String endereco = result.getString("endereco");
                int rg = result.getInt("rg");
                java.sql.Date dataAlta = result.getDate("dataAlta");

                paciente = new PacienteModel(idPaciente,nome,sobrenome,endereco,rg,dataAlta);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return paciente;
    }

    @Override
    public Boolean deletar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM pacientes where id = ?");
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PacienteModel> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<PacienteModel> pacientes = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT *  FROM pacientes");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idPaciente = result.getLong("id");
                String nome = result.getString("nome");
                String sobrenome = result.getString("sobrenome");
                String endereco = result.getString("endereco");
                int rg = result.getInt("rg");
                java.sql.Date dataAlta = result.getDate("dataAlta");

                PacienteModel paciente = new PacienteModel(idPaciente,nome,sobrenome,endereco,rg,dataAlta);
                pacientes.add(paciente);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public PacienteModel atualizar(PacienteModel paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE pacientes SET nome=?, sobrenome=?, endereco=?, rg=?, dataAlta=?  WHERE id = ?");

            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getSobrenome());
            preparedStatement.setString(3, paciente.getEndereco());
            preparedStatement.setInt(4, paciente.getRg());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(paciente.getDataAlta()));

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }
}
