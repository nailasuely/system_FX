package com.example.sistema_gerenciamentofx.dao.estoque;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class ListEstoqueTest {
    Produto pd1 = Produto.novaFonte();
    int i = 30;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void adicionarEstoqueInicial() {
    }


    @Test
    void adicionarProduto() {

        assertEquals(30, pd1.getPreco());
        DAO.getEstoqueDAO().adicionarProduto(pd1, 20);
        assertEquals(20, DAO.getEstoqueDAO().getQuantidade(pd1));
        DAO.getEstoqueDAO().adicionarProduto(pd1, 5);
        assertEquals(25, DAO.getEstoqueDAO().getQuantidade(pd1));

    }

    @Test
    void retirarEstoque() throws SemEstoqueException {
        DAO.getEstoqueDAO().adicionarProduto(pd1, 20);
        DAO.getEstoqueDAO().retirarEstoque(pd1, 5);
        assertEquals(15, DAO.getEstoqueDAO().getQuantidade(pd1));


    }
}