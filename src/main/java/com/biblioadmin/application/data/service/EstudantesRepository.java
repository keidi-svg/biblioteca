package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstudantesRepository extends JpaRepository<Estudante, Long>, JpaSpecificationExecutor<Estudante> {

}
