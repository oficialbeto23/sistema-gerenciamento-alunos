package br.com.academy.model;



import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tb_aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nome")
    @Size(min = 5, max = 35, message = "(Campo Nome) O nome deve conter no mínimo 5 caracter")
    @NotBlank( message = "(Campo Nome)O campo não pode ser vazio. ")
    private String nome;
    @Column(name = "curso")
    @Enumerated(EnumType.STRING)
    @NotNull( message = "(Campo Curso) O campo não pode ser nulo")
    private Curso curso;
    @Column(name = "matricula")
    @NotBlank( message = "(Campo Matricula) Clique no Botão Gerar! ")
    private String matricula;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "(Campo Status) O campo status não pode ser nulo.")
    private Status status;

    @NotBlank(message = "(Campo Turno) O turno não pode ser vazio.")
    @Size(min = 4, message = "(Campo Turno) No mínimo 4 caracteres.")
    private String turno;

    public Curso getCurso() {
        return curso;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }



    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
