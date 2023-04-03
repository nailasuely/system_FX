package com.example.sistema_gerenciamentofx.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TecnicoTest {
    @Test
    void getFullName() {
        Tecnico tecnico1 = new Tecnico();
        tecnico1.setFullName("Maria");
        assertEquals("Maria", tecnico1.getFullName());
    }

    @Test
    void setFullName() {
        Tecnico tecnico1 = new Tecnico();

        tecnico1.setFullName("Maria");
        assertEquals("Maria", tecnico1.getFullName());

        tecnico1.setFullName(null);
        assertNull(tecnico1.getFullName());
    }
    @Test
    void addServiceOrder() {
    }

    @Test
    void finalizeServiceOrder() {
    }
}