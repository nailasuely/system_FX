package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ListOrdensServicoTest {
    private OrdemServico ordem1;
    private OrdemServico ordem2;
    private static Cliente cliente1;
    private static Tecnico tecnico1;

    private Produto produto1;


    @BeforeEach
    void setUp() {
        tecnico1 = new Tecnico("Rhian Sobrenome", "Coité, Bahia",
                "123.789.101-10", 75);
        cliente1 = new Cliente("Maria Sobrenome", "Rua ABC, Bahia",
                "456.789.101-10", 81);
        ordem1 = new OrdemServico();
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getClienteDAO().create(cliente1);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
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