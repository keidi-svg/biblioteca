package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Estudante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EstudantesService {
    private final EstudantesRepository repository;

    public EstudantesService(EstudantesRepository repository) {
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
    public void salvar(Estudante cliente) {
        repository.save(cliente);
    }

    public int count() {
        return (int) repository.count();
    }
    //public Estudante salvar(Estudante equipamentos, String fileName, byte[] fileBytes) throws IOException {
    //    equipamentos.setImagem(fileBytes);
    //    Estudante equipamentosSalvo = repository.save(equipamentos);
    //    return equipamentosSalvo;
    //}
}
