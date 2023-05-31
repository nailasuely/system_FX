package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class ListOrdensServicoTest {
    private OrdemServico ordem1;
    private OrdemServico ordem2;
    private static Cliente cliente1;
    private static Tecnico tecnico1;


    @BeforeEach
    public void setUp() throws Exception {
        Connect.generateCache();
        tecnico1 = new Tecnico("Mirela Sobrenome", "Coité, Bahia",
                "227.605.650-92", 75);
        cliente1 = new Cliente("Lara Sobrenome", "Rua ABC, Bahia",
                "610.819.650-53", 81);
        ordem1 = new OrdemServico();
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getClienteDAO().create(cliente1);


    }

    @AfterEach
    public void tearDown() throws Exception {
       DAO.getOrdemServicoDAO().deleteMany();
        DAO.getEstoqueDAO().deleteMany();
        DAO.getTecnicoDAO().deleteMany();
        DAO.getClienteDAO().deleteMany();


    }

    @Test
    public void create() throws Exception {
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        // Verifica se o ‘id’ foi gerado.
        assertNotNull(ordem1.getId());
        // Verifica se a data inicial ficou correta.
        assertEquals(LocalDate.now(), ordem1.getStart());
        // Verifica o id do cliente.
        assertEquals(cliente1.getId(), ordem1.getClientId());
        // Verifica o tipo do serviço.
        assertEquals(Produto.servicoFormatar().getNome(),ordem1.getType().getNome());
    }

    @Test
    public void update() throws Exception {
        OrdemServico ordemAtt;
        Cliente cliente2 = new Cliente("Latiele Sobrenome", "Rua A", "770.603.570-09", 72);
        DAO.getClienteDAO().create(cliente2);
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        ordemAtt = DAO.getOrdemServicoDAO().findById(ordem1.getId());
        ordemAtt.setClientId(cliente2.getId());
        // Atualiza o cliente da ordem.
        DAO.getOrdemServicoDAO().update(ordemAtt);
        assertEquals(ordemAtt, DAO.getOrdemServicoDAO().findById(ordemAtt.getId()));

        // Tentando adicionar uma ordem não existente no DAO ordem.
        OrdemServico ordem2 = new OrdemServico();
        try {
            DAO.getOrdemServicoDAO().update(ordem2);
            fail("Uma exception deveria ter sido lançada");
        } catch (IllegalArgumentException excep) {
            assertEquals("Ordem não detectado no banco de dados", excep.getMessage());
        }
    }

    @Test
    public void delete() throws Exception {
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        // Verifica a quantidade depois de uma ordem adicionada.
        assertEquals(1,DAO.getOrdemServicoDAO().getList().size());
        DAO.getOrdemServicoDAO().delete(ordem1.getId());
        // Verifica após a ordem ser deletada.
        assertEquals(0,DAO.getOrdemServicoDAO().getList().size());
    }

    @Test
    public void findById() throws Exception {
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        OrdemServico testeEncontrar = DAO.getOrdemServicoDAO().findById(ordem1.getId());
        assertEquals(ordem1, testeEncontrar);
    }

    @Test
    public void deleteMany() throws Exception {
        ordem2 = new OrdemServico();
        Cliente cliente2 = new Cliente("Laisa Sobrenome", "Rua ABC, Bahia",
                "257.705.020-88", 81);
        DAO.getClienteDAO().create(cliente2);
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().create(ordem2, cliente2.getId(), Produto.servicoFormatar());
        assertEquals(2,  DAO.getOrdemServicoDAO().getList().size());
        DAO.getOrdemServicoDAO().deleteMany();
        assertEquals(0,  DAO.getOrdemServicoDAO().getList().size());

    }
    @Test
    public void amountItems() throws Exception {
        ordem2 = new OrdemServico();
        Cliente cliente2 = new Cliente("Yasmim Sobrenome", "Rua ABC, Bahia",
                "640.540.980-53", 81);
        DAO.getClienteDAO().create(cliente2);
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().create(ordem2, cliente2.getId(), Produto.servicoFormatar());
        assertEquals(2,  DAO.getOrdemServicoDAO().amountItems());
    }

    @Test
    public void agendaAtendimento() throws Exception {
        ordem2 = new OrdemServico();
        Cliente cliente2 = new Cliente("Vitor Sobrenome", "Rua ABC, Bahia",
                "579.997.120-51", 81);
        DAO.getClienteDAO().create(cliente2);
        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().create(ordem2, cliente2.getId(), Produto.servicoInstalar());
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("610.819.650-53"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("227.605.650-92", ordem1);
        assertEquals("Tecnico: Mirela Sobrenome\n" +
                "Ordem em andamento: \n" +
                "ID da ordem: "+ ordem1.getId()+"\n" +
                "Nome do cliente: Lara Sobrenome\n" +
                "\n" +
                "Ordem em espera: \n" +
                "ID da ordem: "+ ordem2.getId()+"\n" +
                "Nome do cliente: Vitor Sobrenome\n" +
                "\n",DAO.getOrdemServicoDAO().agendaAtendimento() );


    }
}