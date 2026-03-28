package org.springboot.mballem.service;

import org.springboot.mballem.entity.Autor;
import org.springboot.mballem.entity.Categoria;
import org.springboot.mballem.entity.Post;
import org.springboot.mballem.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final AutorService autorService;

    private final CategoriaService categoriaService;

    public PostService(PostRepository postRepository, AutorService autorService, CategoriaService categoriaService) {
        this.postRepository = postRepository;
        this.autorService = autorService;
        this.categoriaService = categoriaService;
    }

    @Transactional
    public Post save(Post post) {
        Autor autor = this.autorService.findById(post.getAutor().getId()).get();
        post.setAutor(autor);

        List<String> titulos = post.getCategorias().stream().map(
                t -> t.getTitulo()
        ).toList();

        List<Categoria> categorias = this.categoriaService.findByTitulos(titulos);
        post.setCategorias(categorias);
        return postRepository.save(post);
    }

    @Transactional
    public Post saveFor(Post post) {
        Autor autor = this.autorService.findById(post.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        post.setAutor(autor);

        List<String> titulos = new ArrayList<>();

        for (Categoria categoria : post.getCategorias()) {
            titulos.add(categoria.getTitulo());
        }

        List<Categoria> categorias = this.categoriaService.findByTitulos(titulos);
        post.setCategorias(categorias);

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByCategoriaAndAutorId(String categoria, Long autorId) {
        return this.postRepository.findByCategoriasTituloAndAutorId(categoria, autorId);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByAutor(String autorNome, String autorSobrenome) {
        return this.postRepository.findByAutorNomeOrAutorSobrenome(autorNome, autorSobrenome);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByTitulo(String titulo) {
        return this.postRepository.findByTituloContainsOrderByAutorNomeAsc(titulo);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByDataPublicacaoMaiorOuIgual(LocalDate date) {
        return this.postRepository.findByDataPublicacaoIsGreaterThanEqual(date);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllBySemDataPublicacao() {
        return this.postRepository.findByDataPublicacaoIsNull();
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllPagination(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByAno(int ano, int page, int size, String sort, String direction) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return this.postRepository.findByAno(ano, pageRequest);
    }

}
