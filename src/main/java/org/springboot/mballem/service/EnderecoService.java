package org.springboot.mballem.service;

import org.springboot.mballem.entity.Endereco;
import org.springboot.mballem.repository.EnderecoRepository;
import org.springboot.mballem.specification.EnderecoSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndCidade(String uf, String cidade) {
        Specification<Endereco> specification = Specification.where(
                EnderecoSpecifications.likeUf(uf).and(EnderecoSpecifications.likeCidade(cidade))
        );
        return  this.enderecoRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndLogradouro(String uf, String logradouro) {
        Specification<Endereco> specification = Specification.where(
                EnderecoSpecifications.likeUf(uf).and(EnderecoSpecifications.likeLogradouro(logradouro))
        );
        return  this.enderecoRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndCidades(String uf, List<String> cidades) {
        Specification<Endereco> specification = Specification.where(
                EnderecoSpecifications.likeUf(uf).and(EnderecoSpecifications.inCidades(cidades))
        );
        return this.enderecoRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByAutorNomeOrSobrenome(String nome, String sobrenome) {
        Specification<Endereco> specification = Specification.where(
                EnderecoSpecifications.likeAutorNome(nome).or(EnderecoSpecifications.likeAutorSobrenome(sobrenome))
        );
        return this.enderecoRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Optional<Endereco> findByAutorNomeAndSobrenome(String nome, String sobrenome) {
        return this.enderecoRepository.findOne(EnderecoSpecifications.likeAutorNomeAndAutorSobrenome(nome, sobrenome));
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByAutorTotalDePostsAndCidades(long total, List<String> cidades) {
        Specification<Endereco> specification = Specification.where(
                EnderecoSpecifications.inCidades(cidades).and(EnderecoSpecifications.byGreaterThanEqualToPosts(total))
        );
        return this.enderecoRepository.findAll(specification);
    }
}
