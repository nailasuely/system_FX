package com.example.sistema_gerenciamentofx.model;

public abstract class Pessoa {
    private String id;
    private String fullName;
    private String address;
    private String cpf;
    private int telephone;

    // esse aqui é construtor apenas para nao resultar em erro para quando ocorrer a criação de uma
    // classe sem atributos
    public Pessoa(){}
    public Pessoa(String nome, String address, String cpf, int telephone) {
        this.fullName = nome;
        this.address = address;
        this.cpf = cpf;
        this.telephone = telephone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Pessoa [\n" +
                "id='" + id + '\n' +
                ", nome=" + fullName + '\n' +

                ", endereço='" + address + '\n' +
                ", CPF='" + cpf + '\n' +
                ", telefone='" + telephone + '\n' +
                ']';
    }
}
