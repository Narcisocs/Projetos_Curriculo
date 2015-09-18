package br.com.narcisocsales.trabalhoopcional2.model;

/**
 * Created by narciso on 01/09/15.
 */
public class Carros {

    private Long id;
    private String nome;
    private String marca;
    private String ano;

    public Carros(Long id, String nome, String marca, String ano) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca(){
        return marca;
    }

    public String getAno() {
        return ano;
    }
}

