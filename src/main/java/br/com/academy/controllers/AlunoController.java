package br.com.academy.controllers;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunorepositorio;

    @GetMapping("/inserirAlunos")
    public ModelAndView InsertAlunos(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/formAluno");
        mv.addObject("alunoK", new Aluno());
        return mv;
    }

    @PostMapping("InsertAlunos")
    public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br){
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()){
            mv.setViewName("Aluno/formAluno");
            mv.addObject("alunoK", aluno);

            List<String> msg = new ArrayList<String>();
            for (ObjectError objectError : br.getAllErrors()){
                msg.add(objectError.getDefaultMessage());// é todas as mensagens de erro que vem da minha classe Model
            }
            mv.addObject("msg", msg);
            return mv;}
        else{
        mv.setViewName("redirect:/alunos-adicionados");
        alunorepositorio.save(aluno);
            return mv;}
    }

    @GetMapping("alunos-adicionados")
    public ModelAndView listagemAlunos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/listAlunos");
        mv.addObject("alunosList", alunorepositorio.findAll());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alterar");
        Aluno aluno = alunorepositorio.getReferenceById(id);
        mv.addObject("alunoB", aluno);
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        alunorepositorio.save(aluno);
        mv.setViewName("redirect:/alunos-adicionados");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Integer id){
        alunorepositorio.deleteById(id);
        return "redirect:/alunos-adicionados";
    }

    @GetMapping("filtro-alunos")
    public ModelAndView filtroAlunos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/filtroAlunos");
        mv.addObject("alunoK", new Aluno());
        return mv;
    }

    @GetMapping("alunos-ativos")
    public ModelAndView listaAlunosAtivos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-ativos");
        mv.addObject("alunosAtivosU", alunorepositorio.findByStatusAtivos());
        return mv;
    }

    @GetMapping("alunos-inativos")
    public ModelAndView listaAlunosInativos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-inativos");
        mv.addObject("alunosInativosV", alunorepositorio.findByStatusInativo());
        return mv;
    }

    @GetMapping("alunos-trancados")
    public ModelAndView listaAlunosTrancados(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-trancados");
        mv.addObject("alunosTrancadosX", alunorepositorio.findByStatusTrancados());
        return mv;
    }

    @GetMapping("alunos-cancelados")
    public ModelAndView listaAlunosCancelados(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-cancelados");
        mv.addObject("alunosCanceladosY", alunorepositorio.findByStatusCancelados());
        return mv;
    }

    @PostMapping("pesquisar-aluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome){
        ModelAndView mv = new ModelAndView();
        List<Aluno> listaAlunos;
        if(nome == null || nome.trim().isEmpty()){
            listaAlunos = alunorepositorio.findAll();
        }else {
            listaAlunos = alunorepositorio.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("listaDeAlunoResultado", listaAlunos);
        mv.setViewName("Aluno/pesquisa-resultado");
        return mv;

    }

}
