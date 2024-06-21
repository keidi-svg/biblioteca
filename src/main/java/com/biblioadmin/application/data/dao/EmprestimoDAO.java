package com.biblioadmin.application.data.dao;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO extends DAO {

    public EmprestimoDAO(final Connection connection) {
        super(connection);
    }

    public List<Emprestimo> findAll() throws SQLException {

        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT pedido_tb.*, pessoa_tb.*, pedido_item_tb.*,produto_tb.*"
                + " FROM pedido_tb INNER JOIN pessoa_tb ON pedido_tb.ID_Pessoa = pessoa_tb.ID_Pessoa\r\n"
                + "INNER JOIN pedido_item_tb ON pedido_item_tb.ID_Pedido_Item = pedido_tb.ID_Pedido INNER JOIN produto_tb ON  pedido_item_tb.ID_Produto = produto_tb.ID_Produto;")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Emprestimo> pedidos = buildPedido(rs);
                return pedidos;
            }
        }
    }

    public List<Emprestimo> findPedidosOrderByValor() throws SQLException {

        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT pedido_tb.*, pessoa_tb.*, pedido_item_tb.*,produto_tb.* FROM pedido_tb INNER JOIN pessoa_tb ON pedido_tb.ID_Pessoa  = pessoa_tb.ID_Pessoa \r\n"
                + "INNER JOIN pedido_item_tb ON pedido_item_tb.ID_Pedido_Item = pedido_tb.ID_Pedido INNER JOIN produto_tb ON  pedido_item_tb.ID_Produto = produto_tb.ID_Produto order by valor_total;")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Emprestimo> pedidos = buildPedido(rs);
                return pedidos;
            }
        }
    }

    public List<Emprestimo> findPedidosbyClienteId(Long ClienteId) throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT pedido.*, pessoa.*, pedido_item.*,produto.* FROM pedido_tb INNER JOIN pessoa_tb ON pedido_tb.ID_Pessoa = pessoa_tb.ID_Pessoa \r\n"
                + "INNER JOIN pedido_item_tb ON pedido_tb.ID_Pedido_Item = pedido_tb.ID_Pedido  INNER JOIN produto_tb ON  pedido_item_tb.ID_Produto = produto_tb.ID_Produto WHERE ID_Pessoa = ?;")) {
            psmt.setLong(1, ClienteId);
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Emprestimo> pedidos = buildPedido(rs);
                return pedidos;
            }

        }
    }

    private List<Emprestimo> buildPedido(ResultSet rs) throws SQLException {
        final List<Emprestimo> emprestimos = new ArrayList<>();

        while (rs.next()) {
            final Estudante produto = new Estudante()
                    .setRegistro(rs.getInt("ID_Produto"))
                    .setDescricao(rs.getString("descricao"))
                    .setValorUnitario(rs.getBigDecimal("valor_unitario"));

            final Livro livro = new Livro()
                    //.setId(rs.getLong("ID_Pedido_Item"))
                    .setAno(rs.getInt("ano"))
                    .setValorTotal(rs.getBigDecimal("valor_total"))
                    .setProduto(produto);


            emprestimos.add(pedidoItem);

        }
        return emprestimos;
    }

}