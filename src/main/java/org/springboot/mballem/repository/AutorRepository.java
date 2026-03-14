package org.springboot.mballem.repository;

import org.springboot.mballem.dto.AutorInfoDTO;
import org.springboot.mballem.dto.AutorInfoProjection;
import org.springboot.mballem.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("select a from Autor a where a.infoAutor.cargo like :cargo order by a.nome ASC")
    List<Autor> findByCargo(String cargo);

    @Query("select a from Autor a where a.nome like :termo OR a.sobrenome like :termo")
    List<Autor> findByNomeOrSobrenome(String termo);

    @Query("select a.nome as nome, a.sobrenome as sobrenome, a.infoAutor.cargo as cargo, a.infoAutor.bio as bio" +
            " from Autor a where a.id = :id ")
    AutorInfoProjection findAutorInfoById(Long id);
}
