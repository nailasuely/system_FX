package com.example.sistema_gerenciamentofx.model;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.dao.estoque.SemEstoqueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
public class OrdemServicoTest {
    private static OrdemServico ordem1;
    private static OrdemServico ordem2;
    private static Cliente cliente1;
    private static Tecnico tecnico1;
    private static Tecnico tecnico2;
    private static Tecnico tecnico3;

    @BeforeEach
    public void setUp()  throws Exception{
        Connect.generateCache();
        DAO.getOrdemServicoDAO().deleteMany();
        ordem1 = new OrdemServico();
        ordem2 = new OrdemServico();
        cliente1 = new Cliente("Keila Sobrenome", "Rua ABC, Bahia",
                "226.379.720-33", 75);
        tecnico1 = new Tecnico("Ana Sobrenome", "Rua XYZ, Bahia",
                "175.406.590-25", 81);
        DAO.getClienteDAO().create(cliente1);
        DAO.getTecnicoDAO().create(tecnico1);

    }
    @Test
    public void getPriceServico() throws Exception {
        // Adicionado um serviço, ou seja, o preço deve ser apenas o preço do serviço.
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("226.379.720-33"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("175.406.590-25", ordem1);
        assertEquals(50, ordem1.getPrice());

    }

    // esse cálculo do preço difere, pois, ele requer o uso de uma lista com os produtos utilizados na montagem.
    @Test
    public void getPriceMontagem() throws SemEstoqueException, ProdutoErradoException, Exception {
        DAO.getEstoqueDAO().AdicionarEstoqueInicial();
        DAO.getEstoqueDAO().AdicionarEstoqueInicial();
        // Adicionado uma montagem, ou seja, o preço deve ser calculado com a quantidade.
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("226.379.720-33"), Produto.servicoMontagem());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("175.406.590-25", ordem1);
        ordem1.setListaProdutos(Produto.novaPlacaMae(), 10);
        assertEquals(1000, ordem1.getPrice());

    }
    @Test
    public void getPaymentType() {
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
    public void setStatus() throws Exception {

        DAO.getOrdemServicoDAO().create(ordem1);
        assertEquals("espera", ordem1.getStatus());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("175.406.590-25", ordem1);
        assertEquals("andamento", ordem1.getStatus());
    }

    @Test
    public void getDescription() throws Exception {
        tecnico2 = new Tecnico("Naila Sobrenome", "Rua XYZ, Bahia",
                "886.948.540-40", 81);
        tecnico2 = DAO.getTecnicoDAO().create(tecnico2);
        ordem2.setTechnicianID(DAO.getTecnicoDAO().findIdbyCPF("886.948.540-40"));
        DAO.getOrdemServicoDAO().create(ordem2, DAO.getClienteDAO().findIdbyCPF("226.379.720-33"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("886.948.540-40", ordem2);
        OrdemServico teste1;
        teste1 =DAO.getOrdemServicoDAO().openOrderByTechnician("886.948.540-40");
        teste1.finalize(5, "pix");
        assertEquals("------NOTA FISCAL DA ORDEM------" + "\n"+
                "Serviço                    Preço un." + "\n"+
                "formatacao"+" --------------- R$" + 50.0+"\n"+
                "======================================" +"\n"+
                "Preço total da ordem de serviço: R$" + 50.0+ "\n"+
                "Tecnico responsável: " + "Naila Sobrenome" +"\n"+
                "Cliente requisitante: " + "Keila Sobrenome" +"\n"+
                "Forma de pagamento: "+ "pix" +"\n"+
                "Tempo de duração da ordem: " + "Foram gastos 0 dias" + "\n"+
                "ID da ordem de serviço: "+ teste1.getId(), DAO.getOrdemServicoDAO().findById(teste1.getId()).generateInvoice() );
    }

    @Test
    public void generateInvoice() throws SemEstoqueException, ProdutoErradoException, Exception {
        DAO.getEstoqueDAO().AdicionarEstoqueInicial();
        tecnico3 = new Tecnico("Aquino Sobrenome", "Rua XYZ, Bahia",
                "065.199.050-54", 81);
        DAO.getTecnicoDAO().create(tecnico3);
        ordem1.setListaProdutos(Produto.novaPlacaMae(), 2);
        ordem1 = DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("226.379.720-33"), Produto.servicoMontagem());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("065.199.050-54", ordem1);
        OrdemServico teste;
        teste =DAO.getOrdemServicoDAO().openOrderByTechnician("065.199.050-54");
        teste.finalize(5, "cartao");
        assertEquals("-------NOTA FISCAL DA ORDEM-------" + "\n"+
                "Peça/produto   Quantidade   Preço un." + "\n"+
                "placa mae ------- "+2+" ------- R$"+100.0+"\n"+
                "======================================" +"\n"+
                "Quantidade total de itens: " + 2+ "\n"+
                "Preço total da ordem de serviço: R$" + 200.0 +"\n"+
                "Tecnico responsável: " + "Aquino Sobrenome" +"\n"+
                "Cliente requisitante: " + "Keila Sobrenome" +"\n" +
                "Forma de pagamento: "+ "cartao" +"\n"+
                "Tempo de duração da ordem: " + "Foram gastos 0 dias" +"\n"+
                "ID da ordem de serviço: "+ teste.getId(), teste.generateInvoice());
    }

    @Test
    public void calculateExpendTime() {
        LocalDate start = LocalDate.now();
        Period periodo = Period.between(start, LocalDate.now());
        assertEquals(periodo, ordem1.calculateExpendTime());
    }

    @Test
    public void testFinalize() throws Exception {
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("226.379.720-33"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("175.406.590-25", ordem1);
        ordem1.finalize(3, "pix");
    }

    @Test
    public void getStart() {
        LocalDate start = LocalDate.now();
        assertEquals(start, ordem1.getStart());
    }
}