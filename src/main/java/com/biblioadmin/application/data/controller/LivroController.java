package com.biblioadmin.application.data.controller;

import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.data.service.EstudantesService;
import com.biblioadmin.application.data.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroController {

    @Autowired
    public LivrosService livrosService;
    @Autowired
    private EstudantesService estudantesService;


    @GetMapping("/listar")
    public List<Livro> listar() throws Exception {
        return livrosService.listarTodos();
    }

    @GetMapping("/listar/{id}")
    public List<Livro> listarByLivro(@PathVariable("id") long idLivro) throws Exception {
        return livrosService.get(idLivro); // TODO IMPLEMENTAR
    }
}