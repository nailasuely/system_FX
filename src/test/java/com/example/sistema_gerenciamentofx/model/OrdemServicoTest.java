package com.example.sistema_gerenciamentofx.model;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.estoque.SemEstoqueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoTest {
    private static OrdemServico ordem1;
    private static OrdemServico ordem2;
    private static Cliente cliente1;
    private static Tecnico tecnico1;
    private static Tecnico tecnico2;

    @BeforeEach
    void setUp() {
        ordem1 = new OrdemServico();
        ordem2 = new OrdemServico();
        cliente1 = new Cliente("Maria Sobrenome", "Rua ABC, Bahia",
                "123.789.101-10", 75);
        tecnico1 = new Tecnico("João Sobrenome", "Rua XYZ, Bahia",
                "456.789.101-10", 81);
        DAO.getClienteDAO().create(cliente1);
        DAO.getTecnicoDAO().create(tecnico1);

    }
    @Test
    void getPriceServico() {
        // Adicionado um serviço, ou seja, o preço deve ser apenas o preço do serviço.
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("123.789.101-10"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("456.789.101-10", ordem1);
        assertEquals(50, ordem1.getPrice());

    }

    // esse cálculo do preço difere, pois, ele requer o uso de uma lista com os produtos utilizados na montagem.
    @Test
    void getPriceMontagem() throws SemEstoqueException, ProdutoErradoException {
        DAO.getEstoqueDAO().AdicionarEstoqueInicial();
        // Adicionado uma montagem, ou seja, o preço deve ser calculado com a quantidade.
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("123.789.101-10"), Produto.servicoMontagem());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("456.789.101-10", ordem1);
        ordem1.setListaProdutos(Produto.novaPlacaMae(), 10);
        assertEquals(1000, ordem1.getPrice());

    }
    @Test
    void getPaymentType() {
        ordem1.setPaymentType("transferencia");
        assertEquals("transferencia", ordem1.getPaymentType());
    }
    @Test
    public void InvalidPaymentType() {
        try {
            ordem1.setPaymentType("cheque");
            fail("Expected an IllegalArgumentException to be thrown");}
        catch (IllegalArgumentException teste) {
            assertEquals("O tipo de pagamento que você colocou não é válido no sistema: cheque", teste.getMessage());
        }
    }
    @Test
    void setStatus() {
        DAO.getOrdemServicoDAO().create(ordem1);
        assertEquals("espera", ordem1.getStatus());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("456.789.101-10", ordem1);
        assertEquals("andamento", ordem1.getStatus());
    }

    @Test
    void getDescription() {
        tecnico2 = new Tecnico("Everton Sobrenome", "Rua XYZ, Bahia",
                "456.539.155-10", 81);
        DAO.getTecnicoDAO().create(tecnico2);
        DAO.getOrdemServicoDAO().create(ordem2, DAO.getClienteDAO().findIdbyCPF("123.789.101-10"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("456.539.155-10", ordem2);
        OrdemServico teste1;
        teste1 =DAO.getOrdemServicoDAO().openOrderByTechnician("456.539.155-10");
        teste1.finalize(5, "pix");
        assertEquals("------NOTA FISCAL DA ORDEM------" + "\n"+
                "Serviço                    Preço un." + "\n"+
                "formatacao"+" --------------- R$" + 50.0+"\n"+
                "======================================" +"\n"+
                "Preço total da ordem de serviço: R$" + 50.0+ "\n"+
                "Tecnico responsável: " + "Everton Sobrenome" +"\n"+
                "Cliente requisitante: " + "Maria Sobrenome" +"\n"+
                "Forma de pagamento: "+ "pix" +"\n"+
                "Tempo de duração da ordem: " + "Foram gastos 0 dias" + "\n"+
                "ID da ordem de serviço: "+ teste1.getId(), DAO.getOrdemServicoDAO().findById(teste1.getId()).generateInvoice() );
    }

    @Test
    void generateInvoice() throws SemEstoqueException, ProdutoErradoException {
        DAO.getEstoqueDAO().AdicionarEstoqueInicial();

        ordem1.setListaProdutos(Produto.novaPlacaMae(), 2);

        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("123.789.101-10"), Produto.servicoMontagem());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("456.789.101-10", ordem1);
        OrdemServico teste;
        teste =DAO.getOrdemServicoDAO().openOrderByTechnician("456.789.101-10");
        teste.finalize(5, "cartao");
        assertEquals("-------NOTA FISCAL DA ORDEM-------" + "\n"+
                "Peça/produto   Quantidade   Preço un." + "\n"+
                "placa mae ------- "+2+" ------- R$"+100.0+"\n"+
                "======================================" +"\n"+
                "Quantidade total de itens: " + 2+ "\n"+
                "Preço total da ordem de serviço: R$" + 200.0 +"\n"+
                "Tecnico responsável: " + "João Sobrenome" +"\n"+
                "Cliente requisitante: " + "Maria Sobrenome" +"\n" +
                "Forma de pagamento: "+ "cartao" +"\n"+
                "Tempo de duração da ordem: " + "Foram gastos 0 dias" +"\n"+
                "ID da ordem de serviço: "+ teste.getId(), teste.generateInvoice());
    }

    @Test
    void calculateExpendTime() {
        LocalDate start = LocalDate.now();
        Period periodo = Period.between(start, LocalDate.now());
        assertEquals(periodo, ordem1.calculateExpendTime());
    }

    @Test
    void testFinalize() {
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("123.789.101-10"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("456.789.101-10", ordem1);
        ordem1.finalize(3, "pix");
        System.out.println(ordem1);
    }

    @Test
    void getStart() {
        LocalDate start = LocalDate.now();
        assertEquals(start, ordem1.getStart());
    }
}