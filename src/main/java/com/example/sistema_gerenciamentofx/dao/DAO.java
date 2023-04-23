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
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class DAO {
    /**
     * O atributo <b>clienteDAO</b> é do tipo <i>ClienteDAO</i>, e serve para armazenar e manipular, no armazenamento, os dados do tipo <i>Cliente</i>
     */
    private static ClienteDAO clienteDAO;
    /**
     * O atributo <b>tecnicoDAO</b> é do tipo <i>TecnicoDAO</i>, e serve ara armazenar e manipular, no armazenamento, os dados do tipo <i>Tecnico</i>
     */
    private static TecnicoDAO tecnicoDAO;
    /**
     * O atributo <b>ordemServicoDAO</b> é do tipo <i>OrdemServicoDAO</i>, e serve ara armazenar e manipular, no armazenamento, os dados do tipo <i>OrdemServico</i>
     */
    private static OrdemServicoDAO ordemServicoDAO;
    /**
     * O atributo <b>estoqueDAO</b> é do tipo <i>EstoqueDAO</i>, e serve ara armazenar e manipular os dados do tipo <i>Estoque</i>
     */
    private static EstoqueDAO estoqueDAO;

    /**
     * Método para poder obter o atributo "clienteDAO", o qual dentro dele contém os objetos armazenados, assim como métodos especificos
     * para lidar com esses dados dos clientes.
     * @return Objeto do tipo <i>ClienteDAO</i> que contém os clientes armazenados
     */
    public static ClienteDAO getClienteDAO() throws Exception{
        if(clienteDAO == null){
            clienteDAO = new ListClientes();
        }
        return clienteDAO;
    }

    /**
     * Método para poder obter o atributo "tecnicoDAO", o qual dentro dele contém os objetos armazenados, assim como métodos especificos
     * para lidar com esses dados dos tecnicos.
     * @return Objeto do tipo <i>TecnicoDAO</i> que contém os tecnicos armazenados
     */
    public static TecnicoDAO getTecnicoDAO() throws Exception{
        if(tecnicoDAO == null){
            tecnicoDAO = new ListTecnicos();
        }
        return tecnicoDAO;
    }
    /**
     * Método para poder obter o atributo "ordemServicoDAO", o qual dentro dele contém os objetos armazenados, assim
     * como métodos especifico para lidar com esses dados das ordens de serviço
     * @return Objeto do tipo <i>OrdemServiçoDAO</i> que contém as Ordens de Serviço armazenados
     */
    public static OrdemServicoDAO getOrdemServicoDAO() throws Exception{
        if(ordemServicoDAO == null){
            ordemServicoDAO = new ListOrdensServico();
        }
        return ordemServicoDAO;
    }

    /**
     * Método para poder obter o atributo "estoqueDAO", o qual dentro dele contém os objetos armazenados, assim como métodos especificos
     * para lidar com esses dados a respeito do estoque.
     * @return Objeto do tipo <i>EstoqueDAO</i> que contém os produtos armazenados
     */
    public static EstoqueDAO getEstoqueDAO() throws Exception{
        if(estoqueDAO == null){
            estoqueDAO = new ListEstoque();
        }
        return estoqueDAO;
    }



}
