package com.CusoUnivem.Curso.dto;


import com.CusoUnivem.Curso.model.UserModel;
import com.CusoUnivem.Curso.service.UserService;
import jakarta.validation.constraints.NotNull;
import com.CusoUnivem.Curso.model.ProductModel;
import org.hibernate.validator.constraints.Length;

public class ProductDto {
    @NotNull @Length(min = 1,max = 100)
    private String nomeProduto;
    @NotNull
    private double preco;

    @NotNull
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProductModel convert(UserService userService){
        UserModel userModel = userService.findByEmail(email).get();
        return new ProductModel(nomeProduto, preco, userModel);
    }

}
