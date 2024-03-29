package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.conexao.Connect;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Está classe é parte da forma de armazenamento de dados, sendo utilizado lista para tal, nessa classe em que utiliza o padrão DAO.<br>
 * Está salva uma lista de Ordens de Serviço, e contém métodos, padrões ao CRUD (Create, Read, Update, Delete), além de outros
 * métodso necessários para desenvolvimento das funções do sistema dentre eles:<ul>
 *     <li>
 *         <b>openOrderByTechnician</b> - devolve a ordem em aberto de determinado tecnico a partir do cpf do mesmo.
 *     </li>
 *     <li>
 *          <b>atualizarStatusAndamento</b> - para atualizar o status da ordem de serviço para "em andamento".
 *     </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class ListOrdensServico implements OrdemServicoDAO{
    /**
     * O atributo <b>listaOrdensServico</b> é do tipo <i>List</i>, e armazena a lista contendo objetos do tipo <i>OrdemServico</i>
     */
    private List<OrdemServico> listaOrdensServico;
    /**
     * O atributo <b>indiceClienteParaAtender</b> é do tipo <i>Int</i>
     */
    private int indiceClienteParaAtender = Connect.openIndice();

    /**
     * Método construtor da classe, em que inicializa a lista que irá conter as ordens de serviço do sistema
     */
    public ListOrdensServico() throws Exception{
        this.listaOrdensServico = new ArrayList<OrdemServico>();
        this.listaOrdensServico = Connect.openOrdens();
    }

    /**
     * Método para poder criar e adicionar uma nova ordem de serviço ao sistema de dados.<br>
     * Nesse momento de criação alguns atributos são preenchidos automaticamente:<ul>
     *     <li>
     *         Data de inicio da ordem de serviço
     *     </li>
     *     <li>
     *         ID da ordem gerado aleatoriamente pela biblioteca UUID
     *     </li>
     * </ul>
     * @param ordem
     * @param clienteID
     * @param type
     * @return
     */
    @Override
    public OrdemServico create(OrdemServico ordem, String clienteID, Produto type) throws Exception{
        LocalDate inicio = LocalDate.now();
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        //lembrar de verificar dps;
        ordem.setId(newIDStrign);
        ordem.setStart(inicio);
        ordem.setClientId(clienteID);
        ordem.setType(type);
        this.listaOrdensServico.add(ordem);
        Connect.saveOrder(this.listaOrdensServico);
        return ordem;
    }

    /**
     * Método para poder atualizar o status da ordem de serviço, colocando ela com o status "em andamento", e com isso
     * tira da fila de espera.<br>
     * Caso ocorra do Tecnico não poder aceitar aquela ordem, é gerado uma exceção.
     * @param cpfTecnico <i>String</i> contendo o CPF do tecnico o qual deseja procurar a ordem na fila de espera
     * @param ordem Objeto do tipo <i>OrdemServico</i> para ser atualizada
     */
    public void atualizarStatusAndamento(String cpfTecnico, OrdemServico ordem) throws Exception{
       if (listaOrdensServico != null){
           // Aqui o método addservideorder retorna true se o tecnico está com a lista
           // de serviços vazia ou todas finalizadas. Se estiver ele atualiza a ordem para
           // andamento
           if(DAO.getTecnicoDAO().findByCPF(cpfTecnico).addServiceOrder(ordem,
              DAO.getTecnicoDAO().findByCPF(cpfTecnico).getId())){
               listaOrdensServico.get(indiceClienteParaAtender).setStatus("andamento");
               indiceClienteParaAtender++;
               Connect.saveOrder(this.listaOrdensServico);
               Connect.saveIndice(this.indiceClienteParaAtender);
           }
       }
       else{
           throw new IllegalArgumentException("O técnico não pode aceitar novas ordens no momento");
       }
    }

    /**
     * Metodo para poder obter a lista de ordens de serviço presente no sistema
     * @return Uma lista com objetos do tipo <i>OrdemServiço</i>
     */
    @Override
    public List<OrdemServico> getList() {
        return this.listaOrdensServico;
    }

    /**
     * Método para retornar a lista de ordens que estão com o status "em andamento"
     * @return Uma lista com objetos do tipo <i>OrdemServico</i>
     */
    @Override
    public List<OrdemServico> getListOpening() {
        List<OrdemServico> openingOrders = new ArrayList<OrdemServico>();
        //VERIFICAR SE PRECISA SEPARAR POR DIA, TIPO DEIXAR APARECENDO SO AS ORDENS DAQUELE DIA
        for (OrdemServico ordem: this.listaOrdensServico) {
            if(ordem.getStatus().equals("espera")){
                openingOrders.add(ordem);
            }
        }
        return openingOrders;
    }
    // essa classe é apenas para não gerar erros no crud;

    /**
     * Método para poder criar e adicionar uma nova ordem de serviço ao sistema de dados.<br>
     * Nesse momento de criação alguns atributos são preenchidos automaticamente:<ul>
     *     <li>
     *         Data de inicio da ordem de serviço
     *     </li>
     *     <li>
     *         ID da ordem gerado aleatoriamente pela biblioteca UUID
     *     </li>
     * </ul>
     * @param ordem Objeto do tipo <i>OrdemServico</i> contendo os atributos necessários para o cadastro da ordem de serviço,
     *              excluindo os elementos que são gerados automaticamente
     * @return
     */
    @Override
    public OrdemServico create(OrdemServico ordem) throws Exception{
        LocalDate inicio = LocalDate.now();
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        //lembrar de verificar dps;
        ordem.setId(newIDStrign);
        ordem.setStart(inicio);
        this.listaOrdensServico.add(ordem);
        Connect.saveOrder(this.listaOrdensServico);
        return null;
    }

    /**
     * Método para poder atualizar a ordem de serviço já registrado no sistema, contendo novas informações sobre este.<br>
     * Caso a ordem de serviço passado não esteja no banco de dados é gerado uma exceção
     * @param ordem Objeto do tipo <i>OrdemServico</i> para ser trocada de lugar o objeto anterior com informações antigas, e adicionar o novo atualizado.
     */
    @Override
    public void update(OrdemServico ordem) throws Exception{
        boolean status  = false;
        for (int i=0; i< this.listaOrdensServico.size();i++){
            if(listaOrdensServico.get(i).getId().equals(ordem.getId())){
                this.listaOrdensServico.set(i, ordem);
                Connect.saveOrder(this.listaOrdensServico);
                return;
            }
        }
        if(!status){
            throw new IllegalArgumentException("Ordem não detectado no banco de dados");
        }

    }

    /**
     * Método para poder deletar a ordem de serviço da base de dados, partindo do ID da ordem de serviço
     * @param ID <i>String</i> contendo o ID do ordem de serviço para encontra-la, e poder realizar a operação.<br>
     *          Caso não seja encontrada a ordem de serviço, é gerado uma exceção
     */
    @Override
    public void delete(String ID) throws Exception{
        boolean deletado = false;
        for (int i=0; i< this.listaOrdensServico.size();i++){
            if(listaOrdensServico.get(i).getId().equals(ID)){
                this.listaOrdensServico.remove(i);
                Connect.saveOrder(this.listaOrdensServico);
                return;
            }
        }
        if(!deletado){
            throw new IllegalArgumentException("Cliente não detectado no banco de dados");
        }
    }

    /**
     * Método para procurar a ordem de serviço partindo da informação do seu ID
     * @param id <i>String</i> contendo o ID que foi associado a ordem de serviço
     * @return Objeto do tipo <i>OrdemServico</i>, que foi encontrado no sistema, a partir do ID passado
     */
    @Override
    public OrdemServico findById(String id) {
        for(OrdemServico ordem: this.listaOrdensServico){
            if(ordem.getId().equals(id)){
                return ordem;
            }
        }
        return null;
    }

    /**
     * Método realiza a impressão dos clientes cadastrados presentes na lista deles.<br>
     * Impressão segue o modelo:<br>
     * "ID da ordem: id associado a ordem de serviço"
     * "Tecnico da ordem: nome completo do tecnico responsavel pela aquela ordem"
     */
    @Override
    public void listObjects() throws Exception{
        if(this.listaOrdensServico.size()>0){
            Tecnico tecnico;
            for(OrdemServico ordem: this.listaOrdensServico){
                System.out.println("ID da ordem: "+ordem.getId());
                tecnico = DAO.getTecnicoDAO().findById(ordem.getTechnicianID());
                System.out.println("Tecnico da ordem: " + tecnico.getFullName());
            }
        }
    }

    /**
     * Método para deletar todos as ordens presentes no sistema, logo a lista de ordens se torna vazia
     */
    @Override
    public void deleteMany() throws Exception{
        this.listaOrdensServico = new ArrayList<>();
        this.indiceClienteParaAtender =0;
        Connect.saveOrder(this.listaOrdensServico);
        Connect.saveIndice(0);
    }

    /**
     * Método para verificar a quantidade de ordens cadastradas no sistema
     * @return <i>Int</i> contendo o tamanho da lista de ordens de serviço
     */
    @Override
    public int amountItems() {
        return listaOrdensServico.size();
    }

    /**
     * Método para encontrar a ordem em que o tecnico esta trabalhando no momento, ou seja a ordem registrada a ele e que tem
     * o status "em andamento"
     * @param cpf <i>String</i> contendo o CPF do tecnico o qual deseja realizar a busca
     * @return Objeto do tipo <i>OrdemServico</i> que esta com o status "em andamento", e associada ao tecnico do cpf passado
     */
    public OrdemServico openOrderByTechnician(String cpf) throws Exception{
        String idTecnico;
        idTecnico = DAO.getTecnicoDAO().findIdbyCPF(cpf);
        if(this.listaOrdensServico.size()>0){
            for(OrdemServico ordem: this.listaOrdensServico){

                if(idTecnico.equals(ordem.getTechnicianID())){
                    return ordem;
                }
            }
        }
        return null;
    }

    /**
     * Método responsável por informar sobre:
     * <ul>
     *     <li>
     *         Ordens que se encontram em aberto;
     *     </li>
     *     <li>
     *         Fila de ordens para serem realizadas por cada tecnico
     *     </li>
     * </ul>
     * @return <i>String</i> contendo as informações da agenda
     */
   public String agendaAtendimento() throws Exception{
       String agendaSaida = "";
       ArrayList<OrdemServico> esperando = new ArrayList<>();
       ArrayList<OrdemServico> andamento = new ArrayList<>();
       for (OrdemServico ordem : DAO.getOrdemServicoDAO().getList()) {
           if (ordem.getStatus().equals("andamento") && !(andamento.contains(ordem))) {
               agendaSaida += "Tecnico: " + DAO.getTecnicoDAO().findById(ordem.getTechnicianID()).getFullName() + "\n";
               agendaSaida += "Ordem em andamento: \n" +
                       "ID da ordem: " + ordem.getId() + "\n" +
                       "Nome do cliente: " + DAO.getClienteDAO().findById(ordem.getClientId()).getFullName() + "\n\n";
               andamento.add(ordem);
           } else if (ordem.getStatus().equals("espera")) {
               esperando.add(ordem);
           }
       }
       agendaSaida += "Ordem em espera: \n";
       for (OrdemServico ordemEspera : esperando) {
           agendaSaida += "ID da ordem: " + ordemEspera.getId() + "\n" +
                   "Nome do cliente: " + DAO.getClienteDAO().findById(ordemEspera.getClientId()).getFullName() + "\n";
       }
       agendaSaida += "\n";
       return agendaSaida;
    }

    /**
     * Método para verificar a quantidade de ordens em andamento de um determinado técnico.
     *
     * @param cpfTecnico O CPF do técnico para o qual deseja verificar as ordens em andamento.
     * @return A quantidade de ordens em andamento do técnico.
     */
    public int getQuantidadeOrdensEmAndamento(String cpfTecnico) throws Exception {
        int quantidade = 0;
        for (OrdemServico ordem : DAO.getOrdemServicoDAO().getList()) {
            String technicianId = DAO.getTecnicoDAO().findIdbyCPF(cpfTecnico);
            if (ordem.getTechnicianID() != null && technicianId != null && ordem.getTechnicianID().equals(technicianId) && ordem.getStatus().equals("andamento")){
                quantidade++;
            }
        }
        return quantidade;
    }
    /**
     * Retorna a quantidade de ordens de serviço concluídas para um técnico específico.
     *
     * @param cpfTecnico O CPF do técnico para o qual se deseja obter a quantidade de ordens concluídas.
     * @return A quantidade de ordens de serviço concluídas para o técnico escolhido.
     * @throws Exception Se ocorrer uma exceção durante a recuperação das informações das ordens de serviço.
     */
    public int getQuantidadeOrdensConcluidas(String cpfTecnico) throws Exception {
        int quantidade = 0;
        for (OrdemServico ordem : DAO.getOrdemServicoDAO().getList()) {
            String technicianId = DAO.getTecnicoDAO().findIdbyCPF(cpfTecnico);
            if (ordem.getTechnicianID() != null && technicianId != null && ordem.getTechnicianID().equals(technicianId) && ordem.getStatus().equals("finalizada")){
                quantidade++;
            }
        }
        return quantidade;
    }
    /**
     * Retorna uma lista de ordens de serviço associadas a um determinado tecnico.
     *
     * @param cpf O CPF do técnico para o qual se deseja obter as ordens de serviço.
     * @return Uma lista contendo objetos do tipo OrdemServico associados ao técnico especificado.
     *         Retorna null se não houver nenhuma ordem de serviço associada ao técnico.
     * @throws RuntimeException Se ocorrer uma exceção durante a recuperação das informações das ordens de serviço.
     */
    public List<OrdemServico> ordersByTechnician(String cpf){
        ArrayList<OrdemServico> orders = new ArrayList<>();

        for (OrdemServico ordem: this.listaOrdensServico) {
            try {
                if(ordem.getTechnicianID() != null){
                    if(ordem.getTechnicianID().equals(DAO.getTecnicoDAO().findIdbyCPF(cpf)) && (ordem.getStatus().equals("andamento") || ordem.getStatus().equals("espera"))){
                        orders.add(ordem);
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(orders.size()>0){
            return orders;
        } else {
            return null;
        }

    }

/*
    public int getIndiceClienteParaAtender() {
        return indiceClienteParaAtender;
    }

    public void setIndiceClienteParaAtender(int indiceClienteParaAtender) throws Exception {
        this.indiceClienteParaAtender = indiceClienteParaAtender;
        Connect.saveIndice(this.indiceClienteParaAtender);
    }
    */

}
