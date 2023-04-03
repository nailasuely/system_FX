package com.example.sistema_gerenciamentofx.dao;

import com.example.sistema_gerenciamentofx.dao.cliente.ClienteDAO;
import com.example.sistema_gerenciamentofx.dao.cliente.ListClientes;
import com.example.sistema_gerenciamentofx.dao.estoque.EstoqueDAO;
import com.example.sistema_gerenciamentofx.dao.estoque.ListEstoque;
import com.example.sistema_gerenciamentofx.dao.ordemServico.ListOrdensServico;
import com.example.sistema_gerenciamentofx.dao.ordemServico.OrdemServicoDAO;
import com.example.sistema_gerenciamentofx.dao.tecnico.ListTecnicos;
import com.example.sistema_gerenciamentofx.dao.tecnico.TecnicoDAO;

public class DAO {
    private static ClienteDAO clienteDAO;
    private static TecnicoDAO tecnicoDAO;
    private static OrdemServicoDAO ordemServicoDAO;

    private static EstoqueDAO estoqueDAO;

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
    public static OrdemServicoDAO getOrdemServicoDAO(){
        if(ordemServicoDAO == null){
            ordemServicoDAO = new ListOrdensServico();
        }
        return ordemServicoDAO;
    }

    public static EstoqueDAO getEstoqueDAO(){
        if(estoqueDAO == null){
            estoqueDAO = new ListEstoque();
        }
        return estoqueDAO;
    }



}
