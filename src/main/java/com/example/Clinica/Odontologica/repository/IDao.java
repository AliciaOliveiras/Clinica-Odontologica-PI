package com.example.Clinica.Odontologica.repository;

import java.util.List;

public interface IDao <T> {

    T salvar(T t);
    T buscar(Long id);
    Boolean deletar(Long id);
    List<T> buscarTodos();
    T atualizar(T t);
}
