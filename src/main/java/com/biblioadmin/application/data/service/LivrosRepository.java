package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LivrosRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor<Livro> {

}
