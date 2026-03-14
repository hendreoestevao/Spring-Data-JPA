package org.springboot.mballem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "info_autores")
public class InfoAutor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info", nullable = false)
    private Long id;

    @Column(name = "cargo", length = 45, nullable = false)
    private String cargo;

    @Column(name = "bio")
    private String bio;

    public InfoAutor() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InfoAutor infoAutor)) return false;
        return Objects.equals(getId(), infoAutor.getId()) && Objects.equals(getCargo(), infoAutor.getCargo()) && Objects.equals(getBio(), infoAutor.getBio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCargo(), getBio());
    }
}
