package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO extends DAO implements LivroDAOInterface {

    public LivroDAO(Connection connection) {
        super(connection);
    }

    public void create(Livro livro) throws SQLException {
        String sql = "INSERT INTO livro (titulo, autor, editora, ano) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.executeUpdate();
        }
    }

    public Livro read(Long id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAno(rs.getInt("ano"));
                return livro;
            }
        }
        return null;
    }

    public void update(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, editora = ?, ano = ? WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setLong(5, livro.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Livro> findAll() throws SQLException {
        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAno(rs.getInt("ano"));
                livros.add(livro);
            }
        }
        return livros;
    }
}
