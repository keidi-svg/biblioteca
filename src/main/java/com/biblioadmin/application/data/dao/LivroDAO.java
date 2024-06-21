package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO extends DAO {

    public LivroDAO(final Connection connection) {
        super(connection);
    }

    public List<Livro> findAll() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM livro")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Livro> pessoas = buildPessoa(rs);
                return pessoas;
            }
        }
    }

    public List<Livro> findByAZ() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM livro ORDER BY titulo")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Livro> pessoas = buildPessoa(rs);
                return pessoas;
            }
        }
    }

    public Livro findById(Long idLivro) throws SQLException {

        String sql = "SELECT * FROM livro WHERE id = ?";
        try {
            PreparedStatement psmt = this.getConnection().prepareStatement(sql);
            psmt.setLong(1, idLivro);
            ResultSet rs = psmt.executeQuery();
            rs.first();
            Livro livro = new Livro();
            livro.setId(rs.getLong("id"));
            livro.setAno(rs.getInt("ano"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setEditora(rs.getString("editora"));
            livro.setAutor(rs.getString("autor"));
            return livro;

        } catch (Exception e) {
            return null;

        }

    }

    private List<Livro> builLivro(ResultSet rs) throws SQLException {

        final List<Livro> livros = new ArrayList<>();
        while (rs.next()) {
            final Livro livro = new Livro()
                    .setId(rs.getLong("ID_Pessoa"))
                    .setCpf(rs.getString("cpf"))
                    .setNome(rs.getString("nome"));

            livros.add(livro);
        }
        return livros;
    }
}