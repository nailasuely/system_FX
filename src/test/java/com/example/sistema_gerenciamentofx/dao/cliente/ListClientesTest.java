package com.example.sistema_gerenciamentofx.dao.cliente;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListClientesTest {
    private static ClienteDAO clienteDAO;
    ArrayList<Cliente> listaDeClientes = new ArrayList<Cliente>();
    private static Cliente cliente1;
    private static Cliente cliente2;

    @BeforeEach
    void setUp() {
        cliente1 = new Cliente("Maria Sobrenome", "Rua ABC, Bahia",
                "123.789.101-10", 75);


        cliente2 = new Cliente("João Sobrenome", "Rua XYZ, Bahia",
                "456.789.101-10", 81);

    }
    @AfterEach
    void tearDown() {
        DAO.getClienteDAO().deleteMany();
    }

    @Test
    void create() {
        // Cliente criado para verificar se ele adiciona um cliente que já existe
        Cliente teste;
        DAO.getClienteDAO().create(cliente1);
        assertNotNull(DAO.getClienteDAO().findByCPF("123.789.101-10"));

        //verifica se o ID foi realmente criado
        assertNotNull(cliente1.getId());

        teste = DAO.getClienteDAO().create(cliente1);

        // se retornar null, significa que o sistema nao adiciona o cliente ja existente
        assertNull(teste);
        DAO.getClienteDAO().create(cliente2);
        // Verifica se tem a quantidade de clientes esperada.
        assertEquals(2, DAO.getClienteDAO().amountItems());

    }

    @Test
    void update() {
        DAO.getClienteDAO().create(cliente1);
        cliente1.setFullName("Joana novo Sobrenome");
        DAO.getClienteDAO().update(cliente1);
        assertEquals("Joana novo Sobrenome", DAO.getClienteDAO().findByCPF("123.789.101-10").getFullName());
    }

    @Test
    void delete() {
        DAO.getClienteDAO().create(cliente1);
        DAO.getClienteDAO().delete("123.789.101-10");
        assertEquals(0, DAO.getClienteDAO().amountItems());
    }

    @Test
    void findByCPF() {
        DAO.getClienteDAO().create(cliente1);
        Cliente testeEncontrar = DAO.getClienteDAO().findByCPF("123.789.101-10");
        assertEquals(cliente1, testeEncontrar);

    }

    @Test
    void findByCpfIsTrue() {
    }

    @Test
    void findIdbyCPF() {
    }
}