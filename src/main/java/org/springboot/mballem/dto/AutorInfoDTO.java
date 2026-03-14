package org.springboot.mballem.dto;


public class AutorInfoDTO {

    private String nomeCompleto;
    private String cargo;
    private String bio;


    public AutorInfoDTO(String nome, String sobrenome, String cargo, String bio) {
        this.nomeCompleto = nome + " " + sobrenome;
        this.cargo = cargo;
        this.bio = bio;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public String getBio() {
        return bio;
    }
}
