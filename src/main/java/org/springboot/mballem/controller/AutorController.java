package org.springboot.mballem.controller;

import org.springboot.mballem.dao.AutorDAO;
import org.springboot.mballem.dto.AutorInfoDTO;
import org.springboot.mballem.entity.Autor;
import org.springboot.mballem.entity.InfoAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springboot.mballem.service.AutorService;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    private AutorDAO dao;

    private AutorService autorService;

    public AutorController(AutorDAO dao, AutorService autorService) {
        this.dao = dao;
        this.autorService = autorService;
    }

    @PostMapping
    public Autor salvar(@RequestBody Autor autor) {
        autorService.save(autor);
        return autor;
    }

    @PutMapping
    public Autor atualizar(@RequestBody Autor autor) {
        autorService.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id) {
        autorService.delete(id);
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

    @GetMapping("total")
    public Long buscarTotalElements() {
        return dao.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        return dao.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("cargo")
    public List<Autor> getAutorPorCargo(@RequestParam String cargo) {
        return dao.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoDTO getInfoAutor(@RequestParam Long id) {
        return dao.findAutorInfoById(id);
    }
}
