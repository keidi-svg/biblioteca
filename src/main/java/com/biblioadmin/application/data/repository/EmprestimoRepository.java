package com.biblioadmin.application.data.repository;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>, JpaSpecificationExecutor<Emprestimo> {

    Emprestimo findByEstudante(Estudante estudante);
    Emprestimo findByLivro(Livro livro);

}
