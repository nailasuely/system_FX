package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import java.util.List;
/**
 * Interface criada para conter os métodos que não estão presentes na interface geral, o CRUD, já que os métodos do CRUD
 * são extendidos para essa interface.<br>
 * Essa interface contém os métodos implementados relativos a OrdemServico, sendo eles:
 * <ul>
 *     <li>
 *         <b>openOrderByTechnician</b> - encontra a ordem em aberto de determinado tecnico, a partir do seu cpf
 *     </li>
 *     <li>
 *         <b>atualizarStatusAndamento</b> - atualiza o status da ordem de "em espera", para "em andamento", a partir
 *do cpf do tecnico, adjunto ao objeto do tipo <i>OrdemServico</i> que este irá trabalhar, logo que fez a retirada da fila de espera.
 *     </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public interface OrdemServicoDAO extends CRUD<OrdemServico> {
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para encontrar a ordem em aberto que determinado tecnico está trabalhando.
     * @param cpf <i>String</i> contendo o CPF do tecnico o qual deseja procurar a ordem em aberto associada a ele
     * @return Objeto do tipo <i>OrdemServico</i> que foi encontrada no sistema, em caso de não encontrar é retornado nulo.
     */
    public OrdemServico openOrderByTechnician(String cpf) throws Exception;
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para adicionar uma Ordem de Serviço ao sistema de dados, preenchendo com seu ID automaticamente
     * @param clienteID <i>String</i> contendo o ID do cliente que requisitou a ordem
     * @param ordem Objeto do tipo <i>OrdemServico</i> para ser atualizada
     * @param type Objeto do tipo <i>Produto</i> para indicar qual o tipo do serviço que está contido na ordem de serviço
     */
    public OrdemServico create(OrdemServico ordem, String clienteID, Produto type) throws Exception;
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para atualizar o status da ordem, retirando-a da fila de espera e colocando com o status "em andamento"
     * significando que vai ser iniciado o trabalho naquela ordem.
     * @param cpfTecnico <i>String</i> contendo o CPF do tecnico o qual deseja procurar a ordem na fila de espera
     * @param ordem Objeto do tipo <i>OrdemServico</i> para ser atualizada
     */
    public void atualizarStatusAndamento(String cpfTecnico, OrdemServico ordem) throws Exception;

    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para obter a lista de Ordens de Serviço
     * @return Uma <i>Lista</i> de objetos do tipo <i>OrdemServico</i>
     */
    public List<OrdemServico> getList();

    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para obter uma apresentação da agenda de atendimento, com as ordens que estão em aberto
     * e as ordens que estão em espera, separadas por cada um dos tecnicos cadastrados
     * @return <i>String</i> contendo as informações, para apresentação na tela
     */
    public String agendaAtendimento() throws Exception;

    /**
     * Retorna uma lista de ordens de serviço com o status "em andamento".
     *
     * @return Uma lista contendo objetos do tipo OrdemServico com o status "espera".
     */
    public List<OrdemServico> getListOpening();
    /**
     * Assinatura de método que serve para o acesso ao metodo que retorna a quantidade
     * de ordens de serviço concluídas para um técnico específico.
     * @param cpfTecnico O CPF do técnico para o qual se deseja obter a quantidade de ordens concluídas.
     * @return A quantidade de ordens de serviço concluídas para o técnico escolhido.
     * @throws Exception Se ocorrer uma exceção durante a recuperação das informações das ordens de serviço.
     */
    public int getQuantidadeOrdensConcluidas(String cpfTecnico) throws Exception;
    /**
     * Assinatura de método que serve para o acesso ao metodo que retorna a quantidade
     * de ordens de serviço em andamento para um técnico específico.
     * @param cpfTecnico O CPF do técnico para o qual se deseja obter a quantidade de ordens concluídas.
     * @return A quantidade de ordens de serviço em andamento para o técnico escolhido.
     * @throws Exception Se ocorrer uma exceção durante a recuperação das informações das ordens de serviço.
     */
    public int getQuantidadeOrdensEmAndamento(String cpfTecnico) throws Exception;
    /**
     * Assinatura de método que serve para retornar uma lista de serviços de determinao tecnico..
     *
     * @param cpf O CPF do técnico para o qual se deseja obter as ordens de serviço.
     * @return Uma lista contendo objetos do tipo OrdemServico associados ao técnico especificado.
     *         Retorna null se não houver nenhuma ordem de serviço associada ao técnico.
     * @throws RuntimeException Se ocorrer uma exceção durante a recuperação das informações das ordens de serviço.
     */
    public List<OrdemServico> ordersByTechnician(String cpf);
}
