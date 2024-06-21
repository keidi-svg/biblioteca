package com.biblioadmin.application.service;

import java.util.List;
import java.util.Random;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.service.EstudantesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EstudanteServiceTest {
    @Autowired
    private EstudantesService estudanteService;

    Random random = new Random();

    public EstudanteServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("\n##### Fim da rotina de Testes #####\n");
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testIncluirEstudanteOk() {
        System.out.println("\n##### Inicio da rotina de Testes #####");
        Estudante estudante = new Estudante();
        estudante.setNome("Estudante Teste #1");
        estudante.setEmail("estudante_1@gmail.com");
        estudante.setTelefone("48999995878");
        estudante.setMatricula(geraRandomico());
        Long IdEstudante = estudanteService.salvar(estudante).getId();
        Long IdEstudanteExp = null;
        System.out.println("#1 Incluir estudante ok! ");
        assertNotEquals(IdEstudante, IdEstudanteExp,"ERRO: #1 Nao incluiu estudante corretamente ");
    }
    @Test
    @Order(2)
    public void testIncluirEstudanteSemNome() {

        Estudante estudante = new Estudante();
        //estudante.setNome("Estudante Teste #2");
        estudante.setEmail("estudante_2@gmail.com");
        estudante.setTelefone("48999995878");
        estudante.setMatricula(geraRandomico());
        Long IdEstudante = estudanteService.salvar(estudante).getId();
        Long IdEstudanteExp = null;
        System.out.println("#2 Incluir estudante sem nome ");
        assertEquals(IdEstudante, IdEstudanteExp,"ERRO: #2 Incluiu estudante sem nome! ");
    }
    @Test
    @Order(3)
    public void testIncluirEstudanteSemMatricula() {
        Estudante estudante = new Estudante();
        estudante.setNome("Estudante Teste #3");
        estudante.setEmail("estudante_3@gmail.com");
        estudante.setTelefone("48999995878");
        //estudante.setMatricula(geraRandomico());
        Long IdEstudante = estudanteService.salvar(estudante).getId();
        Long IdEstudanteExp = null;
        System.out.println("#3 Incluir estudante sem matricula ");
        assertEquals(IdEstudante, IdEstudanteExp,"ERRO: #3 Incluiu estudante sem matricula! ");
    }
    @Test
    @Order(4)
    public void testIncluirEstudanteMatriculaCadastrada() {
        List<Estudante> listEstudante = estudanteService.listarTodos();
        if( ! listEstudante.isEmpty() ){
            Estudante estudante = new Estudante();
            estudante.setNome("Estudante Teste #4");
            estudante.setEmail("estudante_4@gmail.com");
            estudante.setTelefone("48999995878");
            estudante.setMatricula(listEstudante.get(0).getMatricula());
            Long IdEstudante = estudanteService.salvar(estudante).getId();
            Long IdEstudanteExp = null;
            System.out.println("#4 Incluir estudante com matricula cadastrada");
            assertEquals(IdEstudante, IdEstudanteExp,"ERRO: #4 Incluiu estudante com matricula duplicada ");
        } else {
            assertFalse(true,"#4 ERRO: Sem estudante para testar inclusao duplicada..!");
        }
    }
    @Test
    @Order(5)
    public void testIncluirEstudanteSemEmail() {
        Estudante estudante = new Estudante();
        estudante.setNome("Estudante Teste #3");
        //estudante.setEmail("estudante_3@gmail.com");
        estudante.setTelefone("48999995878");
        estudante.setMatricula(geraRandomico());
        Long IdEstudante = estudanteService.salvar(estudante).getId();
        Long IdEstudanteExp = null;
        System.out.println("#5 Incluir estudante sem email ");
        assertEquals(IdEstudante, IdEstudanteExp,"ERRO: #5 Incluiu estudante sem email! ");
    }
    @Test
    @Order(6)
    public void testIncluirEstudanteSemTelefone() {
        Estudante estudante = new Estudante();
        estudante.setNome("Estudante Teste #6");
        estudante.setEmail("estudante_6@gmail.com");
        //estudante.setTelefone("48999995878");
        estudante.setMatricula(geraRandomico());
        Long IdEstudante = estudanteService.salvar(estudante).getId();
        Long IdEstudanteExp = null;
        System.out.println("#6 Incluir estudante sem telefone ");
        assertNotEquals(IdEstudante, IdEstudanteExp,"ERRO: #6 Nao incluiu estudante sem telefone! ");
    }
    @Test
    @Order(7)
    public void testIncluirEstudanteSemSenha() {
        Estudante estudante = new Estudante();
        estudante.setNome("Estudante Teste #7");
        estudante.setEmail("estudante_7@gmail.com");
        //estudante.setSenha("senha");
        estudante.setTelefone("48999995878");
        estudante.setMatricula(geraRandomico());
        Long IdEstudante = estudanteService.salvar(estudante).getId();
        Long IdEstudanteExp = null;
        System.out.println("#7 Incluir estudante sem senha ");
        assertEquals(IdEstudante, IdEstudanteExp,"ERRO: #7 Incluiu estudante sem senha! ");
    }
    //@Test
    //@Order(8)
    //public void testLoginEstudante() {
    //    List<Estudante> listaEstudante = estudanteService.listarTodos();
    //    if( ! listaEstudante.isEmpty() ){
    //        Long matriculaBD = listaEstudante.get(1).getMatricula();
    //        String senha = "senha";
    //        Estudante estudante = estudanteService.salvar(est);
    //        Long IdEstudanteExp = null;
    //        System.out.println("#8 Login estudante cadastrado! ");
    //        assertNotEquals(estudante, IdEstudanteExp,"ERRO: #8 Falha de Login do estudante! ");
    //    } else {
    //        assertFalse(true,"#8 ERRO: Sem estudantes para testar Login! ");
    //    }
    //}
    //@Test
    //@Order(9)
    //public void testLoginEstudanteMatriculaInexistente() {
    //    String senha = "senha";
    //    Long matricula = geraRandomico();
    //    Estudante estudante = estudanteService.loginEstudante(matricula, senha);
    //    Long IdEstudanteExp = null;
    //    System.out.println("#9 Login estudante matricula nao cadastrada! ");
    //    assertEquals(estudante, IdEstudanteExp,"ERRO: #9 Falha de Login do estudante matricula nao cadastrada! ");
    //}

    //@Test
    //@Order(10)
    //public void testExcluirEstudanteMatriculaCadastrada() {
    //    List<Estudante> listEstudante = estudanteService.listarEstudantes();
    //    if( ! listEstudante.isEmpty() ){
    //        Long IdEstudante = listEstudante.get(1).getIdEstudante();
    //        Boolean result = estudanteService.excluirEstudante(IdEstudante);
    //        Boolean resultExp = true;
    //        System.out.println("#10 Excluir estudante com matricula cadastrada");
    //        assertEquals(result, resultExp,"ERRO: #10 Nao Excluiu estudante com matricula cadastrada! ");
    //    } else {
    //        assertFalse(true,"#10 ERRO: Sem estudante para testar exclusao!");
    //    }
    //}

    public Long geraRandomico(){
        return random.nextLong(999999999);
    }
}