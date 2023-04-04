package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListOrdensServicoTest {
    private OrdemServico ordem1;
    private OrdemServico ordem2;
    private static Cliente cliente1;
    private static Tecnico tecnico1;


    @BeforeEach
    void setUp() {
        tecnico1 = new Tecnico("Rhian Sobrenome", "Coité, Bahia",
                "123.789.101-10", 75);
        cliente1 = new Cliente("Maria Sobrenome", "Rua ABC, Bahia",
                "456.789.101-10", 81);
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getClienteDAO().create(cliente1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        String id;
        ordem1 = new OrdemServico();
        DAO.getOrdemServicoDAO().create(ordem1);
        assertNotNull(ordem1.getId());

        id = ordem1.getId();

        //DAO.getOrdemServicoDAO().atualizarStatusAndamento("123.789.101-10", ordem1);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void listObjects() {
    }

    @Test
    void deleteMany() {
    }

    @Test
    void amountItems() {
    }
}