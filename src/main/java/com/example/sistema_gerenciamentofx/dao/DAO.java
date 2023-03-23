package com.example.sistema_gerenciamentofx.dao;

import com.example.sistema_gerenciamentofx.dao.cliente.ClienteDAO;
import com.example.sistema_gerenciamentofx.dao.cliente.ListClientes;
import com.example.sistema_gerenciamentofx.dao.tecnico.ListTecnicos;
import com.example.sistema_gerenciamentofx.dao.tecnico.TecnicoDAO;

public class DAO {
    private static ClienteDAO clienteDAO;
    private static TecnicoDAO tecnicoDAO;

    public static ClienteDAO getClienteDAO(){
        if(clienteDAO == null){
            clienteDAO = new ListClientes();
        }
        return clienteDAO;
    }

    public static TecnicoDAO getTecnicoDAO(){
        if(tecnicoDAO == null){
            tecnicoDAO = new ListTecnicos();
        }
        return tecnicoDAO;
    }




}
