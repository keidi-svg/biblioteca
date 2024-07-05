package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.views.estudantes.EstudanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @Operation(summary = "Inclui um novo estudante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante incluído com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/estudante")
    public ResponseEntity<Long> incluirEstudante(@RequestBody Estudante estudante){
        Long IdEstudante = estudanteService.incluirEstudante(estudante);
        if(IdEstudante != null){
            return new ResponseEntity<>(IdEstudante, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @Operation(summary = "Altera um estudante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/estudante")
    public ResponseEntity<Boolean> alterarEstudante(@RequestBody Estudante estudante){
        if(estudanteService.alterarEstudante(estudante)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Exclui um estudante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/estudante/id/{IdEstudante}")
    public ResponseEntity<Boolean> excluirEstudantePorId(@PathVariable("IdEstudante") Long IdEstudante){
        if(estudanteService.excluirEstudantePorId(IdEstudante)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Exclui um estudante por matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/estudante/matricula/{matricula}")
    public ResponseEntity<Boolean> excluirEstudantePorMatricula(@PathVariable("matricula") Long matricula){
        if(estudanteService.excluirEstudantePorMatricula(matricula)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Consulta um estudante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/estudante/id/{IdEstudante}")
    public ResponseEntity<Estudante> consultaEstudantePorId(@PathVariable("IdEstudante") Long IdEstudante){
        Estudante estude = estudanteService.consultaEstudantePorId(IdEstudante);
        if(estude != null){
            return new ResponseEntity<>(estude,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Consulta um estudante por matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/estudante/matricula/{matricula}")
    public ResponseEntity<Estudante> consultaEstudantePorMatricula(@PathVariable("matricula") Long matricula){
        Estudante estude = estudanteService.consultaEstudantePorMatricula(matricula);
        if(estude != null){
            return new ResponseEntity<>(estude,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Consulta um estudante por email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudante não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/estudante/email/{email}")
    public ResponseEntity<Estudante> consultaEstudantePorEmail(@PathVariable("email") String email){
        Estudante estude = estudanteService.consultaEstudantePorEmail(email);
        if(estude != null){
            return new ResponseEntity<>(estude,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Lista todos os estudantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estudantes retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/estudante/listar")
    public ResponseEntity<List<Estudante>> listarEstudantes(){
        List<Estudante> estudantes = estudanteService.listarTodos();
        return new ResponseEntity<>(estudantes,HttpStatus.OK);
    }
}
