package org.springboot.mballem.controller;

import org.springboot.mballem.entity.InfoAutor;
import org.springboot.mballem.service.InfoAutorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("info")
public class InfoAutorController {

    private final InfoAutorService autorService;

    public InfoAutorController(InfoAutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("{id}")
    public InfoAutor getInfoAutor(@PathVariable Long id) {
        return this.autorService.findById(id);
    }

    @GetMapping("cargo/{cargo}")
    public List<InfoAutor> getContainsCargo(@PathVariable String cargo) {
        return this.autorService.findAllContainsCargo(cargo);
    }

    @GetMapping("cargo/{cargo}/empresa/{empresa}")
    public List<InfoAutor> getContainsCargoAndEmpresa(@PathVariable String cargo, @PathVariable String empresa) {
        return this.autorService.findAllCargoAndEmpresa(cargo, empresa);
    }

    @GetMapping("bio/{bio}")
    public InfoAutor getFromBio(@PathVariable String bio) {
        return this.autorService.findFromBio(bio);
    }
}
