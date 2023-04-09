package com.example.sistema_gerenciamentofx.model;

/**
 * Subclasse que herda atributos e metodos da classe Pessoa<br>
 * Utilizada para separar o cadastro de Tecnico e Cliente no sistema, e no armazenamento dos dados
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class Cliente extends Pessoa {
    /**
     * Declaração do método construtor default da classe
     * Uso:<br>
     * Cliente cliente = new Cliente();
     */
    public Cliente(){}

    /**
     * Declaração do construtor sobrecarrecado da classe
     * <b>Uso:</b><br>
     * Cliente cliente = new Cliente("Naila Suele", "Rua Maria Quiteria, Nº100, Bairro: Papagaio, Feira de Santana-BA-BR", "00000000000", "11111111111")
     * @param nome String para armazenar o nome completo da pessoa
     * @param address String para armazenar o endereço da pessoa
     * @param cpf String para armazenar o cpf da pessoa
     * @param telephone String para armazenar o telefone da pessoa
     */

    public Cliente(String nome, String address, String cpf, int telephone) {
        super(nome, address, cpf, telephone);
    }


}
