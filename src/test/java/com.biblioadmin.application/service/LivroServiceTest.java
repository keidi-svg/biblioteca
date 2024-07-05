package com.biblioadmin.application.service;

import com.biblioadmin.application.data.entity.Livro;
import com.biblioadmin.application.data.repository.LivroRepository;
import com.biblioadmin.application.views.livros.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetLivro() {
        Livro livro = new Livro();
        livro.setId(1L);
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        Optional<Livro> result = livroService.get(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testUpdateLivro() {
        Livro livro = new Livro();
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro result = livroService.update(livro);

        assertNotNull(result);
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    public void testDeleteLivro() {
        doNothing().when(livroRepository).deleteById(1L);

        livroService.delete(1L);

        verify(livroRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testListLivros() {
        Page<Livro> page = new PageImpl<>(Collections.singletonList(new Livro()));
        when(livroRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Livro> result = livroService.list(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testListLivrosWithFilter() {
        Page<Livro> page = new PageImpl<>(Collections.singletonList(new Livro()));
        when(livroRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);

        Page<Livro> result = livroService.list(PageRequest.of(0, 10), mock(Specification.class));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testListarTodos() {
        List<Livro> list = Collections.singletonList(new Livro());
        when(livroRepository.findAll()).thenReturn(list);

        List<Livro> result = livroService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testSalvarLivro() {
        Livro livro = new Livro();
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        livroService.salvar(livro);

        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    public void testCountLivros() {
        when(livroRepository.count()).thenReturn(10L);

        int result = livroService.count();

        assertEquals(10, result);
    }
}
