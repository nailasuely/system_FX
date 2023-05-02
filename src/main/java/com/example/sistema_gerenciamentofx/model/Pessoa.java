package com.example.sistema_gerenciamentofx.model;

import java.io.Serializable;

/**
 * Classe abstrata utilizada para melhor modularizar o codigo, ja que os elementos dela se compartilham nas classes <b>Cliente</b> e <b>Tecnico</b>
 *
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public abstract class Pessoa implements Serializable {
    /**
     * O atributo <b>id</b> serve para salvar a identificação interna daquela pessoa, seja um tecnico ou cliente<br>
     * Cada pessoa possui um id gerado aleatoriamente pela biblioteca <i>UUID</i><br>
     * Utilizado para tarefas:
     * <ul>
     *     <li>
     *         procurar no banco de dados
     *     </li>
     *     <li>
     *         identificação unica daquela pessoa
     *     </li>
     * </ul>
     */
    private String id;
    /**
     * O atributo <b>fullName</b> serve para salvar o nome completo da pessoa
     */
    private String fullName;
    /**
     * O atributo <b>adress</b> serve para salvar o endereço da pessoa
     */
    private String address;
    /**
     * O atributo <b>cpf</b> serve para salvar o numero do cpf da pessoa<br>
     * Utilizado para algumas tarefas:
     * <ul>
     *     <li>procurar na base de dados</li>
     *     <li>realizar login no sistema</li>
     *     <li>identificar a pessoa</li>
     * </ul>
     */
    private String cpf;
    /**
     * O atributo <b>telephone</b> serve para salvar o numero de contato telefonico da pessoa
     */
    private int telephone;

    // esse aqui é construtor apenas para nao resultar em erro para quando ocorrer a criação de uma
    // classe sem atributos

    /**
     * Construtor padrão/default da classe <b>Pessoa</b><br>
     * <b>Uso:</b><br>
     * Pessoa pessoa = new Pessoa();<br>
     */
    public Pessoa(){}

    /**
     * Construtor sobrecarregado da Classe Pessoa<br>
     * <b>Uso:</b><br>
     * Pessoa pessoa = new Pessoa("Naila Suele", "Rua Maria Quiteria, Nº100, Bairro: Papagaio, Feira de Santana-BA-BR", "00000000000", "11111111111")
     * @param nome String para armazenar o nome completo da pessoa
     * @param address String para armazenar o endereço da pessoa
     * @param cpf String para armazenar o cpf da pessoa
     * @param telephone String para armazenar o telefone da pessoa
     */
    public Pessoa(String nome, String address, String cpf, int telephone) {
        this.fullName = nome;
        this.address = address;
        this.cpf = cpf;
        this.telephone = telephone;
    }

    /**
     * Método para poder retornar o valor de ID seja do CLiente, ou Tecnico, a depender de onde esta sendo referenciado
     * @return String contendo valor do ID que foi gerado no cadastro
     */
    public String getId() {
        return id;
    }

    /**
     * Serve para preencher com um valor de ID<br>
     * O proprio sistema preenche aleatoriamente no momento do cadastro
     * @param id Valor de ID para preencher o atributo
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método para poder ter acesso ao nome completo da Pessoa
     * @return String contendo o valor do nome que foi passado no cadastro
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Serve para preencher com o nome da Pessoa
     * @param fullName String contendo o nome da pessoa
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Método para poder ter acesso ao endereço da Pessoa
     * @return String contendo o endereço passado no cadastro
     */
    public String getAddress() {
        return address;
    }

    /**
     * Serve para preencher com o endereço da Pessoa
     * @param address String contendo o endereço da pessoa
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Método para poder ter acesso ao cpf da Pessoa
     * @return String contendo os números do CPF da pessoa
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Serve para preencher com o CPF da Pessoa
     * @param cpf String contendo os números do CPF da pessoa
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Método para poder ter acesso ao telefone da Pessoa
     * @return String contendo o número de telefone da pessoa
     */
    public int getTelephone() {
        return telephone;
    }

    /**
     * Serve para preencher com o telefone da Pessoa
     * @param telephone String contendo o número de telefone da pessoa
     */
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    /**
     * Método para obter uma impressão personalizada ao utilizar o comando <i>System.out.println(pessoa);</i> em que "pessoa" é uma instância da classe Pessoa
     * @return String contendo as informações presentes nos atributos da classe
     */
    @Override
    public String toString() {
        return "Pessoa\n" +
                "id: " + id + '\n' +
                "nome: " + fullName + '\n' +
                "endereço: " + address + '\n' +
                "CPF: " + cpf + '\n' +
                "telefone: " + telephone + '\n';
    }
}
