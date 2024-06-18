package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {
    private final LivrosRepository repository;

    public LivrosService(LivrosRepository repository) {
        this.repository = repository;
    }

    public Optional<Livro> get(Long id) {
        return repository.findById(id);
    }

    public Livro update(Livro entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Livro> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Livro> list(Pageable pageable, Specification<Livro> filter) {
        return repository.findAll(filter, pageable);
    }
    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public void salvar(Livro cliente) {
        repository.save(cliente);
    }

    public int count() {
        return (int) repository.count();
    }
}
