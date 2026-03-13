package org.springboot.mballem.controller;

import org.springboot.mballem.dao.AutorDAO;
import org.springboot.mballem.entity.Autor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
