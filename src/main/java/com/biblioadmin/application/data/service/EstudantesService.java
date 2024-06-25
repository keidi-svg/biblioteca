package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Estudante;
import org.springframework.stereotype.Service;

import java.util.List;

import com.biblioadmin.application.data.dao.EstudanteDAOInterface;

import java.sql.SQLException;

@Service
public class EstudantesService {

    //@Autowired
    private EstudanteDAOInterface estudanteDAO;

    public void createEstudante(Estudante estudante) throws SQLException {
        estudanteDAO.create(estudante);
    }

    public Estudante getEstudante(Long id) throws SQLException {
        return estudanteDAO.read(id);
    }

    public void updateEstudante(Estudante estudante) throws SQLException {
        estudanteDAO.update(estudante);
    }

    public void deleteEstudante(Long id) throws SQLException {
        estudanteDAO.delete(id);
    }

    public List<Estudante> getAllEstudantes() throws SQLException {
        return estudanteDAO.findAll();
    }
}
