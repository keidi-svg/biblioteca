package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import com.biblioadmin.application.data.dao.LivroDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@Service
public class LivrosService {

    //@Autowired
    private LivroDAOInterface livroDAO;

    public void createLivro(Livro livro) throws SQLException {
        livroDAO.create(livro);
    }

    public Livro getLivro(Long id) throws SQLException {
        return livroDAO.read(id);
    }

    public void updateLivro(Livro livro) throws SQLException {
        livroDAO.update(livro);
    }

    public void deleteLivro(Long id) throws SQLException {
        livroDAO.delete(id);
    }

    public List<Livro> getAllLivros() throws SQLException {
        return livroDAO.findAll();
    }
}
