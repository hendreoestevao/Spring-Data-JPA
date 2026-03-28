package org.springboot.mballem.controller;

import org.springboot.mballem.entity.Endereco;
import org.springboot.mballem.service.EnderecoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("uf/{uf}/logradouro/{logradouro}")
    public List<Endereco> getByUfAndLogradouro(@PathVariable String uf, @PathVariable String logradouro) {
        return this.enderecoService.findByUfAndLogradouro(uf, logradouro);
    }

    @GetMapping("uf/{uf}/cidades")
    public List<Endereco> getByUfAndCidades(@PathVariable String uf, @RequestParam(name = "nomes") List<String> cidades) {
        return this.enderecoService.findByUfAndCidades(uf, cidades);
    }

    @GetMapping("autores/nome/{nome}/sobrenome/{sobrenome}")
    public List<Endereco> getByAutoresNomeOrSobrenome(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.enderecoService.findByAutorNomeOrSobrenome(nome, sobrenome);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public Endereco getByAutoresNomeAndSobrenome(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.enderecoService.findByAutorNomeAndSobrenome(nome, sobrenome).orElseGet(Endereco::new);
    }

    @GetMapping("autores/total-posts")
    public List<Endereco> getByAutoresTotalDePostsPorCudades(@RequestParam long total, @RequestParam List<String> cidades) {
        return this.enderecoService.findByAutorTotalDePostsAndCidades(total, cidades);
    }
}
