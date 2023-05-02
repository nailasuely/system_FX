package com.example.sistema_gerenciamentofx.model;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class TecnicoTest {
    private static Tecnico tecnico1;
    private static OrdemServico ordem1;
    private static Cliente cliente1;

    @BeforeEach
    public void setUp() throws Exception {
        Connect.generateCache();
        tecnico1 = new Tecnico();

        // dps eu posso tirar isso, é apenas para um teste
        ordem1 = new OrdemServico();
        cliente1 = new Cliente("Rhian Sobrenome", "Rua ABC, Bahia",
                "211.131.280-03", 75);
        tecnico1 = new Tecnico("João Sobrenome", "Rua XYZ, Bahia",
                "300.272.440-83", 81);
        DAO.getClienteDAO().create(cliente1);
        DAO.getTecnicoDAO().create(tecnico1);
    }

    @Test
    public void getFullName() {
        tecnico1.setFullName("Maria");
        assertEquals("Maria", tecnico1.getFullName());
    }

    @Test
    public void setFullName() {
        tecnico1.setFullName("Maria");
        assertEquals("Maria", tecnico1.getFullName());

        tecnico1.setFullName(null);
        assertNull(tecnico1.getFullName());
    }

    @Test
    public void setId() {
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        tecnico1.setId(newIDStrign);
        assertEquals(newIDStrign, tecnico1.getId());
    }
    @Test
    public void setAddress(){
        tecnico1.setAddress("Rua ABC, Bahia");
        assertEquals("Rua ABC, Bahia", tecnico1.getAddress());

        tecnico1.setAddress(null);
        assertNull(tecnico1.getAddress());

    }
    @Test
    public void getCpf() {
        tecnico1.setCpf("300.272.440-83");
        assertEquals("300.272.440-83", tecnico1.getCpf());

        tecnico1.setCpf(null);
        assertNull(tecnico1.getCpf());

    }
    @Test
    public void getTelephone() {
        tecnico1.setTelephone(12345678);
        assertEquals(12345678, tecnico1.getTelephone());
    }


    @Test
    public void addServiceOrder() {
        tecnico1 = new Tecnico("Maria Sobrenome", "Rua ABC, Bahia", "300.272.440-83", 12345678);
        OrdemServico ordem1 = new OrdemServico();
        UUID newID = UUID.randomUUID();
        String tecnicoID = newID.toString();
        ordem1.setStatus("andamento");
        boolean adicionar = tecnico1.addServiceOrder(ordem1, tecnicoID);
        assertTrue(adicionar);
        assertEquals(1, Tecnico.getServiceOrders().size());

        OrdemServico ordem2 = new OrdemServico();
        ordem2.setStatus("andamento");
        boolean resultado2 = tecnico1.addServiceOrder(ordem2, tecnicoID);
        assertFalse(resultado2);
        assertEquals(1, Tecnico.getServiceOrders().size());

        ordem1.setStatus("Finalizado");
        boolean resultado3 = tecnico1.addServiceOrder(new OrdemServico(), tecnicoID);
        assertTrue(resultado3);
        assertEquals(2, Tecnico.getServiceOrders().size());
    }

    @Test
    public void geraRelatorioFinal() throws Exception{
        DAO.getOrdemServicoDAO().create(ordem1, DAO.getClienteDAO().findIdbyCPF("211.131.280-03"), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().atualizarStatusAndamento("300.272.440-83", ordem1);
        ordem1.finalize(3, "pix");
    }




}
