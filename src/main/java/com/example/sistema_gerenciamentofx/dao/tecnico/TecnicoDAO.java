package com.example.sistema_gerenciamentofx.dao.tecnico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;

public interface TecnicoDAO extends CRUD<Tecnico> {
    public Tecnico findByCPF(String cpf);
    public boolean findByCPFIsTrue(String cpf);
}
