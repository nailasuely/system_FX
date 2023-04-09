package com.example.sistema_gerenciamentofx.dao.tecnico;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Está classe é parte da forma de armazenamento de dados, sendo utilizado para tal lista nessa classe, em que utiliza o padrão DAO.<br>
 * Está salva uma lista de tecnicos, e contém métodos, padrões ao CRUD (Create, Read, Update, Delete), além de outros
 * métodos necessários para desenvolvimento das funções do sistema dentre eles: <ul>
 *     <li>
 *          findById - procurar o tecnico a partir do seu ID
 *      </li>
 *      <li>
 *          findByCPF - procurar o tecnico a partir do seu CPF
 *      </li>
 *      <li>
 *          findByCpfIsTrue - verificar se aquele CPF existe no sistema
 *      </li>
 *      <li>
 *        findIdbyCPF -  procurar o ID do tecnico, partindo do seu CPF
 *      </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class ListTecnicos implements TecnicoDAO {
    /**
     * O atributo <i>listaTecnicos</i> é do tipo <i>List</i>, e armazena uma lista de objetos do tipo <i>Tecnicos</i>
     */
    private List<Tecnico> listaTecnicos;

    /**
     * Método construtor da classe, em que inicializa a lista que irá conter os tecnicos do sistema
     */
    public ListTecnicos() {
        this.listaTecnicos = new ArrayList<Tecnico>();
    }

    /**
     ** Método para poder criar e adicionar o cadastro de um novo tecnico ao sistema de dados.<br>
     * Antes de salvar o objeto contendo as informações do tecnico, existem verificações para evitar problemas no sistema,
     * dentre as verificações:<ul>
     *     <li>
     *         Verificação para não criar um tecnico ja existente - utiliza o CPF para isso
     *     </li>
     *     <li>
     *         Verificação para o tecnico não estar, já cadastrado como um cliente
     *     </li>
     * </ul>
     * Ainda é gerado um ID aleatorio, pela biblioteca UUID, para preenchimento do ID do tecnico, e após feito o set do atributo <b>ID</b> é então salvo na lista
     * @param tecnico Objeto do tipo <i>Tecnico</i> contendo os atributos necessários para o cadastro, excluindo o ID, que é gerado internamente
     * @return Objeto do tipo <i>Tecnico</i> que contém agora o ID preenchido.<br>
     */
    @Override
    public Tecnico create(Tecnico tecnico) {
        if (findByCPFIsTrue(tecnico.getCpf()) | DAO.getClienteDAO().findByCpfIsTrue(tecnico.getCpf())) {
            return null;
        }
        else{
            // Gerar id pseudoaleatório;
            UUID newID = UUID.randomUUID();
            String newIDStrign= newID.toString();

            //lembrar de verificar dps;
            tecnico.setId(newIDStrign);
            this.listaTecnicos.add(tecnico);
            return tecnico;
        }
    }

    /**
     * Método para poder atualizar o tecnico já registrado no sistema, contendo novas informações sobre este.<br>
     * Caso o tecnico passado não esteja no banco de dados é gerado uma exceção
     * @param tecnico Objeto do tipo <i>Tecnico</i> para ser trocar de lugar o objeto anterior com informações
     *                antigas, e adicionar o novo atualizado.
     */
    @Override
    public void update(Tecnico tecnico) {
        boolean encontrado = false;
        for (int i = 0; i < this.listaTecnicos.size(); i++) {
            if (this.listaTecnicos.get(i).getId() == tecnico.getId()) {
                this.listaTecnicos.set(i, tecnico);
                encontrado = true;
                return;
            }
        }
        if (!encontrado) {
            throw new IllegalArgumentException("Tecnico não detectado no banco de dados");
        }
    }

    /**
     * Método para poder deletar o tecnico da base de dados, partindo do CPF do tecnico
     * @param cpf <i>String</i> contendo o número do CPF do tecnico para encontra-lo, e poder realizar a operação.<br>
     *            Caso não seja encontrado o cliente, é gerado uma exceção
     */
    @Override
    public void delete(String cpf) {
        boolean encontrado = false;
        for (int i = 0; i < this.listaTecnicos.size(); i++) {
            if (this.listaTecnicos.get(i).getCpf().equals(cpf)) {
                this.listaTecnicos.remove(i);
                encontrado = true;
                return;
            }
        }
        if(encontrado == false){
            throw new IllegalArgumentException("Tecnico não detectado no banco de dados");
        }
    }

    /**
     * Método para procurar o tecnico partindo da informação do seu ID
     * @param id <i>String</i> contendo o ID que foi associado ao tecnico
     * @return Objeto do tipo <i>Tecnico</i>, que foi encontrado no sistema, a partir do ID passado
     */
    @Override
    public Tecnico findById(String id) {
        for (Tecnico tecnico : this.listaTecnicos) {
            if (tecnico.getId().equals(id)) {
                return tecnico;
            }
        }
        return null;
    }

    /**
     * Método realiza a impressão dos tecnicos cadastrados presentes na lista deles.<br>
     * Impressão segue o modelo: "ID do tecnico: id referente ao tecnico"
     */
    @Override
    public void listObjects() {
        for (Tecnico tecnico : this.listaTecnicos) {
            System.out.println("ID do técnico: " + tecnico.getId() );
        }
    }

    /**
     * Método para deletar todos os tecnicos presentes no sistema, logo a lista de tecnicos se torna vazia
     */
    @Override
    public void deleteMany() {
        this.listaTecnicos = new ArrayList<>();
    }

    /**
     * Método para verificar a quantidade de tecnicos que estão cadastrados no sistema
     * @return <i>Int</i> contendo o tamanho da lista de tecnicos
     */
    @Override
    public int amountItems() {
        return listaTecnicos.size();
    }

    /**
     * Método para encontrar o tecnico no sistema a partir do seu CPF
     * @param cpf <i>String</i> contendo o número do cpf do tecnico que deseja encontrar
     * @return Objeto do tipo <i>Tecnico</i> encontrado no sistema.<br>
     * Em caso de não encontrar o retorno é nulo
     */
    @Override
    public Tecnico findByCPF(String cpf) {
        for (Tecnico tecnico : this.listaTecnicos) {
            if (tecnico.getCpf().equals(cpf)) {
                return tecnico;
            }
        }
        return null;
    }

    /**
     * Método para verificar a existencia daquele CPF de um tecnico no sistema
     * @param cpf <i>String</i> contendo o número do cpf do tecnico que deseja se verificar
     * @return Booleano, em que se o CPF existe no sistema é retornado <b><i>True</i></b>, e no caso contrário, <b><i>False</i></b>
     */
    public boolean findByCPFIsTrue(String cpf) {
        for (Tecnico tecnico : this.listaTecnicos) {
            if (tecnico.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para encontrar o ID do tecnico, a partir da informação do seu CPF
     * @param CPF <i>String</i> contendo o número do cpf do tecnico que deseja encontrar o ID
     * @return <i>String</i> contendo o ID do tecnico, no caso do tecnico existir no sistema, caso o mesmo não exista
     * é gerado uma exceção
     */
    public String findIdbyCPF(String CPF){
        for(Tecnico tecnico: this.listaTecnicos){
            if(tecnico.getCpf().equals(CPF)){
                return tecnico.getId();
            }
        }
        throw new IllegalArgumentException("Tecnico não detectado no banco de dados");

    }

    //trocar nome
    public String balanceamento(){
        //metodo para comparar e emitir aviso quando um tecnico quiser criar uma ordem
        //porem este ja estiver sobrecarregado e ser a vez de outro
        //fazer verificação no tamanho das listas de ordens de cada um dos tecnicos
        //colocar valor max de diferença entre uma lista e outra
        return null;
    }

}
