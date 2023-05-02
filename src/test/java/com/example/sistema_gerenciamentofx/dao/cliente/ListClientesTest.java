package com.example.sistema_gerenciamentofx.dao.cliente;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ListClientesTest {
    private static ClienteDAO clienteDAO;
    ArrayList<Cliente> listaDeClientes = new ArrayList<Cliente>();
    private static Cliente cliente1;
    private static Cliente cliente2;

    @BeforeEach
    public void setUp() throws Exception {
        Connect.generateCache();
        cliente1 = new Cliente("Ivete Sobrenome", "Rua ABC, Bahia",
                "855.332.800-73", 75);

        cliente2 = new Cliente("Isabel Sobrenome", "Rua XYZ, Bahia",
                "638.989.410-43", 81);

    }
    @AfterEach
    public void tearDown() throws Exception {
        DAO.getClienteDAO().deleteMany();
    }

    @Test
    public void create() throws Exception {
        // Cliente criado para verificar se ele adiciona um cliente que já existe
        Cliente teste;
        DAO.getClienteDAO().create(cliente1);
        assertNotNull(DAO.getClienteDAO().findByCPF("855.332.800-73"));

        //verifica se o ID foi realmente criado
        assertNotNull(cliente1.getId());

        //teste se o sistema permite criar um cliente, ja cadastrado como tecnico
        Tecnico tecnico1 = new Tecnico("Amanda Sobrenome", "Rua XYZ, Bahia",
                "638.989.410-43", 81);
        Tecnico testeTecnico;
        testeTecnico = DAO.getTecnicoDAO().create(tecnico1);
        //se retornar null funcionou
        assertNull(DAO.getClienteDAO().create(cliente2));
        DAO.getTecnicoDAO().delete(testeTecnico.getCpf());

        teste = DAO.getClienteDAO().create(cliente1);

        // se retornar null, significa que o sistema nao adiciona o cliente ja existente
        assertNull(teste);
        DAO.getClienteDAO().create(cliente2);
        // Verifica se tem a quantidade de clientes esperada.
        assertEquals(2, DAO.getClienteDAO().amountItems());
    }

    @Test
    public void update() throws Exception {
        DAO.getClienteDAO().create(cliente1);
        cliente1.setFullName("Joana novo Sobrenome");
        DAO.getClienteDAO().update(cliente1);
        assertEquals("Joana novo Sobrenome", DAO.getClienteDAO().findByCPF("855.332.800-73").getFullName());
    }

    @Test
    public void delete() throws Exception {
        DAO.getClienteDAO().create(cliente1);
        DAO.getClienteDAO().delete("855.332.800-73");
        assertEquals(0, DAO.getClienteDAO().amountItems());
    }

    @Test
    public void findByCPF() throws Exception {
        DAO.getClienteDAO().create(cliente1);
        Cliente testeEncontrar = DAO.getClienteDAO().findByCPF("855.332.800-73");
        assertEquals(cliente1, testeEncontrar);

    }

    @Test
    public void findByCpfIsTrue() throws Exception {
        DAO.getClienteDAO().create(cliente1);
        DAO.getClienteDAO().create(cliente2);
        // Teste com um cpf de um cliente presente na lista.
        assertTrue(DAO.getClienteDAO().findByCpfIsTrue("855.332.800-73"));
        //Teste com um cpf de um cliente NÃO presente na lista.
        assertFalse(DAO.getClienteDAO().findByCpfIsTrue("111.123.456.10"));
    }

    @Test
    public void findIdbyCPF() throws Exception {
        DAO.getClienteDAO().create(cliente1);
        DAO.getClienteDAO().create(cliente2);
        String id1 = cliente1.getId();

        //Verificar primeiro com um cliente presente no sistema.
        assertEquals(id1, DAO.getClienteDAO().findIdbyCPF("855.332.800-73"));
        // Verificar se a exception foi lançada pelo método.
        try {
            DAO.getClienteDAO().findIdbyCPF("111.123.456.10");
            fail("Erro, pois a exception não foi lançada");
        } catch (IllegalArgumentException excep) {
            assertEquals("Cliente não detectado no banco de dados", excep.getMessage());
        }

    }
}