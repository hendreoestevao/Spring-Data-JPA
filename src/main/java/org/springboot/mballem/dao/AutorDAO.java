package org.springboot.mballem.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springboot.mballem.entity.Autor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AutorDAO {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public void save(Autor autor) {
        manager.persist(autor);
    }
}
