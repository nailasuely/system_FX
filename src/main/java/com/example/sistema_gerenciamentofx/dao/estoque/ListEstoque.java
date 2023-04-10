package com.example.sistema_gerenciamentofx.dao.estoque;
import com.example.sistema_gerenciamentofx.model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// coloquei o nome list só para padronizar, mas na vdd aqui vamos usar map;

/**
 * Está classe é parte da forma de armazenamento de dados, sendo utilizada para tal um dicionário, a fim de controlar o
 * estoque, com relação a quantidade de produtos, a compra de novos produtos, e a retirada de produtos para o estoque.<br>
 * Há nessa classe os métodos:
 * <ul>
 *     <li>
 *         <b>adicionarEstoqueInicial</b>
 *     </li>
 *     <li>
 *         <b>adicionarProduto</b>
 *     </li>
 *     <li>
 *         <b>retirarEstoque</b>
 *     </li>
 *     <li>
 *         <b>ordemDeCompraAutomatica</b>
 *     </li>
 *     <li>
 *         <b>getQuantidade</b>
 *     </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class ListEstoque implements EstoqueDAO {
    /**
     * O atributo <b>estoque</b> é do tipo <i>HashMap</i>, e serve para armazenar as informações a respeito do estoque.<br>
     * Nesse dicicionário, é utilizado um objeto do tipo <i>Produto</i> como chave, e um inteiro, do tipo <i>Integer</i> como valor
     * afim de para cada produto ter o inteiro como quantidade em estoque daquele produto da chave
     */
    private Map<Produto, Integer> estoque;
    /**
     * Método construtor da classe, em que ocorre a inicialização do dicionário que irá conter informações sobre o estoque, a
     * respeito do tipo do produto e sua quantidade em estoque.
     */
    public ListEstoque() {
        estoque = new HashMap<>();
    }

    //Seguinte: Essa classe é utilizada apenas no início de programa para preencher o estoque;
    /**
     * Método para ao início do programa adicionar uma quantidade de produtos ao estoque.<br>
     * Nesse método é colocado a quantidade de 20 peças para cada um dos produtos pré-definidos no sistema
     */
    public void AdicionarEstoqueInicial(){
        estoque.put(Produto.novaRam(), 20);
        estoque.put(Produto.novaPlacaMae(), 20);
        estoque.put(Produto.novaFonte(), 20);
        estoque.put(Produto.novaPlacaDeVideo(), 20);
        estoque.put(Produto.novoHDSSD(), 20);
    }

    /**
     * Método responsavel para poder obter o dicionário que contém salvo as informações sobre o estoque
     * @return Dicionario, <i>Map</i>, com as informações dos produtos contidos no estoque, adjuntos com suas quantidades
     */
    @Override
    public Map<Produto, Integer> getList() {
        return this.estoque;
    }


    // essa classe também funciona como ordem de compra.
    /**
     * Método responsável por adicionar produtos ao estoque.<br>
     * Um de seus usos ocorre na realização da "ordem de compra" dentro do sistema
     * @param produto Objeto do tipo <i>Produto</i> o qual ja contém o nome do produto a ser adicionado.
     * @param quantidade <i>Int</i> que contém o valor que vai ser adicionado ao estoque
     */
    public void adicionarProdutotest(Produto produto, int quantidade) {
        if (estoque.containsKey(produto)) {
            int quantidadeAtual = estoque.get(produto);
            System.out.println(quantidadeAtual);
            estoque.put(produto, quantidadeAtual + quantidade);
        }
        else {
            estoque.put(produto, quantidade);
        }
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        for (Produto pd1 : this.estoque.keySet()) {
            if (pd1.getNome().equals(produto.getNome())) {
                int quantidadeAtual = estoque.get(pd1);
                estoque.put(pd1, quantidadeAtual + quantidade);
                return;
            }
        }
        estoque.put(produto, quantidade);
    }
    /**
     * Método responsável por fazer a retirada do produto do estoque, para ser utilizado/instalado na realização da ordem de serviço.<br>
     * Algumas condições acontecem, antes de ser feita a retirada, e caso elas tenham resultados negativos, é gerada uma
     * exceção personalizada para cada caso. Dentre as verificações:
     * <ul>
     *     <li>
     *          Verificação se o produto inserido existe no estoque;<br>
     *          Exceção gerada: "O produto (nome do produto) não foi encontrado no estoque."
     *     </li>
     *     <li>
     *         Verificação se foi pedido uma quantidade maior do que 0, além de se aquela quantidade esta presente no estoque;<br>
     *         Exceção gerada: "O produto (nome do produto) não está no estoque."
     *     </li>
     * </ul>
     *
     * @param produto Objeto do tipo <i>Produto</i> que contém o nome do produto que deseja ser retirado
     * @param quantidade <i>Int</i> que representa a quantidade de produto requerida para ser utilizada na ordem de serviço
     * @throws SemEstoqueException Exceção criada diretamente para este método, afim de personalizar a mensagem de erro, quando este ocorrer
     */
    public void retirarEstoque(Produto produto, int quantidade) throws SemEstoqueException {
        boolean produtoEncontrado = false;
        for (Produto produto1 : estoque.keySet()) {
            if (produto1.getNome().equals(produto.getNome())) {
                produtoEncontrado = true;
                int quantidadeAtual = estoque.get(produto1);
                if (quantidadeAtual > 0 && quantidadeAtual >= quantidade) {
                    estoque.put(produto1, quantidadeAtual - quantidade);
                    break;
                } else {
                    throw new SemEstoqueException("O produto " + produto.getNome() + " não está no estoque.");
                }
            }
        }
        if (!produtoEncontrado) {
            throw new SemEstoqueException("O produto " + produto.getNome() + " não foi encontrado no estoque.");
        }
    }

    /**
     * Método que realiza a "compra" automaticamente, preenchendo o estoque com novos produtos, para isso ele primeiro
     * percorre todos os elementos presentes no estoque, e verifica qual contém um valor abaixo de 5 unidades, se houver
     * com essa quantidade, ou menos, é adicionado a quantidade de 20 unidades ao estoque.<br>
     * Acionado no decorrer do programa.<br>
     */
    public void ordemDeCompraAutomatica() {
        for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
            Produto pdt = entry.getKey();
            int quantidade = entry.getValue();
            if (quantidade < 5) {
                estoque.put(pdt, quantidade + 20);}
        }
    }

    /**
     * Método para obter a informação da quantidade daquele determinado produto no estoque.
     * @param produto Objeto do tipo <i>Produto</i> que serve para identificar qual produto está sendo referido para obter a quantidade.
     * @return <i>Int</i> com a quantidade do produto presente no estoque, caso não tenha nenhum daqueles produtos, é retornado o valor 0
     */
    public int getQuantidadetest(Produto produto) {
        return estoque.getOrDefault(produto, 0);
    }
    public int getQuantidade(Produto produto) {
        for (Produto pd1 : this.estoque.keySet()) {
            if (pd1.getNome().equals(produto.getNome())) {
                int quantidade = this.estoque.get(pd1);
                return quantidade;
            }
        }
        return 0;
    }
    @Override
    public void deleteMany() {
        this.estoque = new HashMap<>();
    }
}
