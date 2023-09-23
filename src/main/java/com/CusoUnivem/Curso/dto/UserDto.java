package com.CusoUnivem.Curso.dto;


import com.CusoUnivem.Curso.model.UserModel;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Length;




public class UserDto {
    @NotNull @Length(min = 1,max = 50)
    private String email;

    @NotNull @Length(min = 1,max = 12)
    private String senha;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserModel convert(){
        return new UserModel(email, senha);
    }
}
