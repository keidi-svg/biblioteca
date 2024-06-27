package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.dao.LivroDAO;
import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.data.service.EstudantesService;
import com.biblioadmin.application.data.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    public LivrosService livrosService;

    @PostMapping
    public void create(@RequestBody Livro livro) throws SQLException {
        livrosService.createLivro(livro);
    }

    @GetMapping("/{id}")
    public Livro read(@PathVariable Long id) throws SQLException {
        return livrosService.getLivro(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Livro livro) throws SQLException {
        livro.setId(id);
        livrosService.updateLivro(livro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws SQLException {
        livrosService.deleteLivro(id);
    }

    @GetMapping("/listar")
    public List<Livro> findAll() throws SQLException {
        return livrosService.getAllLivros();
    }
}
