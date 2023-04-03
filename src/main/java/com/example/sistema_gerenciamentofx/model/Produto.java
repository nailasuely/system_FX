package com.example.sistema_gerenciamentofx.model;

public class Produto {
    private String nome;
    private double preco;
    public Produto() {
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public static Produto novaRam() {
        return new Produto("ram", 20.0);
    }

    public static Produto novaPlacaMae() {
        return new Produto("placa mãe", 100.0);
    }

    public static Produto novaFonte() {
        return new Produto("fonte", 30.0);
    }

    public static Produto novaPlacaDeVideo() {
        return new Produto("placa de video", 100.0);
    }

    public static Produto novoHDSSD() {
        return new Produto("hd/ssd", 30.0);
    }

}
