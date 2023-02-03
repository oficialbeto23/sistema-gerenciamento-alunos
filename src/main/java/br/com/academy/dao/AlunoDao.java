package br.com.academy.dao;

import br.com.academy.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {

    @Query("select j from Aluno j where j.status = 'ATIVO'")
    public List<Aluno> findByStatusAtivos();

    @Query("select j from Aluno j where j.status = 'INATIVO'")
    public List<Aluno> findByStatusInativo();

    @Query("select j from Aluno j where j.status = 'TRANCADO'")
    public List<Aluno> findByStatusTrancados();

    @Query("select j from Aluno j where j.status = 'CANCELADO'")
    public List<Aluno> findByStatusCancelados();

    public List<Aluno> findByNomeContainingIgnoreCase(String nome);

}
