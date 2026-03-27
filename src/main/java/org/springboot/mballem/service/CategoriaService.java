package org.springboot.mballem.service;

import org.springboot.mballem.entity.Categoria;
import org.springboot.mballem.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {


    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public List<Categoria> save(List<Categoria> categorias) {
        return categoriaRepository.saveAll(categorias);
    }

    @Transactional(readOnly = true)
    public Categoria findByTitulo(String titulo) {
        return categoriaRepository.findByTitulo(titulo).orElseGet(Categoria::new);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findByInicioTitulo(String titulo) {
        return this.categoriaRepository.findByTituloStartsWith(titulo);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findByTitulos(List<String> titulos) {
        return this.categoriaRepository.findByTituloIn(titulos);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAllOrderByTitulosAsc() {
        return this.categoriaRepository.findByOrderByTituloAsc();
    }
}
