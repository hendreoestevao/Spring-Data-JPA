package org.springboot.mballem.controller;

import org.springboot.mballem.entity.Categoria;
import org.springboot.mballem.service.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public List<Categoria> salvar(@RequestBody List<Categoria> categorias) {
        return this.categoriaService.save(categorias);
    }

    @GetMapping("titulo/{titulo}")
    public Categoria buscarPorTitulo(@PathVariable String titulo) {
        return this.categoriaService.findByTitulo(titulo);
    }

    @GetMapping("tituloInicio/{tituloInicio}")
    public List<Categoria> buscarPorTituloInicio(@PathVariable String tituloInicio) {
        return this.categoriaService.findByInicioTitulo(tituloInicio);
    }

    @GetMapping("titulos")
    public List<Categoria> buscarPorTitulos(@RequestParam(name = "t") List<String> titulos) {
        return this.categoriaService.findByTitulos(titulos);
    }

    @GetMapping()
    public List<Categoria> buscarCategoriasOrdenadasAsc() {
        return this.categoriaService.findAllOrderByTitulosAsc();
    }

}
