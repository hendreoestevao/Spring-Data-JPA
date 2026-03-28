package org.springboot.mballem.controller;

import org.springboot.mballem.entity.Post;
import org.springboot.mballem.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post salvar(@RequestBody Post post) {
        return this.postService.saveFor(post);
    }

    @GetMapping("categoria/{categoria}/autor/{autorId}")
    public List<Post> getByCategoriaAndAutor(@PathVariable String categoria, @PathVariable Long autorId) {
        return this.postService.findAllByCategoriaAndAutorId(categoria, autorId);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public List<Post> getByAutor(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.postService.findAllByAutor(nome, sobrenome);
    }

    @GetMapping("titulo/{titulo}")
    public List<Post> getByTitulo(@PathVariable String titulo) {
        return this.postService.findAllByTitulo(titulo);
    }

    @GetMapping("data-publicacao/{data}")
    public List<Post> getByDataPublicacao(@PathVariable LocalDate data) {
        return this.postService.findAllByDataPublicacaoMaiorOuIgual(data);
    }

    @GetMapping("sem-data-publicacao")
    public List<Post> getBySemDataPublicacao() {
        return this.postService.findAllBySemDataPublicacao();
    }

    @GetMapping("all")
    public Page<Post> getAllPagination(@PageableDefault(page = 0, size = 10, sort = "dataPublicacao", direction = Sort.Direction.DESC) Pageable pageable) {
        return this.postService.findAllPagination(pageable);
    }

    @GetMapping("ano/{ano}")
    public Page<Post> getAllPagination(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "dataPublicacao") String sort,
                                       @RequestParam(defaultValue = "desc") String direction,
                                       @PathVariable int ano) {
        return this.postService.findAllByAno(ano, page, size, sort, direction);
    }


}
