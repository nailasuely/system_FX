package com.example.sistema_gerenciamentofx.dao.cliente;
import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Está classe é parte da forma de armazenamento de dados, sendo utilizado lista para tal, nessa classe em que utiliza o padrão DAO.<br>
 * Está salva uma lista de clientes, e contém métodos, padrões ao CRUD (Create, Read, Update, Delete), além de outros
 * métodos necessários para desenvolvimento das funções do sistema dentre eles:<ul>
 *     <li>
 *         <b>findById</b> - procurar o cliente a partir do seu ID
 *     </li>
 *     <li>
 *         <b>findByCPF</b> - procurar o cliente a partir do seu CPF
 *     </li>
 *     <li>
 *         <b>findByCpfIsTrue</b> - verificar se aquele CPF existe no sistema
 *     </li>
 *     <li>
 *         <b>findIdbyCPF</b> -  procurar o ID do cliente, partindo do seu CPF
 *     </li>
 * </ul>
 *
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class ListClientes implements ClienteDAO{
    /**
     * O atributo <b>listaClientes</b> é do tipo <i>List</i>, e armazena a lista contendo objetos do tipo <i>Cliente</i>
     */
    private List<Cliente> listaClientes;

    /**
     * Método construtor da classe, em que inicializa a lista que irá conter os clientes do sistema
     */
    public ListClientes() throws Exception{
        this.listaClientes = new ArrayList<Cliente>();
        this.listaClientes = Connect.openCliente();
    }

    /**
     * Método para criar e adicionar o cadastro de um novo cliente ao sistema de dados.<br>
     * Antes de salvar o objeto contendo as informações do cliente, existem verificações para evitar problemas no sistema,
     * dentre as verificações:<ul>
     *     <li>
     *         Verificação para não criar um cliente ja existente - utiliza o CPF para isso
     *     </li>
     *     <li>
     *         Verificação para o cliente não estar, já cadastrado como um tecnico
     *     </li>
     * </ul>
     * Ainda é gerado um ID aleatorio, pela biblioteca UUID, para preenchimento do ID do cliente, e após feito o set do atributo <b>ID</b> é então salvo na lista
     * @param cliente Objeto do tipo <i>Cliente</i> contendo os atributos necessários para o cadastro, excluindo o ID, que é gerado internamente
     * @return Objeto do tipo <i>Cliente</i> que contém agora o ID preenchido.<br>
     */
    @Override
    public Cliente create(Cliente cliente) throws Exception{

        //VERIFICAÇÃO PARA NAO CRIAR CLIENTE JA EXISTENTE
        if (findByCpfIsTrue(cliente.getCpf()) | DAO.getTecnicoDAO().findByCPFIsTrue(cliente.getCpf())) {
            //POR TRATATIVA PARA ISSO, SE O RETORNO AQUI FOR NULL

            return null;
        }
        else {
            // Gerar id pseudoaleatório;
            UUID newID = UUID.randomUUID();
            String newIDStrign = newID.toString();
            //lembrar de verificar dps;
            cliente.setId(newIDStrign);

            this.listaClientes.add(cliente);
            Connect.saveCliente(this.listaClientes);
            return cliente;
        }
    }

    /**
     * Método para poder atualizar o cliente já registrado no sistema, contendo novas informações sobre este.<br>
     * Caso o cliente passado não esteja no banco de dados é gerado uma exceção
     * @param cliente Objeto do tipo <i>Cliente</i> para ser trocar de lugar o objeto anterior com informações antigas, e adicionar o novo atualizado.
     */
    @Override
    public void update(Cliente cliente) throws Exception{
        boolean encontrado = false;
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getId() == cliente.getId()) {
                this.listaClientes.set(i, cliente);
                encontrado = true;
                Connect.saveCliente(this.listaClientes);
                return;
            }
        }
        throw new IllegalArgumentException("Cliente não detectado no banco de dados");

    }

    /**
     * Método para poder deletar o cliente da base de dados, partindo do CPF do cliente
     * @param cpf <i>String</i> contendo o número do CPF do cliente para encontra-lo, e poder realizar a operação.<br>
     *            Caso não seja encontrado o cliente, é gerado uma exceção
     */
    @Override
    public void delete(String cpf) throws Exception{
        boolean encontrado = false;
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getCpf().equals(cpf)) {
                this.listaClientes.remove(i);
                encontrado = true;
                Connect.saveCliente(this.listaClientes);
                return;
            }
        }
        if (encontrado == false) {
            throw new IllegalArgumentException("Cliente não detectado no banco de dados");
        }
    }

    /**
     * Método para procurar o cliente partindo da informação do seu ID
     * @param id <i>String</i> contendo o ID que foi associado ao cliente
     * @return Objeto do tipo <i>Cliente</i>, que foi encontrado no sistema, a partir do ID passado
     */
    @Override
    public Cliente findById(String id) {
        for (Cliente cliente : this.listaClientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Método realiza a impressão dos clientes cadastrados presentes na lista deles.<br>
     * Impressão segue o modelo: "ID do cliente: id referente ao tecnico"
     */
    @Override
    public void listObjects() {
        for (Cliente cliente : this.listaClientes) {
            System.out.println("ID do cliente: " + cliente.getId());
        }
    }

    /**
     * Método para encontrar o cliente no sistema a partir do seu CPF
     * @param cpf <i>String</i> contendo o número do cpf do cliente que deseja encontrar
     * @return Objeto do tipo <i>Cliente</i> encontrado no sistema.<br>
     * Em caso de não encontrar o retorno é nulo
     */
    @Override
    public Cliente findByCPF(String cpf) {
        for (Cliente cliente : this.listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Método para verificar a existencia do CPF de um cliente no sistema
     * @param cpf <i>String</i> contendo o número do cpf do cliente que deseja se verificar
     * @return Booleano, em que se o CPF existe no sistema é retornado <b><i>True</i></b>, e no caso contrário, <b><i>False</i></b>
     */
    @Override
    public boolean findByCpfIsTrue(String cpf) {
        for(Cliente cliente: this.listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para encontrar o ID do cliente, a partir da informação do seu CPF
     * @param CPF <i>String</i> contendo o número do cpf do cliente que deseja encontrar o ID
     * @return <i>String</i> contendo o ID do cliente, no caso do cliente existir no sistema, caso o mesmo não exista
     * é gerado uma exceção
     */
    public String findIdbyCPF(String CPF){
        try{
            for(Cliente cliente: this.listaClientes){
                if(cliente.getCpf().equals(CPF)){
                    return cliente.getId();
                }
            }
            return null;
        }catch (Exception e){
            throw new IllegalArgumentException("Cliente não detectado no banco de dados");
        }


    }

    /**
     * Método para poder obter a lista de clientes salva no sistema
     * @return List contendo objetos do tipo <i>Cliente</i>
     */
    @Override
    public List<Cliente> getList() {
        return this.listaClientes;
    }

    /**
     * Método para deletar todos os clientes presentes no sistema, logo a lista de clientes se torna vazia
     */
    @Override
    public void deleteMany() throws Exception{
        this.listaClientes = new ArrayList<>();
        Connect.saveCliente(this.listaClientes);
    }

    /**
     * Método para verificar a quantidade de clientes cadastrados no sistema
     * @return <i>Int</i> contendo o tamanho da lista de clientes
     */
    public int amountItems() {
        return listaClientes.size();
    }

}
