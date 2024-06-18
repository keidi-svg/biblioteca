package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmprestimosRepository extends JpaRepository<Emprestimo, Long>, JpaSpecificationExecutor<Emprestimo> {

}
