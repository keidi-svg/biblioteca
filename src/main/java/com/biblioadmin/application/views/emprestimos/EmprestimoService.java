package com.biblioadmin.application.views.emprestimos;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.repository.EmprestimoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    private final EmprestimoRepository repository;

    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }

    public Optional<Emprestimo> get(Long id) {
        return repository.findById(id);
    }

    public Emprestimo update(Emprestimo entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Emprestimo> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Emprestimo> list(Pageable pageable, Specification<Emprestimo> filter) {
        return repository.findAll(filter, pageable);
    }
    public List<Emprestimo> listarTodos() {
        return repository.findAll();
    }

    public void salvar(Emprestimo emprestimo) {
        repository.save(emprestimo);
    }

    public int count() {
        return (int) repository.count();
    }

    public void updateDevolucao(Long id, boolean devolucao) {
        Optional<Emprestimo> optionalEmprestimo = repository.findById(id);
        if (optionalEmprestimo.isPresent()) {
            Emprestimo emprestimo = optionalEmprestimo.get();
            emprestimo.setDevolucao(devolucao);
            repository.save(emprestimo);
        }
    }
}