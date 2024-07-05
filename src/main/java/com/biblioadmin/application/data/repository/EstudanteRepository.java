package com.biblioadmin.application.data.repository;

import com.biblioadmin.application.data.entity.Estudante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>, JpaSpecificationExecutor<Estudante> {

    Optional<Estudante> findByEmail(String email);
    Optional<Estudante> findByMatricula(Long matricula);
    List<Estudante> findByNome(String nome);
    Page<Estudante> findAll(Pageable pagina);

    void deleteByMatricula(Long matricula);

}
