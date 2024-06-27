package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.dao.EmprestimoDAO;
import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.service.EmprestimosService;
import com.biblioadmin.application.data.service.EstudantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    public EmprestimosService emprestimosService;

    @PostMapping
    public void create(@RequestBody Emprestimo emprestimo) throws SQLException {
        emprestimosService.createEmprestimo(emprestimo);
    }

    @GetMapping("/{id}")
    public Emprestimo read(@PathVariable Long id) throws SQLException {
        return emprestimosService.getEmprestimo(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Emprestimo emprestimo) throws SQLException {
        emprestimo.setId(id);
        emprestimosService.updateEmprestimo(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws SQLException {
        emprestimosService.deleteEmprestimo(id);
    }

    @GetMapping("/listar")
    public List<Emprestimo> findAll() throws SQLException {
        return emprestimosService.getAllEmprestimos();
    }
}
