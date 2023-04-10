package com.example.sistema_gerenciamentofx.dao.tecnico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.util.ArrayList;
import java.util.List;
/**
 * Interface criada para conter os métodos que não estão presentes na interface geral, o CRUD, já que os métodos do CRUD
 * são extendidos para essa interface.<br>
 * Essa interface contém os métodos implementados relativos ao Tecnico, sendo eles:
 * <ul>
 *     <li>
 *         <b>findByCPF</b> - realiza a busca do objeto <i>Tecnico</i> partindo de um CPF
 *     </li>
 *     <li>
 *         <b>findByCPFIsTrue</b> - realiza a conferencia se existe um Tecnico com aquele CPF no sistema
 *     </li>
 *    <li>
 *        <b>findIdbyCPF</b> - realiza a busca do ID do objeto <i>Tecnico</i> partindo de um CPF
 *    </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public interface TecnicoDAO extends CRUD<Tecnico> {
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para procurar um tecnico a partir do cpf
     * @param cpf <i>String</i> contendo o CPF do tecnico o qual deseja encontrar
     * @return Objeto do tipo <i>Tecnico</i> o qual foi encontrado, em caso de não encontrar é retornado nulo
     */
    public Tecnico findByCPF(String cpf);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para verificar a existencia de um tecnico com aquele CPF
     * @param cpf <i>String</i> contendo o CPF do tecnico o qual deseja verificar a existência
     * @return <i>Boolean</i>, contendo <i>True</i> se foi encontrado, ou <i>False</i> se não foi encontrado
     */
    public boolean findByCPFIsTrue(String cpf);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para encontrar o ID do tecnico partindo do seu CPF
     * @param CPF <i>String</i> contendo o CPF do tecnico o qual deseja encontrar o ID
     * @return <i>String</i> contendo o ID do tecnico, em caso de não encontrar é gerada uma exceção
     */
    public String findIdbyCPF(String CPF);
    public String balanceamento();

    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para obter a lista de Tecnicos
     * @return Uma <i>Lista</i> de objetos do tipo <i>Tecnico</i>
     */
    public List<Tecnico> getList();

    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para obter a lista de ordens de serviço associadas ao tecnico
     * @return Uma lista, do tipo <i>ArrayList</i> com objetos do tipo <i>OrdemServico</i>
     */
    public ArrayList<OrdemServico> getServiceOrders();
}
