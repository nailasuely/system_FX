package com.example.sistema_gerenciamentofx.dao.cliente;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.Cliente;

import java.util.ArrayList;
/**
 * Interface criada para conter os métodos que não estão presentes na interface geral, o CRUD,
 * já que os métodos do CRUD são extendidos para essa interface.<br>
 * Essa interface contém os métodos implementados relativos ao Cliente, sendo eles:
 * <ul>
 *     <li>
 *         <i><b>findByCPF</b></i> - realiza a busca do objeto <i>Cliente</i> partindo de um CPF
 *     </li>
 *     <li>
 *         <i><b>findByCPFIsTrue</b></i> - realiza a conferencia se existe um cliente com aquele CPF no sistema
 *     </li>
 *    <li>
 *        <i><b>findIdbyCPF</b></i> - realiza a busca do ID do objeto <i>Cliente</i> partindo de um CPF
 *    </li>
 * </ul>
 */
public interface ClienteDAO extends CRUD<Cliente> {
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação<br>
     * Tal método serve para procurar um cliente a partir do cpf
     * @param cpf <i>String</i> contendo o CPF do cliente o qual deseja encontrar
     * @return Objeto do tipo <i>Cliente</i> o qual foi encontrado, em caso de não encontrar é retornado nulo
     */
    public Cliente findByCPF(String cpf);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para verificar a existencia de um cliente com aquele CPF
     * @param cpf <i>String</i> contendo o CPF do cliente o qual deseja verificar a existência
     * @return <i>Boolean</i>, contendo <i>True</i> se foi encontrado, ou <i>False</i> se não foi encontrado
     */
    public boolean findByCpfIsTrue(String cpf);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para encontrar o ID do cliente partindo do seu CPF
     * @param CPF <i>String</i> contendo o CPF do cliente o qual deseja encontrar o ID
     * @return <i>String</i> contendo o ID do cliente, em caso de não encontrar é gerada uma exceção
     */
    public String findIdbyCPF(String CPF);


}
