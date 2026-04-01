package org.springboot.mballem.repository;

import org.springboot.mballem.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>, JpaSpecificationExecutor<Endereco> {

    @Modifying
    @Query("update Endereco e " +
            "set e.bairro = :bairro, e.logradouro = :logradouro, e.numero = :numero where e.id = :id")
    int updateByBairroAndLogradouroAndNumero(Long id, String bairro, String logradouro, int numero);

    @Query(nativeQuery = true, value = "select * from enderecos where uf like :uf")
    List<Endereco> findEnderecosByUf(String uf);

    @Procedure(procedureName = "atualizar_numero_endereco", outputParameterName = "p_resultado")
    String updateNumeroEndereco(@Param("p_id") Long id, @Param("p_novo_numero") int numero);
}
