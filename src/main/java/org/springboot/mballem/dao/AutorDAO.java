package org.springboot.mballem.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springboot.mballem.dto.AutorInfoDTO;
import org.springboot.mballem.entity.Autor;
import org.springboot.mballem.entity.InfoAutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AutorDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public void save(Autor autor) {
        manager.persist(autor);
    }


    @Transactional
    public void update(Autor autor) {
        manager.merge(autor);
    }

    @Transactional
    public void delete(Long id) {
        manager.remove(manager.getReference(Autor.class, id));
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id) {
        return manager.find(Autor.class, id);
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        String query = "select a from Autor a";
        return manager.createQuery(query, Autor.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Autor> findByNomeOrSobrenome(String args) {
        String query = "select a from Autor a where a.nome like :args OR a.sobrenome like :args";
        return manager.createQuery(query, Autor.class)
                .setParameter("args", "%" + args + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Long getTotalElements() {
        String query = "select count(1) from Autor a ";
        return manager.createQuery(query, Long.class).getSingleResult();
    }

    @Transactional()
    public Autor saveInfoAutor(InfoAutor infoAutor, Long autorId) {
        Autor autor = this.findById(autorId);
        autor.setInfoAutor(infoAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo) {
        String query = "select a from Autor a where a.infoAutor.cargo like :cargo order by a.nome ASC";
        return manager.createQuery(query, Autor.class)
                .setParameter("cargo", "%" + cargo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public AutorInfoDTO findAutorInfoById(Long id) {
        String query = "select new org.springboot.mballem.dto.AutorInfoDTO(a.nome, a.sobrenome, a.infoAutor.cargo, a.infoAutor.bio)" +
                " from Autor a where a.id = :id ";


        return manager.createQuery(query, AutorInfoDTO.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
