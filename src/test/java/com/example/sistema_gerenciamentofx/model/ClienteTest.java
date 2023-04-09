package com.example.sistema_gerenciamentofx.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    Cliente cliente1;
    @BeforeEach
    void setUp() {
        cliente1 = new Cliente();
    }


    // Teste para verificar o funcionamento do get e do set do atributo FullName.
    @Test
    void setFullName() {
        cliente1.setFullName("Julia");
        assertEquals("Julia", cliente1.getFullName());

        cliente1.setFullName(null);
        assertNull(cliente1.getFullName());
    }

    // Teste para verificar o funcionamento do get e do set do atributo ID.
    @Test
    void setId() {
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        cliente1.setId(newIDStrign);
        assertEquals(newIDStrign, cliente1.getId());
    }
    @Test
    void setAddress(){
        cliente1.setAddress("Rua ABC, Bahia");
        assertEquals("Rua ABC, Bahia", cliente1.getAddress());

        cliente1.setAddress(null);
        assertNull(cliente1.getAddress());

    }
    @Test
    void getCpf() {
        cliente1.setCpf("123.456.789-00");
        assertEquals("123.456.789-00", cliente1.getCpf());

        cliente1.setCpf(null);
        assertNull(cliente1.getCpf());

    }
    @Test
    void getTelephone() {
        cliente1.setTelephone(12345678);
        assertEquals(12345678, cliente1.getTelephone());
    }
}