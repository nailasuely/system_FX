
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

    public static void generateCache() throws Exception{
        Connect.saveCliente(new ArrayList<Cliente>());
        Connect.saveOrder(new ArrayList<OrdemServico>());
        Connect.saveTecnico(new ArrayList<Tecnico>());
        Connect.saveEstoque(new HashMap<>());
    }

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


}

