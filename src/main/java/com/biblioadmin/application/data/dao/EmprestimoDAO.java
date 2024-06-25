package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.data.entity.Estudante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO extends DAO implements EmprestimoDAOInterface {

    public EmprestimoDAO(Connection connection) {
        super(connection);
    }

    public void create(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo (data_emprestimo, data_entrega, devolucao, livro_id, estudante_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(2, Date.valueOf(emprestimo.getDataEntrega()));
            stmt.setBoolean(3, emprestimo.getDevolucao());
            stmt.setLong(4, emprestimo.getLivro().getId());
            stmt.setLong(5, emprestimo.getEstudante().getId());
            stmt.executeUpdate();
        }
    }

    public Emprestimo read(Long id) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("id"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                emprestimo.setDataEntrega(rs.getDate("data_entrega").toLocalDate());
                emprestimo.setDevolucao(rs.getBoolean("devolucao"));
                Livro livro = new Livro();
                livro.setId(rs.getLong("livro_id"));
                emprestimo.setLivro(livro);
                Estudante estudante = new Estudante();
                estudante.setId(rs.getLong("estudante_id"));
                emprestimo.setEstudante(estudante);
                return emprestimo;
            }
        }
        return null;
    }

    public void update(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo SET data_emprestimo = ?, data_entrega = ?, devolucao = ?, livro_id = ?, estudante_id = ? WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(2, Date.valueOf(emprestimo.getDataEntrega()));
            stmt.setBoolean(3, emprestimo.getDevolucao());
            stmt.setLong(4, emprestimo.getLivro().getId());
            stmt.setLong(5, emprestimo.getEstudante().getId());
            stmt.setLong(6, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Emprestimo> findAll() throws SQLException {
        String sql = "SELECT * FROM emprestimo";
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("id"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                emprestimo.setDataEntrega(rs.getDate("data_entrega").toLocalDate());
                emprestimo.setDevolucao(rs.getBoolean("devolucao"));
                Livro livro = new Livro();
                livro.setId(rs.getLong("livro_id"));
                emprestimo.setLivro(livro);
                Estudante estudante = new Estudante();
                estudante.setId(rs.getLong("estudante_id"));
                emprestimo.setEstudante(estudante);
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }
}
