package org.springboot.mballem.dto;

import org.springframework.beans.factory.annotation.Value;

public interface AutorInfoProjection {

    @Value("#{target.nome + ' ' + target.sobrenome}")
    String getNomeCompleto();

    @Value("#{target.cargo}")
    String getCargo();

    @Value("#{target.bio}")
    String getBio();
}
