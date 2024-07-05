package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.views.emprestimos.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    public EmprestimoService emprestimosService;

    @Operation(summary = "Cria um novo empréstimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public void create(@RequestBody Emprestimo emprestimo) throws SQLException {
        emprestimosService.salvar(emprestimo);
    }

    @Operation(summary = "Obtém um empréstimo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo encontrado"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado")
    })
    @GetMapping("/{id}")
    public Optional<Emprestimo> read(@PathVariable Long id) throws SQLException {
        return emprestimosService.get(id);
    }

    @Operation(summary = "Atualiza um empréstimo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Emprestimo emprestimo) throws SQLException {
        emprestimo.setId(id);
        emprestimosService.update(emprestimo);
    }

    @Operation(summary = "Exclui um empréstimo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empréstimo excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws SQLException {
        emprestimosService.delete(id);
    }

    @Operation(summary = "Lista todos os empréstimos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empréstimos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/listar")
    public List<Emprestimo> findAll() throws SQLException {
        return emprestimosService.listarTodos();
    }
}
