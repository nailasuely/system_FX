package com.example.sistema_gerenciamentofx.dao.estoque;

import com.example.sistema_gerenciamentofx.model.Produto;


import java.util.Map;
/**
 * A interface criada para conter os métodos implementados relativos ao Estoque, sendo eles:
 * <ul>
 *     <li>
 *         <b>adicionarProduto</b> - para adicionar produtos ao estoque
 *     </li>
 *     <li>
 *         <b>getQuantidade</b> - para obter a quantidade no estoque de determinado produto
 *     </li>
 *      <li>
 *         <b>retirarEstoque</b> - para retirar determinada quantidade de produtos do estoque para usar na realização da ordem de serviço
 *     </li>
 *     <li>
 *         <b>ordemDeCompraAutomatica</b> - para adicionar produtos automaticamente ao estoque, quando detectado baixo nível no estoque
 *     </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public interface EstoqueDAO {
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para adicionar produtos ao estoque, sendo usado em operações de compra de produto para o estoque.
     * @param produto Objeto do tipo <i>Produto</i> para indicar qual produto vai ser adicionado ao estoque, se for este ja estiver no
     *                sistema é somente adicionada a quantidade, porém se ainda não existir, é inserido no dicionário.
     * @param quantidade <i>Int</i> que representa a quantidade que vai ser adicionada no estoque
     */
    public void adicionarProduto(Produto produto, int quantidade);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para obter a quantidade presente no estoque daquele determinado produto.
     * @param produto Objeto do tipo <i>Produto</i> para poder indicar qual dos produtos no estoque deseja saber a quantidade
     * @return <i>Int</i> contendo a quantidade daquele objeto presente no estoque
     */
    public int getQuantidade(Produto produto);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para realizar a retirada de produtos do estoque.
     * @param produto Objeto do tipo <i>Produto</i> que serve para indicar qual produto vai ser retirado do estoque
     * @param quantidade <i>Int</i> para indicar qual quantidade deverá ser retirada do estoque
     * @throws SemEstoqueException Representa exceção que pode acontecer de 2 formas diferentes:
     * <ul>
     *     <li>
     *          1ª - Caso o produto não tenha a quantidade adequada, ou não tenha nennhuma quantidade no sistema
     *     </li>
     *     <li>
     *         2º - Caso o produto não foi encontrado no estoque, ou seja ele nunca foi cadastrado no estoque
     *     </li>
     * </ul>
     */
    public void retirarEstoque(Produto produto, int quantidade) throws SemEstoqueException;
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para realização de uma compra automaticamente, quando detectado baixa quantidade no estoque
     */
    void ordemDeCompraAutomatica();

    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para adicionar itens ao estoque de forma rápida, já que para todos os produtos pré-definidos
     * é adicionado a quantidade de 20 itens para o estoque.<br>
     * Seu funcionamento é necessário, em prioridade na primeira inicialização do programa.
     */
    public void AdicionarEstoqueInicial();

    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para poder obter o dicionário em que está salvo as informações do estoque
     * @return <i>Map</i> contendo as informações dos produtos e suas quantidades
     */
    public Map<Produto, Integer> getList();

    public void deleteMany();
}
