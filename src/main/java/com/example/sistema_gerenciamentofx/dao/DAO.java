package com.example.sistema_gerenciamentofx.dao;

import com.example.sistema_gerenciamentofx.dao.cliente.ClienteDAO;
import com.example.sistema_gerenciamentofx.dao.cliente.ListClientes;
import com.example.sistema_gerenciamentofx.dao.estoque.EstoqueDAO;
import com.example.sistema_gerenciamentofx.dao.estoque.ListEstoque;
import com.example.sistema_gerenciamentofx.dao.ordemServico.ListOrdensServico;
import com.example.sistema_gerenciamentofx.dao.ordemServico.OrdemServicoDAO;
import com.example.sistema_gerenciamentofx.dao.tecnico.ListTecnicos;
import com.example.sistema_gerenciamentofx.dao.tecnico.TecnicoDAO;
/**
 * Está classe representa uma especie de contrato, realizando o intermedio entre a base de dados, e as partes a qual necessitam desses dados.<br>
 * A partir dos métodos presentes nesta classe, é possivel acessar ao armazenamento, seja dos clientes, dos tecnicos, das ordens de serviço, ou do estoque.<br>
 * Esses métodos podem ser utilizados em qualquer parte do programa, facilitando assim o acesso aos dados que estão armazenados,
 * independente a forma de armazenamento utilizada, bastando para isso importar esta classe <i><b>DAO</b></i>.
 */
public class DAO {
    /**
     * O atributo <b>clienteDAO</b> é do tipo <i>ClienteDAO</i> e leva
     */
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
