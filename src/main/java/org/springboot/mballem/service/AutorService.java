package org.springboot.mballem.service;

import org.springboot.mballem.entity.Autor;
import org.springboot.mballem.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    public void save(Autor autor){
        autorRepository.save(autor);
    }

    public void update(Autor autor){
        autorRepository.save(autor);
    }

    public void delete(Long id){
        autorRepository.deleteById(id);
    }
}
