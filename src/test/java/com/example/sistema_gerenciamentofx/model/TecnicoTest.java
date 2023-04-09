package com.example.sistema_gerenciamentofx.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TecnicoTest {
    Tecnico tecnico1;

    @BeforeEach
    void setUp() {
        tecnico1 = new Tecnico();
    }

    @Test
    void getFullName() {
        tecnico1.setFullName("Maria");
        assertEquals("Maria", tecnico1.getFullName());
    }

    @Test
    void setFullName() {
        tecnico1.setFullName("Maria");
        assertEquals("Maria", tecnico1.getFullName());

        tecnico1.setFullName(null);
        assertNull(tecnico1.getFullName());
    }

    @Test
    void setId() {
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        tecnico1.setId(newIDStrign);
        assertEquals(newIDStrign, tecnico1.getId());
    }
    @Test
    void setAddress(){
        tecnico1.setAddress("Rua ABC, Bahia");
        assertEquals("Rua ABC, Bahia", tecnico1.getAddress());

        tecnico1.setAddress(null);
        assertNull(tecnico1.getAddress());

    }
    @Test
    void getCpf() {
        tecnico1.setCpf("123.456.789-00");
        assertEquals("123.456.789-00", tecnico1.getCpf());

        tecnico1.setCpf(null);
        assertNull(tecnico1.getCpf());

    }
    @Test
    void getTelephone() {
        tecnico1.setTelephone(12345678);
        assertEquals(12345678, tecnico1.getTelephone());
    }


    @Test
    void addServiceOrder() {
        tecnico1 = new Tecnico("Maria Sobrenome", "Rua ABC, Bahia", "123.456.789.-00", 12345678);
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
    void finalizeServiceOrder() {
    }
}