package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.dao.EstudanteDAO;
import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.service.EstudantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    public EstudantesService estudanteService;

    @PostMapping
    public void create(@RequestBody Estudante estudante) throws SQLException {
        estudanteService.create(estudante);
    }

    @GetMapping("/{id}")
    public Estudante read(@PathVariable Long id) throws SQLException {
        return estudanteService.read(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Estudante estudante) throws SQLException {
        estudante.setId(id);
        estudanteService.update(estudante);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws SQLException {
        estudanteService.delete(id);
    }

    @GetMapping("/listar")
    public List<Estudante> findAll() throws SQLException {
        return estudanteService.findAll();
    }
}

