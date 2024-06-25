package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.dao.EmprestimoDAO;
import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.biblioadmin.application.data.dao.EmprestimoDAOInterface;
import com.biblioadmin.application.data.entity.Emprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimosService {

    //@Autowired
    private EmprestimoDAOInterface emprestimoDAO;

    public void createEmprestimo(Emprestimo emprestimo) throws SQLException {
        emprestimoDAO.create(emprestimo);
    }

    public Emprestimo getEmprestimo(Long id) throws SQLException {
        return emprestimoDAO.read(id);
    }

    public void updateEmprestimo(Emprestimo emprestimo) throws SQLException {
        if(emprestimoDAO.read(emprestimo.getId()) != null)
            emprestimoDAO.update(emprestimo);
        else
            emprestimoDAO.create(emprestimo);
    }

    public void deleteEmprestimo(Long id) throws SQLException {
        emprestimoDAO.delete(id);
    }

    public List<Emprestimo> getAllEmprestimos() throws SQLException {
        return emprestimoDAO.findAll();
    }
}
