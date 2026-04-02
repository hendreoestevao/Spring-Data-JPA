package org.springboot.mballem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "ENDERECOS")
@NamedQuery(name = "Endereco.buscarEnderecoPorAutorId", query = "select e from Endereco e where e.autor.id = ?1")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Long id;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "cidade", length = 55)
    private String cidade;

    @Column(name = "bairro", length = 55)
    private String bairro;

    @Column(name = "logradouro", length = 120)
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @OneToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
