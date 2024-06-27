package com.biblioadmin.application.views.estudantes;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.service.EstudantesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {
    private final EstudantesRepository repository;

    public EstudanteService(EstudantesRepository repository) {
        this.repository = repository;
    }

    public Optional<Estudante> get(Long id) {
        return repository.findById(id);
    }

    public Estudante update(Estudante entity) {
        return repository.save(entity);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Estudante> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Estudante> list(Pageable pageable, Specification<Estudante> filter) {
        return repository.findAll(filter, pageable);
    }
    public List<Estudante> listarTodos() {
        return repository.findAll();
    }
    public Estudante salvar(Estudante estudante) {
        return repository.save(estudante);
    }
}