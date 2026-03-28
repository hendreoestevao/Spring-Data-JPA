package org.springboot.mballem.specification;

import org.springboot.mballem.entity.Endereco;
import org.springframework.data.jpa.domain.Specification;

public class EnderecoSpecifications {

    public static Specification<Endereco> likeUf(String uf) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("uf"), uf)
        );
    }

    public static Specification<Endereco> likeCidade(String cidade) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("cidade"), cidade)
        );
    }

    public static Specification<Endereco> likeLogradouro(String logradouro) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("logradouro"), "%" + logradouro + "%")
        );
    }
}
