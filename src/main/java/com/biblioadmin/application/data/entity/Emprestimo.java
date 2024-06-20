package com.biblioadmin.application.data.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
public class Emprestimo extends AbstractEntity {
    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    @Column(name = "devolucao")
    private Boolean devolucao;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    // Getters and Setters
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public Boolean getDevolucao() { return devolucao; }

    public void setDevolucao(Boolean devolucao) {
        this.devolucao = devolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
}
