
package com.example.sistema_gerenciamentofx.dao.conexao;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta classe serve para poder realizar o intermédio entre o arquivo binário que esta salvo
 * na maquina, e as funcionalidades internas do sistema, ficando responsável por recuperar os dados contidos
 * nos arquivos, ou salvar dados nesses arquivos, mantendo, com essa última operação, os dados salvos após mesmo
 * após fechar o programa, ficando eles disponíveis para próxima abertura do programa.<br>
 */
public class Connect {
    /**
     * Este metodo estático, <i>static</i> serve para criação dos arquivos binários em caso de terem sido apagados,
     * ou na primeira vez que o sistema é utilizado naquela máquina.<br>
     * Este método deve ser utilizado ao iniciar do programa.<br>
     * Em caso dos arquivos não existirem e necessitar da criação, é inserida as estruturas vazias utilizadas por
     * cada classe do DAO.
     * @throws Exception se ocorrer um problema para salvar o valor em arquivo.
     */
    public static void generateCache() throws Exception{
        if(!(new File("cache")).exists()){
            File file = new File("cache");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        if(!(new File("cache\\clientes.nsr")).exists()){
            Connect.saveCliente(new ArrayList<Cliente>());
        }
        if(!(new File("cache\\tecnicos.nsr")).exists()){
            Connect.saveTecnico(new ArrayList<Tecnico>());
        }
        if(!(new File("cache\\ordensServico.nsr")).exists()){
            Connect.saveOrder(new ArrayList<OrdemServico>());
        }
        if(!(new File("cache\\estoque.nsr")).exists()){
            Connect.saveEstoque(new HashMap<>());
        }
        if(!(new File("cache\\indiceEspera.nsr")).exists()){
            Connect.saveIndice(0);
        }
    }

    /**
     * Este método estático serve para realizar o procedimento de gravação da estrutura passada,
     * nesse caso uma <i>ArrayList</i>, para o arquivo um arquivo binário, com final <i>.nsr</i><br>
     * Neste caso ele salva a lista de clientes cadastrados em um arquivo binário.
     * @param listaClientes <i>ArrayList</i>, contendo objetos do tipo <i>Cliente</i> que foram cadastrados no sistema
     * @throws Exception se ocorrer um problema para salvar o valor em arquivo.
     */
    public static void saveCliente(List<Cliente> listaClientes) throws Exception{
        try {
            File caminho = new File("cache\\clientes.nsr");
            FileOutputStream teste = new FileOutputStream(caminho);
            ObjectOutputStream gravador = new ObjectOutputStream(teste);
            gravador.writeObject(listaClientes);
            gravador.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método serve para realizar a abertura do arquivo binario, o qual contém uma estrutura
     * com objetos do tipo <i>Cliente</i>, ele ja contém validações para possíveis problemas que tenham
     * com o arquivo, seja dele ter sido deletado, ou não encontrado, ou corrompido.<br>
     * O método retorna a estrutura com os clientes que estão salvos no arquivo, caso o arquivo esteja
     * vazio, ele retorna a estrutura vazia.
     * @return <i>ArrayList</i> contendo objetos do tipo <i>CLiente</i>, que estão cadastrados no sistema
     * @throws Exception Se ocorrer um problema para abrir ou realizar a leitura do arquivo.
     */
    public static List<Cliente> openCliente() throws Exception{
        try {
            FileInputStream teste = new FileInputStream("cache\\clientes.nsr");
            ObjectInputStream recebedor = new ObjectInputStream(teste);
            List<Cliente> clientes = (List<Cliente>) recebedor.readObject();
            recebedor.close();
            if (clientes.isEmpty()) {
                return new ArrayList<>();
            }
            return clientes;
        } catch (FileNotFoundException excep) {
            throw new Exception("O arquivo não foi encontrado no sistema.", excep);
        } catch (ClassNotFoundException excep) {
            throw new Exception("Classe não encontrada.", excep);
        } catch (IOException excep) {
            throw new Exception("Problemas na leitura do arquivo.", excep);
        }
    }


    /**
     * Este método estático serve para realizar o procedimento de gravação da estrutura passada,
     * nesse caso uma <i>ArrayList</i>, para o arquivo um arquivo binário, com final <i>.nsr</i><br>
     * Neste caso ele salva a lista de Técnicos cadastrados em um arquivo binário.
     * @param listaTecnicos <i>ArrayList</i>, contendo objetos do tipo <i>Tecnico</i> que foram cadastrados no sistema
     * @throws Exception se ocorrer um problema para salvar o valor em arquivo.
     */
    public static void saveTecnico(List<Tecnico> listaTecnicos) throws Exception{
        try {
            FileOutputStream arquivo = new FileOutputStream("cache\\tecnicos.nsr");
            ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
            gravador.writeObject(listaTecnicos);
            gravador.close();
        }catch (IOException excep){
            excep.printStackTrace();
        }
    }

    /**
     * Este método serve para realizar a abertura do arquivo binario, o qual contém uma estrutura
     * com objetos do tipo <i>Tecnico</i>, ele ja contém validações para possíveis problemas que tenham
     * com o arquivo, seja dele ter sido deletado, ou não encontrado, ou corrompido.<br>
     * O método retorna a estrutura com os tecnicos que estão salvos no arquivo, caso o
     * arquivo esteja vazio, ele retorna a estrutura vazia.
     * @return <i>ArrayList</i> contendo objetos do tipo <i>Tecnico</i>, que estão cadastrados no sistema
     * @throws Exception Se ocorrer um problema para abrir ou realizar a leitura do arquivo.
     */
    public static List<Tecnico> openTecnicos() throws Exception{
        try {
            FileInputStream arquivo = new FileInputStream("cache\\tecnicos.nsr");
            ObjectInputStream recebedor = new ObjectInputStream(arquivo);
            List<Tecnico> tecnicos = (List<Tecnico>) recebedor.readObject();
            recebedor.close();
            if(tecnicos.isEmpty()){
                return new ArrayList<Tecnico>();
            }
            return tecnicos;
        } catch (FileNotFoundException excep) {
            throw new Exception("O arquivo não foi encontrado no sistema.", excep);
        } catch (ClassNotFoundException excep) {
            throw new Exception("Classe não encontrada.", excep);
        } catch (IOException excep) {
            throw new Exception("Problemas na leitura do arquivo.", excep);
        }
    }

    /**
     * Este método estático serve para realizar o procedimento de gravação da estrutura passada,
     * nesse caso uma <i>ArrayList</i>, para o arquivo um arquivo binário, com final <i>.nsr</i><br>
     * Neste caso ele salva a lista de ordens cadastradas em um arquivo.
     * @param listaOrdens <i>ArrayList</i>, contendo objetos do tipo <i>OrdemServico</i> que foram cadastrados no sistema
     * @throws Exception se ocorrer um problema para salvar o valor em arquivo.
     */
    public static void saveOrder(List<OrdemServico> listaOrdens) throws Exception{
        try {
            FileOutputStream arquivo = new FileOutputStream("cache\\ordensServico.nsr");
            ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
            gravador.writeObject(listaOrdens);
            gravador.close();
        }catch(IOException excep){
            excep.printStackTrace();
        }
    }

    /**
     * Este método serve para realizar a abertura do arquivo binario, o qual contém uma estrutura com objetos
     * do tipo <i>OrdemServico</i>, ele ja contém validações para possíveis problemas que tenham com o arquivo,
     * seja dele ter sido deletado, ou não encontrado, ou corrompido.<br>
     * O método retorna a estrutura com as Ordens de Serviço que estão salvas no arquivo, caso o arquivo
     * esteja vazio, ele retorna a estrutura vazia.
     * @return <i>ArrayList</i> contendo objetos do tipo <i>OrdemServico</i>, que estão cadastrados no sistema
     * @throws Exception Se ocorrer um problema para abrir ou realizar a leitura do arquivo.
     */
    public static List<OrdemServico> openOrdens() throws Exception{
        try {
            FileInputStream arquivo = new FileInputStream("cache\\ordensServico.nsr");
            ObjectInputStream recebedor = new ObjectInputStream(arquivo);
            List<OrdemServico> ordens = (List<OrdemServico>) recebedor.readObject();
            recebedor.close();
            if(ordens.isEmpty()){
                return new ArrayList<OrdemServico>();
            }
            return ordens;
        } catch (FileNotFoundException excep) {
            throw new Exception("O arquivo não foi encontrado no sistema.", excep);
        } catch (ClassNotFoundException excep) {
            throw new Exception("Classe não encontrada.", excep);
        } catch (IOException excep) {
            throw new Exception("Problemas na leitura do arquivo.", excep);
        }
    }


    /**
     * Este método estático serve para realizar o procedimento de gravação da
     * estrutura passada, nesse caso um <i>Map</i>, em que a chave é um objeto do tipo <i>Produto</i>, e a
     * o valor é do tipo <i>Integer</i> que informa a quantidade daquele produto, para o arquivo um arquivo binário,
     * com final <i>.nsr</i><br>
     * Neste caso ele salva o dicionário com o estoque presente no arquivo.
     * @param estoque <i>Map</i>, contendo objetos do tipo <i>Produto</i> como chave, e <i>Integer</i> como valor da chave
     * @throws Exception se ocorrer um problema para salvar o valor em arquivo.
     */
    public static void saveEstoque(Map<Produto, Integer> estoque) throws Exception{
        try {
            FileOutputStream arquivo = new FileOutputStream("cache\\estoque.nsr");
            ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
            gravador.writeObject(estoque);
            gravador.close();
        }catch (IOException excep){
            excep.printStackTrace();
        }
    }

    /**
     * Este método serve para realizar a abertura do arquivo binario, o qual contém uma estrutura com um
     * dicionário, que possui como chave um objeto do tipo <i>Produto</i> e como valor a quantidade
     * presente no estoque, ele ja contém validações para possíveis problemas que tenham com o arquivo,
     * seja dele ter sido deletado, ou não encontrado, ou corrompido.<br>
     * O método retorna a estrutura com o estoque salvo no arquivo, caso o arquivo
     * esteja vazio, ele retorna a estrutura vazia.
     * @return <i>Map</i> com as informações do estoque salvas no arquivo
     * @throws Exception Se ocorrer um problema para abrir ou realizar a leitura do arquivo.
     */
    public static Map<Produto, Integer> openEstoque() throws Exception{
        try {
            FileInputStream arquivo = new FileInputStream("cache\\estoque.nsr");
            ObjectInputStream recebedor = new ObjectInputStream(arquivo);
            Map<Produto, Integer> estoque = (Map<Produto,Integer>) recebedor.readObject();
            recebedor.close();
            if(estoque.isEmpty()){
                return new HashMap<>();
            }
            return estoque;
        } catch (FileNotFoundException excep) {
            throw new Exception("O arquivo não foi encontrado no sistema.", excep);
        } catch (ClassNotFoundException excep) {
            throw new Exception("Classe não encontrada.", excep);
        } catch (IOException excep) {
            throw new Exception("Problemas na leitura do arquivo.", excep);
        }
    }

    /**
     * Este método salva um valor inteiro que representa o índice do cliente que deve se atendido
     * na agenda de serviços. Ele foi criado para permitir a persistência deste índice em arquivo.
     *
     * @param indiceEspera O valor inteiro a ser salvo como índice da agenda de serviços.
     * @throws Exception Se ocorrer um problema ao salvar o valor em arquivo.
     */
    public static void saveIndice(int indiceEspera) throws Exception{
        try {
            FileOutputStream arquivo = new FileOutputStream("cache\\indiceEspera.nsr");
            ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
            gravador.writeObject(indiceEspera);
            gravador.close();
        }catch (IOException excep){
            excep.printStackTrace();
        }
    }

    /**
     * Este método é utilizado para realizar a abertura do arquivo presente no sistema
     * que armazena um indice do cliente que deve se atendido na agenda de serviços.
     *
     * @return <i>int</i> O valor inteiro do índice salvo no arquivo.
     * @throws Exception Se ocorrer um problema para abrir ou realizar a leitura do arquivo.
     */
    public static int openIndice() throws Exception{
        try {
            FileInputStream arquivo = new FileInputStream("cache\\indiceEspera.nsr");
            ObjectInputStream recebedor = new ObjectInputStream(arquivo);
            int indiceEspera = (int) recebedor.readObject();
            recebedor.close();

            return indiceEspera;
        } catch (FileNotFoundException excep) {
            throw new Exception("O arquivo não foi encontrado no sistema.", excep);
        } catch (ClassNotFoundException excep) {
            throw new Exception("Classe não encontrada.", excep);
        } catch (IOException excep) {
            throw new Exception("Problemas na leitura do arquivo.", excep);
        }
    }

}

