package com.example.sistema_gerenciamentofx.dao.cliente;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.Cliente;

public interface ClienteDAO extends CRUD<Cliente> {
    public Cliente findByCPF(String cpf);
}
