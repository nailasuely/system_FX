package com.example.sistema_gerenciamentofx.model;
import com.example.sistema_gerenciamentofx.dao.DAO;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
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
    Este método é responsável por adicionar serviços para o técnico.
    Para garantir que um técnico só possa aceitar uma nova ordem de
    serviço quando todas as suas ordens anteriores já estiverem
    finalizadas, é realizada uma verificação.

    Primeiro, é verificado se a lista de ordens está vazia.
    Se sim, a nova ordem é adicionada. Caso contrário,
    um loop for percorre a lista para verificar se todas
    as ordens foram finalizadas. Se sim, a nova ordem é adicionada.
    *//**
    @param servico Objeto do tipo OrdemServico, que contém as informações de uma nova ordem de serviço criada.
    @param tecID ID do técnico usado para realizar buscas na base de dados do sistema.
    @return Valor booleano indicando se o técnico pode pegar a nova ordem de serviço ou se precisa ser passada para outro técnico, caso o retorno seja falso.
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
                if (servicos.getStatus().equals("Finalizado")){
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
    /**
     * Método para poder obter a lista de Ordens de Serviço associadas àquele tecnico
     * @return Uma <i>ArrayList</i> contendo objetos do tipo <i>OrdemServico</i>
     */
    public static ArrayList<OrdemServico> getServiceOrders() {
        return serviceOrders;
    }


    /**
     * Método para poder gerar relátorios gerais ao sistema, contendo informações com médias de todos os valores obtidos ao
     * longo do funcionamento do programa.<br>
     * Dentre as informações presentes nesse relátorio:
     * <ul>
     *     <li>
     *         <b>Quantidade de serviços finalizados</b> - serviços que ja ficaram prontos
     *     </li>
     *     <li>
     *         <b>Quantidade de serviços em andamento</b> - serviços que estão sendo realizados no momento
     *     </li>
     *     <li>
     *         <b>Quantidade de serviços em espera</b> - serviços cadastrados que estão na fila para serem realizados
     *     </li>
     *     <li>
     *         <b>Faturamento obtido</b> - representa o valor total que entrou em caixa a partir da realização de ordens de serviço
     *     </li>
     *     <li>
     *         <b>Media de dias para realização de uma ordem</b> - média de tempo entre o inicio da ordem, até ela receber o status de finalizada
     *     </li>
     *     <li>
     *         <b>Média de satisfação</b> - media das satisfações dadas pelos clientes com relação aos serviços prestados
     *     </li>
     * </ul>
     *
     * @return <i>String</i> contendo as informações citadas acima
     */
    public String gerarRelatorioFinal() throws Exception{
        int qntServicosFinalizados = 0;
        int qntServicosAndamento = 0;
        int qntServicosEspera = 0;
        double totalObtido = 0;
        double sastifacao = 0;
        double mediaSastifacao = 0;
        long quantidadeDias = 0;
        long mediaQuantidadeDias = 0;




        for(OrdemServico servico: DAO.getOrdemServicoDAO().getList()){
            if (servico.getStatus().equals("finalizada")) {
                quantidadeDias = ChronoUnit.DAYS.between(servico.getStart(), servico.getEnd());
                qntServicosFinalizados += 1;
                totalObtido += servico.getPrice();
                sastifacao += servico.getClientSatisfaction();
            }
            else if(servico.getStatus().equals("andamento")){
                qntServicosAndamento += 1;
            }
            else if(servico.getStatus().equals("espera")){
                qntServicosEspera += 1;
            }
            }
        if (qntServicosFinalizados > 0){
            mediaSastifacao = sastifacao / qntServicosFinalizados;
            mediaQuantidadeDias = quantidadeDias / qntServicosFinalizados;

        }
        return "Quantidade de serviços finalizados: "+ qntServicosFinalizados +
               "\nQuantidade de serviços em andamento: "+ qntServicosAndamento +
               "\nQuantidade de serviços em espera: "+ qntServicosEspera +
               "\nTotal de faturamento obtido: "+ totalObtido +
               "\nQuantidade média de dias de ordem aberta: "+ mediaQuantidadeDias +
               "\nMédia de sastifação: "+ mediaSastifacao;
    }

    /**
     * Método para obter uma impressão personalizada ao utilizar o comando <i>System.out.println(tecnico);</i> em que "tecnico" é uma instância da classe Tecnico
     * @return String contendo o nome e o id armazenados no objeto
     */
    @Override
    public String toString() {
        return "\nNome do Tecnico: "+ getFullName() + "\nID do tecnico: "+ getId() + "\nCPF: "+ getCpf() + "\nEndereço: "+ getAddress();
    }
}
