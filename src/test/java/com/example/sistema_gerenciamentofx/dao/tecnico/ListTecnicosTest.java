package com.example.sistema_gerenciamentofx.dao.tecnico;

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

public class ListTecnicosTest {
    private static Tecnico tecnico1;
    private static Tecnico tecnico2;


    @BeforeEach
    public void setUp() throws Exception{
        Connect.generateCache();
        tecnico1 = new Tecnico("Italo Sobrenome", "Coité, Bahia",
                "196.814.670-94", 75);


        tecnico2 = new Tecnico("Maria Sobrenome", "Rua XYZ, Bahia",
                "399.048.730-20", 81);

    }
    @AfterEach
    public void tearDown() throws Exception {
        DAO.getTecnicoDAO().deleteMany();
    }

    @Test
    public void create() throws Exception{
        DAO.getTecnicoDAO().deleteMany();
        Tecnico teste;
        DAO.getTecnicoDAO().create(tecnico1);
        assertNotNull(DAO.getTecnicoDAO().findByCPF("196.814.670-94"));

        //verifica se o ID foi realmente criado
        assertNotNull(tecnico1.getId());

        teste = DAO.getTecnicoDAO().create(tecnico1);

        Cliente teste1 = new Cliente("João Sobrenome", "Rua XYZ, Bahia",
                "399.048.730-20", 81);
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
    public void update() throws Exception {
        DAO.getTecnicoDAO().create(tecnico1);
        tecnico1.setFullName("Joana novo Sobrenome");
        DAO.getTecnicoDAO().update(tecnico1);
        assertEquals("Joana novo Sobrenome", DAO.getTecnicoDAO().findByCPF("196.814.670-94").getFullName());
    }

    @Test
    public void delete() throws Exception {
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getTecnicoDAO().delete("196.814.670-94");
        assertEquals(0, DAO.getTecnicoDAO().amountItems());
    }


    @Test
    public void deleteMany() throws Exception {
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getTecnicoDAO().create(tecnico2);
        DAO.getTecnicoDAO().deleteMany();
        assertEquals(0, DAO.getTecnicoDAO().amountItems());
    }

    @Test
    public void amountItems() throws Exception {
        int amount;
        DAO.getTecnicoDAO().create(tecnico1);
        DAO.getTecnicoDAO().create(tecnico2);
        amount = DAO.getTecnicoDAO().amountItems();
        assertEquals(2, amount);

    }

    @Test
    public void findByCPF() throws Exception {
        DAO.getTecnicoDAO().create(tecnico1);
        Tecnico testeEncontrar = DAO.getTecnicoDAO().findByCPF("196.814.670-94");
        assertEquals(tecnico1, testeEncontrar);
    }

    @Test
    public void findByCPFIsTrue() throws Exception {
        Tecnico teste;
        boolean testando;
        DAO.getTecnicoDAO().create(tecnico1);
        assertTrue(DAO.getTecnicoDAO().findByCPFIsTrue("196.814.670-94"));

    }
}