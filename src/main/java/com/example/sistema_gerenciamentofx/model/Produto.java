package com.example.sistema_gerenciamentofx.model;

import java.io.Serializable;

/**
 *Está classe serve para poder gerar objetos dela mesma, em que cada um desses contém um valor do tipo <i>String</i>,
 * utilizado para dar nome a peça, ou serviço, além de um valor do tipo <i>double</i> que serve para poder preencher com o valor da peça
 * ou com o valor do serviço
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class Produto implements Serializable {
    /**
     * O atributo <b>nome</b>, do tipo <i>String</i> serve para poder armazenar o nome do serviço, ou nome de uma peça.
     * Em que "nome de serviço", aparece outras vezes nesse sistema como: "tipo de serviço"
     */
    private String nome;
    /**
     * O atributo <b>preco</b>, do tipo <i>double</i>, serve para armazenar o preço do produto, ou preço do serviço contratado
     */
    private double preco;

    /**
     * Metodo construtor padrão/default da classe <b>Produto</b><br>
     * <b>Uso:</b><br>
     * Produto produto = new Produto();
     */
    public Produto() {
    }

    /**
     * Metodo construtor sobrecarregado da classe <b>Produto</b>
     * <b>Uso:</b><br>
     * Produto produto = new Produto("Memoria RAM", 30);
     * @param nome <i>String</i> contendo o nome da peça/produto, ou do serviço
     * @param preco <i>Double</i> contendo o preço daquela peça/produto, ou de um serviço
     */
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Método para poder obter a informação de preço do atributo <b>preço</b>
     * @return <i>Double</i> contendo um preço daquele serviço, ou produto/peça
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Método para obter o nome salvo no atributo <b>nome</b>
     * @return <i>String</i> contendo o nome daquele serviço, ou produto/peça
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações do produto <b>memória ram</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do produto <b>memória ram</b>
     */
    public static Produto novaRam() {
        return new Produto("ram", 20.0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações do produto <b>placa-mãe</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do produto <b>placa-mãe</b>
     */
    public static Produto novaPlacaMae() {
        return new Produto("placa mae", 100.0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações do produto <b>fonte</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do produto <b>fonte</b>
     */
    public static Produto novaFonte() {
        return new Produto("fonte", 30.0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações do produto <b>placa de Video</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do produto <b>placa de Video</b>
     */
    public static Produto novaPlacaDeVideo() {
        return new Produto("placa de video", 100.0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações do produto <b>Hdd/ssd</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do produto <b>hdd/ssd</b>
     */
    public static Produto novoHDSSD() {
        return new Produto("hd/ssd", 30.0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações sobre o serviço de <b>montagem</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do serviço de <b>montagem</b>.<br>
     * Nesse caso relativo à montagem, não há valor, devido o preço ser preenchido pelas peças que serão montadas
     */
    // aqui são os serviços
    public static Produto servicoMontagem() {
        return new Produto("montagem", 0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações sobre o serviço de <b>formatação</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do serviço de <b>formatação</b>
     */
    public static Produto servicoFormatar() {
        return new Produto("formatacao", 50.0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações sobre o serviço de <b>instalação de programas</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do serviço de <b>instação de programas</b>
     */
    public static Produto servicoInstalar() {
        return new Produto("instalacao", 10.0);
    }
    /**
     * Método que "cria", ou gera, um objeto do tipo <i>Produto</i> contendo ja as informações sobre o serviço de <b>limpeza</b>
     * @return Objeto do tipo <i>Produto</i> com o valor do serviço de <b>limpeza</b>
     */
    public static Produto servicoLimpeza() {
        return new Produto("limpeza", 70.0);
    }

    /**
     * Método sobrescrito ao originario <i>toString</i> para poder obter uma saída personalizada, ao solicitar um <i>System.out.println();</i>
     * de um objeto da classe <i>produto</i>
     * Uso:<br>
     * Produto produto;
     * produto = Produto.servicoInstalar();
     * System.out.println(produto);
     * >>>Produto/Serviço [<br>
     * >>>nome= instação<br>
     * >>>preço= 10.0<br>
     * >>>]<br>
     * @return <i>String</i> já formatada contendo o informações do nome e preço daquele objeto do tipo <i>Produto</i>
     */
    @Override
    public String toString() {

        return "Produto/Serviço [\n" +
                "nome= " + this.nome + '\n' +
                "preço= " + this.preco + '\n' +
                ']';
    }

}
