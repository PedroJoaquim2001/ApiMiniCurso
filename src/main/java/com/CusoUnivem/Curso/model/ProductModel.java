package com.CusoUnivem.Curso.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="TB")
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "email")
    private UserModel email;

    @Column(nullable = false, unique = true)
    private String nomeProduto;

    @Column(nullable = false)
    private double preco;



    public ProductModel(){}
    public ProductModel(String nomeProduto, double preco, UserModel email) {
        this.email = email;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserModel getEmail() {
        return email;
    }

    public void setEmail(UserModel email) {
        this.email = email;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
