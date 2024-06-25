package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Estudante;
import java.sql.SQLException;
import java.util.List;

public interface EstudanteDAOInterface {
    void create(Estudante estudante) throws SQLException;
    Estudante read(Long id) throws SQLException;
    void update(Estudante estudante) throws SQLException;
    void delete(Long id) throws SQLException;
    List<Estudante> findAll() throws SQLException;
}