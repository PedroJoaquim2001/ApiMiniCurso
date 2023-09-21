package dto;


import jakarta.validation.constraints.NotNull;
import model.ProductModel;
import model.UserModel;
import org.hibernate.validator.constraints.Length;

public class ProductDto {
    @NotNull @Length(min = 1,max = 100)
    private String nomeProduto;
    @NotNull
    private double preco;

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

    public ProductModel convert(){
        return new ProductModel(nomeProduto, preco);
    }

}
