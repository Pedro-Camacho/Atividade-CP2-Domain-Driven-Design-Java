package com.fiap.restapi.service;

import com.fiap.restapi.model.Aluno;
import com.fiap.restapi.model.Professor;
import com.fiap.restapi.repository.AlunoRepository;
import com.fiap.restapi.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository repo;

    public ProfessorService(ProfessorRepository repo){
        this.repo = repo;
    }

    public Professor adicionar(String nome, String departamento){
        validar(nome, departamento);
        Professor professor = new Professor(null, nome.trim(), departamento.trim());
        return repo.adicionar(professor);
    }

    public Optional<Professor> buscarPorId(Long id){
        if(id == null || id <= 0) throw new IllegalArgumentException("Id inválido");
        return repo.buscarPorId(id);
    }

    public List<Professor> listar(){
        return repo.listar();
    }

    public Optional<Professor> atualizar(Long id, String nome, String departamento){
        if(id == null || id <= 0) throw new IllegalArgumentException("Id inválido");
        validar(nome, departamento);
        return repo.atualizar(id, new Professor(id, nome.trim(), departamento.trim()));
    }

    public boolean deletar(Long id){
        if(id == null || id <= 0) throw new IllegalArgumentException("Id inválido");
        return repo.deletar(id);
    }

    private void validar(String nome, String departamento){
        if(nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        if(departamento == null || departamento.isBlank()) throw new IllegalArgumentException("departamento é obrigatório");
        if(nome.length() > 150) throw new IllegalArgumentException("Nome excede 150 caracteres");
        if(departamento.length() > 150) throw new IllegalArgumentException("departamento excede 150 caracteres");
    }
}
