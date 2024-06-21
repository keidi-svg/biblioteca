package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO extends DAO {

    public EstudanteDAO(final Connection connection) {
        super(connection);
    }

    public List<Estudante> findAll() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM estudante")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Estudante> estudantes = buildEstudante(rs);
                return estudantes;
            }
        }
    }

    public List<Estudante> findByAZ() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM estudante ORDER BY nome")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Estudante> estudantes = buildEstudante(rs);
                return estudantes;
            }
        }
    }

    public Estudante findById(Long idEstudante) throws SQLException {

        String sql = "SELECT * FROM estudante WHERE id = ?";
        try {
            PreparedStatement psmt = this.getConnection().prepareStatement(sql);
            psmt.setLong(1, idEstudante);
            ResultSet rs = psmt.executeQuery();
            rs.first();
            Estudante estudante = new Estudante();
            estudante.setId(rs.getLong("id"));
            estudante.setEmail(rs.getString("email"));
            estudante.setNome(rs.getString("nome"));
            estudante.setTelefone(rs.getString("telefone"));
            estudante.setMatricula(rs.getLong("matricula"));
            //PROVAVEL ERRO
            estudante.setNascimento(rs.getDate("nascimento").toLocalDate());
            return estudante;

        } catch (Exception e) {
            return null;

        }

    }

    private List<Estudante> buildEstudante(ResultSet rs) throws SQLException {

        final List<Estudante> livros = new ArrayList<>();
        while (rs.next()) {
            final Estudante livro = new Estudante()
                    .setId(rs.getLong("id"))
            (rs.getString("cpf"))
                    .setNome(rs.getString("nome"));

            livros.add(livro);
        }
        return livros;
    }
