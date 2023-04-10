package com.example.sistema_gerenciamentofx.dao.tecnico;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ListTecnicosTest {
    private static Tecnico tecnico1;
    private static Tecnico tecnico2;


    @BeforeEach
    void setUp() {
        tecnico1 = new Tecnico("Rhian Sobrenome", "Coité, Bahia",
                "123.789.101-10", 75);


        tecnico2 = new Tecnico("João Sobrenome", "Rua XYZ, Bahia",
                "456.789.101-10", 81);

    }
    @AfterEach
    void tearDown() {
        DAO.getTecnicoDAO().deleteMany();
    }

    @Test
    void create() {
        Tecnico teste;
        DAO.getTecnicoDAO().create(tecnico1);
        assertNotNull(DAO.getTecnicoDAO().findByCPF("123.789.101-10"));

        //verifica se o ID foi realmente criado
        assertNotNull(tecnico1.getId());

        teste = DAO.getTecnicoDAO().create(tecnico1);

        Cliente teste1 = new Cliente("João Sobrenome", "Rua XYZ, Bahia",
                "456.789.101-10", 81);
        Cliente testeConflito;
        testeConflito = DAO.getClienteDAO().create(teste1);
        assertNull(DAO.getTecnicoDAO().create(tecnico2));
        DAO.getClienteDAO().delete(testeConflito.getCpf());

        // se retornar null, significa que o sistema nao adiciona o cliente ja existente
        assertNull(teste);
        DAO.getTecnicoDAO().create(tecnico2);
        // Verifica se tem a quantidade de clientes esperada.
        assertEquals(2, DAO.getTecnicoDAO().amountItems());
    }

    @Test
    void update() {
        DAO.getTecnicoDAO().create(tecnico1);
        tecnico1.setFullName("Joana novo Sobrenome");
        DAO.getTecnicoDAO().update(tecnico1);
        assertEquals("Joana novo Sobrenome", DAO.getTecnicoDAO().findByCPF("123.789.101-10").getFullName());
    }

    @Test
    void delete() {
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getTecnicoDAO().delete("123.789.101-10");
        assertEquals(0, DAO.getTecnicoDAO().amountItems());
    }


    @Test
    void deleteMany() {
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getTecnicoDAO().create(tecnico2);
        DAO.getTecnicoDAO().deleteMany();
        assertEquals(0, DAO.getTecnicoDAO().amountItems());
    }

    @Test
    void amountItems() {
        int amount;
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getTecnicoDAO().create(tecnico2);
        amount = DAO.getTecnicoDAO().amountItems();
        assertEquals(2, amount);

    }

    @Test
    void findByCPF() {
        DAO.getTecnicoDAO().create(tecnico1);
        Tecnico testeEncontrar = DAO.getTecnicoDAO().findByCPF("123.789.101-10");
        assertEquals(tecnico1, testeEncontrar);
    }

    @Test
    void findByCPFIsTrue() {
        Tecnico teste;
        boolean testando;
        DAO.getTecnicoDAO().create(tecnico1);
        assertTrue(DAO.getTecnicoDAO().findByCPFIsTrue("123.789.101-10"));




    }
}