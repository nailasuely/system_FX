package com.example.sistema_gerenciamentofx.model;

import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(JUnitPlatform.class)
public class ProdutoTest {
    Produto produto1;

    @BeforeEach
    public void setUp() throws Exception{
        Connect.generateCache();
        produto1 = new Produto();
    }

    @Test
    public void testGetPreco() {
        produto1 = new Produto("teste", 50.0);
        assertEquals(50.0, produto1.getPreco());
    }

    @Test
    public void getNome() {
        produto1 = new Produto("teste", 10.0);
        assertEquals("teste", produto1.getNome());
    }

    @Test
    public void novaRam() {
        produto1 = Produto.novaRam();
        assertEquals("ram", produto1.getNome());
        assertEquals(20.0, produto1.getPreco());
    }

    @Test
    public void novaPlacaMae() {
        produto1 = Produto.novaPlacaMae();
        assertEquals("placa mae", produto1.getNome());
        assertEquals(100.0, produto1.getPreco());
    }

    @Test
    public void novaFonte() {
        produto1 = Produto.novaFonte();
        assertEquals("fonte", produto1.getNome());
        assertEquals(30.0, produto1.getPreco());
    }

    @Test
    public void novaPlacaDeVideo() {
        produto1 = Produto.novaPlacaDeVideo();
        assertEquals("placa de video", produto1.getNome());
        assertEquals(100.0, produto1.getPreco());
    }

    @Test
    public void novoHDSSD() {
        produto1 = Produto.novoHDSSD();
        assertEquals("hd/ssd", produto1.getNome());
        assertEquals(30.0, produto1.getPreco());
    }

    @Test
    public void servicoMontagem() {
        produto1 = Produto.servicoMontagem();
        assertEquals("montagem", produto1.getNome());
        assertEquals(0.0, produto1.getPreco());
    }

    @Test
    public void servicoFormatar() {
        produto1 = Produto.servicoFormatar();
        assertEquals("formatacao", produto1.getNome());
        assertEquals(50.0, produto1.getPreco());
    }

    @Test
    public void servicoInstalar() {
        produto1 = Produto.servicoInstalar();
        assertEquals("instalacao", produto1.getNome());
        assertEquals(10.0, produto1.getPreco());
    }

    @Test
    public void servicoLimpeza() {
        produto1 = Produto.servicoLimpeza();
        assertEquals("limpeza", produto1.getNome());
        assertEquals(70.0, produto1.getPreco());
    }
}