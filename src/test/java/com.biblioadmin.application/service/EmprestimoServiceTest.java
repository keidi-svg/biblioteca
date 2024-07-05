package com.biblioadmin.application.service;

import com.biblioadmin.application.data.entity.Emprestimo;
import com.biblioadmin.application.data.repository.EmprestimoRepository;
import com.biblioadmin.application.views.emprestimos.EmprestimoService;
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

public class EmprestimoServiceTest {

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));

        Optional<Emprestimo> result = emprestimoService.get(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testUpdateEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        Emprestimo result = emprestimoService.update(emprestimo);

        assertNotNull(result);
        verify(emprestimoRepository, times(1)).save(emprestimo);
    }

    @Test
    public void testDeleteEmprestimo() {
        doNothing().when(emprestimoRepository).deleteById(1L);

        emprestimoService.delete(1L);

        verify(emprestimoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testListEmprestimos() {
        Page<Emprestimo> page = new PageImpl<>(Collections.singletonList(new Emprestimo()));
        when(emprestimoRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Emprestimo> result = emprestimoService.list(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testListEmprestimosWithFilter() {
        Page<Emprestimo> page = new PageImpl<>(Collections.singletonList(new Emprestimo()));
        when(emprestimoRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);

        Page<Emprestimo> result = emprestimoService.list(PageRequest.of(0, 10), mock(Specification.class));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testListarTodos() {
        List<Emprestimo> list = Collections.singletonList(new Emprestimo());
        when(emprestimoRepository.findAll()).thenReturn(list);

        List<Emprestimo> result = emprestimoService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testSalvarEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        emprestimoService.salvar(emprestimo);

        verify(emprestimoRepository, times(1)).save(emprestimo);
    }

    @Test
    public void testCountEmprestimos() {
        when(emprestimoRepository.count()).thenReturn(10L);

        int result = emprestimoService.count();

        assertEquals(10, result);
    }

    @Test
    public void testUpdateDevolucao() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDevolucao(false);
        when(emprestimoRepository.findById(1L)).thenReturn(Optional.of(emprestimo));
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        emprestimoService.updateDevolucao(1L, true);

        assertTrue(emprestimo.getDevolucao());
        verify(emprestimoRepository, times(1)).save(emprestimo);
    }
}
