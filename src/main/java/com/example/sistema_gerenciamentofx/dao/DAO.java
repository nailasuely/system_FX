package com.example.sistema_gerenciamentofx.dao;

import com.example.sistema_gerenciamentofx.dao.cliente.ClienteDAO;
import com.example.sistema_gerenciamentofx.dao.cliente.ListClientes;

public class DAO {
    private static ClienteDAO clienteDAO;

    public static ClienteDAO getClienteDAO(){
        if(clienteDAO == null){
            clienteDAO = new ListClientes();
        }
        return clienteDAO;
    }


}
