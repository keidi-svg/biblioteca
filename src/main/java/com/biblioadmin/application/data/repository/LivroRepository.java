package com.biblioadmin.application.data.repository;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LivroRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor<Livro> {

    Estudante findByTitulo(String titulo);

}