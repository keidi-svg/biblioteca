package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Livro;
import java.sql.SQLException;
import java.util.List;

public interface LivroDAOInterface {
    void create(Livro livro) throws SQLException;
    Livro read(Long id) throws SQLException;
    void update(Livro livro) throws SQLException;
    void delete(Long id) throws SQLException;
    List<Livro> findAll() throws SQLException;
}
