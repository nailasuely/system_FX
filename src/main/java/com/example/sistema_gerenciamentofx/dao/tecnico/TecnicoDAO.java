package com.example.sistema_gerenciamentofx.dao.tecnico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.util.List;

public interface TecnicoDAO extends CRUD<Tecnico> {
    public Tecnico findByCPF(String cpf);
    public boolean findByCPFIsTrue(String cpf);
    public String findIdbyCPF(String CPF);
    public String balanceamento();
    public List<Tecnico> getList();
}
