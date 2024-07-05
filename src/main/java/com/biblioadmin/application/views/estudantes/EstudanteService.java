package com.biblioadmin.application.views.estudantes;

import com.biblioadmin.application.data.entity.Estudante;
import com.biblioadmin.application.data.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {

    @Autowired
    EstudanteRepository estudanteRepository;

    @Value("${biblio.registros.pag}")
    private int regPaginas;

    private Pageable pageable;


    public Long incluirEstudante(Estudante estudante){
        return estudanteRepository.save(estudante).getId();
    }

    public boolean alterarEstudante(Estudante estudante){

        if( estudante.getNome() == null || estudante.getMatricula() == null ||
                estudante.getEmail() == null){
            return false;
        }
        Estudante estudBD = estudanteRepository.getReferenceById(estudante.getId());
        if(estudBD != null){
            estudBD.setNome(estudante.getNome());
            estudBD.setEmail(estudante.getEmail());
            estudBD.setMatricula(estudante.getMatricula());
            estudBD.setTelefone(estudante.getTelefone());
            estudanteRepository.save(estudBD);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        estudanteRepository.deleteById(id);
    }
    public boolean excluirEstudantePorMatricula(Long matricula){
        if(estudanteRepository.findByMatricula(matricula).isPresent()){
            estudanteRepository.deleteByMatricula(matricula);
            return true;
        }
        return false;
    }

    public Estudante consultaEstudantePorId(Long IdEstudante){
        return estudanteRepository.findById(IdEstudante).get();
    }

    public Estudante consultaEstudantePorMatricula(Long matricula){
        return estudanteRepository.findByMatricula(matricula).get();
    }

    public Estudante consultaEstudantePorEmail(String email){
        return estudanteRepository.findByEmail(email).get();
    }

    public List<Estudante> consultaEstudantePorNome(String nome){

        if(nome == null || nome.equalsIgnoreCase("")) { return null; }
        return estudanteRepository.findByNome(nome.replaceAll("_", " "));
    }

    public List<Estudante> listarTodos() {
        return estudanteRepository.findAll();
    }
    public Estudante salvar(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }
    public List<Estudante> listarEstudante(Integer pagina){
        if(pagina == null || pagina == 0) { pagina = 1; }
        pagina = (pagina -1);
        Pageable pagsort = PageRequest.of(pagina,regPaginas,Sort.by("nome").ascending());
        List<Estudante> estudantes = estudanteRepository.findAll(pagsort).getContent();
        if(estudantes.isEmpty()){
            return null;
        } else {
            return estudantes;
        }
    }

    public boolean excluirEstudantePorId(Long IdEstudante){

        if(estudanteRepository.findById(IdEstudante).isPresent()){
            estudanteRepository.deleteById(IdEstudante);
            return true;
        }
        return false;
    }
}
