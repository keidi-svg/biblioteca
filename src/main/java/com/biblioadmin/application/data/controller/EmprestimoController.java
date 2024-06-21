package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.service.EmprestimosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    private final EmprestimosService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimosService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public List<Emprestimo> getAllEmprestimos() {
        return emprestimoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getEmprestimoById(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoService.findById(id);
        return emprestimo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Emprestimo createEmprestimo(@RequestBody Emprestimo emprestimo) {
        return emprestimoService.save(emprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> updateEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimoDetails) {
        Optional<Emprestimo> emprestimoOptional = emprestimoService.findById(id);
        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            emprestimo.setDataEmprestimo(emprestimoDetails.getDataEmprestimo());
            emprestimo.setDataEntrega(emprestimoDetails.getDataEntrega());
            emprestimo.setDevolucao(emprestimoDetails.getDevolucao());
            emprestimo.setLivro(emprestimoDetails.getLivro());
            emprestimo.setEstudante(emprestimoDetails.getEstudante());
            return ResponseEntity.ok(emprestimoService.salvar(emprestimo););
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprestimo(@PathVariable Long id) {
        if (emprestimoService.findById(id).isPresent()) {
            emprestimoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
