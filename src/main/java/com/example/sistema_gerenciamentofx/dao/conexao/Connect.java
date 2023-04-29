
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
    public static void generate() throws Exception{
        Cliente cliente1 = new Cliente("Maria Sobrenome", "Rua ABC, Bahia",
                "123.789.101-10", 75);


        Cliente cliente2 = new Cliente("Pedro Santos", "Avenida XYZ, Rio de Janeiro",
                "987.654.321-00", 25);
        Cliente cliente3 = new Cliente("Joaquim Sobrenome", "Rua XAFJZ, Bahia",
                "456.129.101-10", 42);
        cliente1 = DAO.getClienteDAO().create(cliente1);
        cliente2 = DAO.getClienteDAO().create(cliente2);
        cliente3 = DAO.getClienteDAO().create(cliente3);

        Tecnico tecnico1 = new Tecnico("Rhian Sobrenome", "Coité, Bahia",
                "234.567.890-11", 75);


        Tecnico tecnico2 = new Tecnico("João Sobrenome", "Rua XYZ, Bahia",
                "456.000.101-10", 81);
        tecnico1 = DAO.getTecnicoDAO().create(tecnico1);
        tecnico2 = DAO.getTecnicoDAO().create(tecnico2);

        DAO.getEstoqueDAO().AdicionarEstoqueInicial();

        OrdemServico ordem1 = new OrdemServico();
        OrdemServico ordem2 = new OrdemServico();

        DAO.getOrdemServicoDAO().create(ordem1, cliente1.getId(), Produto.servicoFormatar());
        DAO.getOrdemServicoDAO().create(ordem2, cliente2.getId(), Produto.servicoInstalar());

    }

    public static void printa() throws Exception{
        System.out.println("\nCLIENTES: ");
        DAO.getClienteDAO().listObjects();
        System.out.println("\nTECNICOS: ");
        DAO.getTecnicoDAO().listObjects();
        System.out.println("\nORDENS: ");
        DAO.getOrdemServicoDAO().listObjects();
        System.out.println("\nESTOQUE: ");
        Map<Produto, Integer> hash = DAO.getEstoqueDAO().getList();
        for (Produto produto1 : hash.keySet()){
            System.out.println(produto1.getNome());
        }
    }

    /**
     * Este metodo estático, <i>static</i> serve para criação dos arquivos binários em caso de terem sido apagados,
     * ou na primeira vez que o sistema é utilizado naquela máquina.<br>
     * Este método deve ser utilizado ao iniciar do programa.<br>
     * Em caso dos arquivos não existirem e necessitar da criação, é inserida as estruturas vazias utilizadas por
     * cada classe do DAO.
     * @throws Exception
     */
    public static void generateCache() throws Exception{
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
     * @throws Exception
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
     * @throws Exception
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
     * @throws Exception
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
     * @throws Exception
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
     * @throws Exception
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
     * @throws Exception
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
     * estrutura passada, nesse caso um <i>Map<Produto, Integer</i>, em que a chave é um objeto do tipo <i>Produto</i>, e a
     * o valor é do tipo <i>Integer</i> que informa a quantidade daquele produto, para o arquivo um arquivo binário,
     * com final <i>.nsr</i><br>
     * Neste caso ele salva o dicionário com o estoque presentes no arquivo.
     * @param estoque <i>Map<></i>, contendo objetos do tipo <i>Produto</i> como chave, e <i>Integer</i> como valor da chave
     * @throws Exception
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
     * @return <i>Map<"Produto, Integer"></i> com as informações do estoque que estão salvas no arquivo
     * @throws Exception
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

