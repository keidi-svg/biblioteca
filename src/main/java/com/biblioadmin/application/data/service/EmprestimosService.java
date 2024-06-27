package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.Emprestimo;
import org.springframework.stereotype.Service;
import java.util.List;
import com.biblioadmin.application.data.dao.EmprestimoDAOInterface;
import java.sql.SQLException;

@Service
public class EmprestimosService {

    //@Autowired
    private EmprestimoDAOInterface emprestimoDAO;

    public void createEmprestimo(Emprestimo emprestimo) throws SQLException {
        emprestimoDAO.create(emprestimo);
    }

    public Emprestimo getEmprestimo(Long id) throws SQLException {
        return emprestimoDAO.read(id);
    }

    public void updateEmprestimo(Emprestimo emprestimo) throws SQLException {
        if(emprestimoDAO.read(emprestimo.getId()) != null)
            emprestimoDAO.update(emprestimo);
        else
            emprestimoDAO.create(emprestimo);
    }

    public void updateDevolucao(Long id, boolean devolucao) throws SQLException {
        emprestimoDAO.updateDevolucao(id, devolucao);
    }

    public void deleteEmprestimo(Long id) throws SQLException {
        emprestimoDAO.delete(id);
    }

    public List<Emprestimo> getAllEmprestimos() throws SQLException {
        return emprestimoDAO.findAll();
    }
}
