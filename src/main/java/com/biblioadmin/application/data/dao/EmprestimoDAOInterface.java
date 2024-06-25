package com.biblioadmin.application.data.dao;


import com.biblioadmin.application.data.entity.Emprestimo;
import java.sql.SQLException;
import java.util.List;

public interface EmprestimoDAOInterface {
    void create(Emprestimo emprestimo) throws SQLException;
    Emprestimo read(Long id) throws SQLException;
    void update(Emprestimo emprestimo) throws SQLException;
    void delete(Long id) throws SQLException;
    List<Emprestimo> findAll() throws SQLException;
}
