package com.example.sistema_gerenciamentofx.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setFullName() {
        // fazer o public de cliente depois.
        Cliente cliente1 = new Cliente();

        cliente1.setFullName("Julia");
        assertEquals("Julia", cliente1.getFullName());

        cliente1.setFullName(null);
        assertNull(cliente1.getFullName());
    }
}