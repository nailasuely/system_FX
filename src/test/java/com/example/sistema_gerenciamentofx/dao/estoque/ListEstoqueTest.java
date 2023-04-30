package com.example.sistema_gerenciamentofx.dao.estoque;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(JUnitPlatform.class)
public class ListEstoqueTest {
    Produto produto1;
    Produto produto2;
    Produto produto3;

    @BeforeEach
    public void setUp() throws Exception {
        Connect.generateCache();
        produto1 = Produto.novaFonte();
        produto2 = Produto.novaPlacaMae();
        produto3 = Produto.novaPlacaDeVideo();
        DAO.getEstoqueDAO().AdicionarEstoqueInicial();
    }

    @AfterEach
    public void tearDown() throws Exception {
        DAO.getEstoqueDAO().deleteMany();
    }

    @Test
    public void adicionarEstoqueInicial()  throws Exception{
        assertEquals(20, DAO.getEstoqueDAO().getQuantidade(Produto.novaFonte()));
        assertEquals(20, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaMae()));
        assertEquals(20, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaDeVideo()));
    }


    @Test
    public void adicionarProduto()  throws Exception{

        assertEquals(30, produto1.getPreco());
        DAO.getEstoqueDAO().adicionarProduto(produto1, 20);
        assertEquals(40, DAO.getEstoqueDAO().getQuantidade(produto1));
        DAO.getEstoqueDAO().adicionarProduto(produto1, 5);
        assertEquals(45, DAO.getEstoqueDAO().getQuantidade(produto1));

    }
    @Test
    public void retirarEstoque() throws SemEstoqueException, Exception {
        DAO.getEstoqueDAO().retirarEstoque(produto1, 5);
        assertEquals(15, DAO.getEstoqueDAO().getQuantidade(produto1));


    }
    @Test
    public void ordemDeCompraAutomatica() throws SemEstoqueException, Exception {
        // Primeiro verificar a quantidade itens adicionados na inicicialização
        assertEquals(20, DAO.getEstoqueDAO().getQuantidade(Produto.novaFonte()));
        assertEquals(20, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaMae()));
        assertEquals(20, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaDeVideo()));
        // Aqui vai ser retirado 18 itens de cada um, pois a ordem de compra automática é caso
        // o produto tenha menos que cinco produtos em seu estoque.
        DAO.getEstoqueDAO().retirarEstoque(Produto.novaFonte(), 18);
        DAO.getEstoqueDAO().retirarEstoque(Produto.novaPlacaMae(), 18);
        DAO.getEstoqueDAO().retirarEstoque(Produto.novaPlacaDeVideo(), 18);
        assertEquals(2, DAO.getEstoqueDAO().getQuantidade(Produto.novaFonte()));
        assertEquals(2, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaMae()));
        assertEquals(2, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaDeVideo()));
        //Agora gera a ordem de compra, que vai adicionar mais 20 em cada um.
        DAO.getEstoqueDAO().ordemDeCompraAutomatica();
        assertEquals(22, DAO.getEstoqueDAO().getQuantidade(Produto.novaFonte()));
        assertEquals(22, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaMae()));
        assertEquals(22, DAO.getEstoqueDAO().getQuantidade(Produto.novaPlacaDeVideo()));

    }
}