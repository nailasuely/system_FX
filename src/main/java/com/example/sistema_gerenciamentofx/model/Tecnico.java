package com.example.sistema_gerenciamentofx.model;
import java.util.ArrayList;

/**
 * Subclasse que herda os atributos e métodos da classe Pessoa<br>
 * Utilizada para:
 * <ul>
 *     <li>
 *         separar o cadastro de Tecnico e Cliente no sistema, e no armazenamento dos dados
 *     </li>
 *     <li>
 *         poder atribuir, e associar, ordens de serviço a ele
 *     </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class Tecnico extends Pessoa {
    /**
     * O atributo <b>serviceOrders</b> é uma lista que contém as ordens de serviço atribuidas ao tecnico que ainda serão realizadas, ou está em andamento
     */
    private static ArrayList<OrdemServico> serviceOrders;

    /**
     * Declaração do método construtor default da classe
     * Uso:<br>
     * Tecnico tecnico = new Tecnico();
     */
    public Tecnico(){}

    /**
     * Declaração do método construtor sobrecarregado da classe<br>
     * Esse método já é responsavel por iniciar o atributo <b>serviceOrders</b>
     * @param nome String para armazenar o nome completo da pessoa
     * @param address String para armazenar o endereço da pessoa
     * @param cpf String para armazenar o cpf da pessoa
     * @param telephone String para armazenar o telefone da pessoa
     */
    public Tecnico(String nome, String address, String cpf, int telephone) {
        super(nome, address, cpf, telephone);
        serviceOrders = new ArrayList<>();
    }

    /*
    Esse método é responsável por adicionar serviços para o tecnico. Dessa forma, pensando
    no requisito que um tecnico só pode aceitar uma ordem quando todas as suas ordens anteriores já
    estiverem finalizadas, existe uma verificação.
    Primeiro, é verificado se a lista de ordens está vazia. Nesse caso, a ordem é adiconada. Caso
    contrário, um for percorre a lista para verificar se todas ordens estão finalizadas. Se sim, a nova
    ordem é adicionada.
     */

    /*REVER LOGICA
    pelo q tinhamos visto na aula do PBL falou q pode add, mas nao pode por o status em andamento
     */

    /**
     * Método para realizar verificação se o tecnico pode adicionar uma ordem de serviço a sua lista<br>
     *
     * @param servico Objeto do tipo <i>OrdemServico</i>, que contém as informações de uma ordem de serviço criada
     * @param tecID String para receber o id do tecnico e então realizar procuras na base de dados do sistema
     * @return True ou False, a depender se o tecnico pode pegar aquela ordem de serviço para si, ou precisa passar para outro tecnico
     */
    public boolean addServiceOrder(OrdemServico servico, String tecID) {
        int quantidadeServicos = serviceOrders.size();
        int servicosFinalizados = 0;
        if (serviceOrders.isEmpty()){
             servico.setTechnicianID(tecID);
             serviceOrders.add(servico);

             return true;}
        else{
            for(OrdemServico servicos: serviceOrders){
                if (servicos.getStatus() == "Finalizado"){
                    servicosFinalizados += 1;
                }
            }
            if(quantidadeServicos == servicosFinalizados){
                servico.setTechnicianID(tecID);
                serviceOrders.add(servico);
                return true;
            }
         }
        return false;

    }
    public static ArrayList<OrdemServico> getServiceOrders() {
        return serviceOrders;
    }

    public void finalizeServiceOrder(OrdemServico ordem){

    }


    /**
     * Método para obter uma impressão personalizada ao utilizar o comando <i>System.out.println(tecnico);</i> em que "tecnico" é uma instância da classe Tecnico
     * @return String contendo o nome e o id armazenados no objeto
     */
    @Override
    public String toString() {
        return "Nome do Tecnico: "+ getFullName() + "\nID do tecnico: "+ getId();
    }
}
