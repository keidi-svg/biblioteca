package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.views.livros.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livrosService;

    @Operation(summary = "Cria um novo livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public void create(@RequestBody Livro livro) throws SQLException {
        livrosService.salvar(livro);
    }

    @Operation(summary = "Obtém um livro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/{id}")
    public Optional<Livro> read(@PathVariable Long id) throws SQLException {
        return livrosService.get(id);
    }

    @Operation(summary = "Atualiza um livro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Livro livro) throws SQLException {
        livro.setId(id);
        livrosService.update(livro);
    }

    @Operation(summary = "Exclui um livro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws SQLException {
        livrosService.delete(id);
    }

    @Operation(summary = "Lista todos os livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/listar")
    public List<Livro> findAll() throws SQLException {
        return livrosService.listarTodos();
    }
}
