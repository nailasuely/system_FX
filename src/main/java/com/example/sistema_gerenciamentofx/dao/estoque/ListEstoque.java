package com.example.sistema_gerenciamentofx.dao.estoque;
import com.example.sistema_gerenciamentofx.model.Produto;

import java.util.HashMap;
import java.util.Map;

// coloquei o nome list só para padronizar, mas na vdd aqui vamos usar map;

/**
 *
 */
public class ListEstoque implements EstoqueDAO {
    private Map<Produto, Integer> estoque;

    public ListEstoque() {
        estoque = new HashMap<>();
    }

    //Seguinte: Essa classe é utilizada apenas no início de programa para preencher o estoque;
    public void AdicionarEstoqueInicial(){
        estoque.put(Produto.novaRam(), 20);
        estoque.put(Produto.novaPlacaMae(), 20);
        estoque.put(Produto.novaFonte(), 20);
        estoque.put(Produto.novaPlacaDeVideo(), 20);
        estoque.put(Produto.novoHDSSD(), 20);
    }

    // essa classe também funciona como ordem de compra.
    public void adicionarProduto(Produto produto, int quantidade) {
        if (estoque.containsKey(produto)) {
            int quantidadeAtual = estoque.get(produto);
            System.out.println(quantidadeAtual);
            estoque.put(produto, quantidadeAtual + quantidade);
        }
        else {
            estoque.put(produto, quantidade);
        }
    }
    public void retirarEstoque(Produto produto, int quantidade) throws SemEstoqueException {
        if (estoque.containsKey(produto)) {
            int quantidadeAtual = estoque.get(produto);
            if (quantidadeAtual > 0 && quantidadeAtual > quantidade) {
                estoque.put(produto, quantidadeAtual - quantidade);
            } else {
                throw new SemEstoqueException("O produto " + produto.getNome() + " não está no estoque.");
            }
        } else {
            throw new SemEstoqueException("O produto " + produto.getNome() + " não foi encontrado no estoque.");
        }
    }

    public void ordemDeCompraAutomatica() {
        for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
            Produto pdt = entry.getKey();
            int quantidade = entry.getValue();
            if (quantidade < 5) {
                estoque.put(pdt, quantidade + 20);}
        }
    }


    public int getQuantidade(Produto produto) {
        return estoque.getOrDefault(produto, 0);
    }


}
