package org.springboot.mballem.controller;

import org.springboot.mballem.entity.Endereco;
import org.springboot.mballem.service.EnderecoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco) {
        return this.enderecoService.save(endereco);
    }

    @GetMapping("uf/{uf}/cidade/{cidade}")
    public List<Endereco> getByUfAndCidade(@PathVariable String uf, @PathVariable String cidade) {
        return this.enderecoService.findByUfAndCidade(uf, cidade);
    }
}
