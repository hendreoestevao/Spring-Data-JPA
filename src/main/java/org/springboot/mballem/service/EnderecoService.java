package org.springboot.mballem.service;

import org.springboot.mballem.entity.Endereco;
import org.springboot.mballem.repository.EnderecoRepository;
import org.springboot.mballem.specification.EnderecoSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
