package org.springboot.mballem.controller;

import org.springboot.mballem.dao.AutorDAO;
import org.springboot.mballem.entity.Autor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    private AutorDAO dao;

    public AutorController(AutorDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public Autor salvar(@RequestBody Autor autor) {
        dao.save(autor);
        return autor;
    }

    @PutMapping
    public Autor atualizar(@RequestBody Autor autor) {
        dao.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id) {
        dao.delete(id);
        return "Deletado com sucesso";
    }

    @GetMapping("{id}")
    public Autor buscarPorId(@PathVariable Long id) {
        return dao.findById(id);
    }

    @GetMapping()
    public List<Autor> buscarTodosAutores() {
        return dao.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> buscarPorNomeOuSobrenome(@RequestParam String args) {
        return dao.findByNomeOrSobrenome(args);
    }

}
