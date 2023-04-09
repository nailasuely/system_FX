package com.example.sistema_gerenciamentofx.dao.tecnico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;
/**
 * Interface criada para conter os métodos que não estão presentes na interface geral, o CRUD, já que os métodos do CRUD
 * são extendidos para essa interface.<br>
 * Essa interface contém os métodos implementados relativos ao Tecnico, sendo eles:
 * <ul>
 *     <li>
 *         <i><b>findByCPF</b></i> - realiza a busca do objeto <i>Tecnico</i> partindo de um CPF
 *     </li>
 *     <li>
 *         <i><b>findByCPFIsTrue</b></i> - realiza a conferencia se existe um Tecnico com aquele CPF no sistema
 *     </li>
 *    <li>
 *        <i><b>findIdbyCPF</b></i> - realiza a busca do ID do objeto <i>Tecnico</i> partindo de um CPF
 *    </li>
 * </ul>
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
    //analisar possibilidade de delete desse método
    public String balanceamento();
}
