package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Estudante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO extends DAO {

    public EstudanteDAO(Connection connection) {
        super(connection);
    }

    public void create(Estudante estudante) throws SQLException {
        String sql = "INSERT INTO estudante (nome, nascimento, email, telefone, matricula) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, estudante.getNome());
            stmt.setDate(2, Date.valueOf(estudante.getNascimento()));
            stmt.setString(3, estudante.getEmail());
            stmt.setString(4, estudante.getTelefone());
            stmt.setLong(5, estudante.getMatricula());
            stmt.executeUpdate();
        }
    }

    public Estudante read(Long id) throws SQLException {
        String sql = "SELECT * FROM estudante WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setId(rs.getLong("id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setNascimento(rs.getDate("nascimento").toLocalDate());
                estudante.setEmail(rs.getString("email"));
                estudante.setTelefone(rs.getString("telefone"));
                estudante.setMatricula(rs.getLong("matricula"));
                return estudante;
            }
        }
        return null;
    }

    public void update(Estudante estudante) throws SQLException {
        String sql = "UPDATE estudante SET nome = ?, nascimento = ?, email = ?, telefone = ?, matricula = ? WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, estudante.getNome());
            stmt.setDate(2, Date.valueOf(estudante.getNascimento()));
            stmt.setString(3, estudante.getEmail());
            stmt.setString(4, estudante.getTelefone());
            stmt.setLong(5, estudante.getMatricula());
            stmt.setLong(6, estudante.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM estudante WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Estudante> findAll() throws SQLException {
        String sql = "SELECT * FROM estudante";
        List<Estudante> estudantes = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setId(rs.getLong("id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setNascimento(rs.getDate("nascimento").toLocalDate());
                estudante.setEmail(rs.getString("email"));
                estudante.setTelefone(rs.getString("telefone"));
                estudante.setMatricula(rs.getLong("matricula"));
                estudantes.add(estudante);
            }
        }
        return estudantes;
    }
}
