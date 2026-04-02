package org.springboot.mballem.service;

import org.springboot.mballem.dto.AutorInfoProjection;
import org.springboot.mballem.entity.Autor;
import org.springboot.mballem.entity.InfoAutor;
import org.springboot.mballem.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Autor> findById(Long id){
        return autorRepository.findById(id);
    }

    public Autor saveInfoAutor(InfoAutor infoAutor, Long autorId){
        Autor autor = findById(autorId).orElseThrow(() -> new RuntimeException("Autor not found"));
        autor.setInfoAutor(infoAutor);
        return autorRepository.save(autor);
    }

    public List<Autor> findAll(){
        return autorRepository.findAll();
    }

    public long getTotalElements() {
        return autorRepository.count();
    }

    public List<Autor> findByCargo(String cargo){
        return autorRepository.findByCargo("%" + cargo + "%");
    }

    public List<Autor> findByNomeOrSobrenome(String termo){
        return autorRepository.findByNomeOrSobrenome("%" + termo + "%");
    }

    public AutorInfoProjection findAutorInfoById(Long id){
        return autorRepository.findAutorInfoById(id);
    }


}
