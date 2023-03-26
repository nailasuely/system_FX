package com.example.sistema_gerenciamentofx;


import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Essa main foi criada de modo a realizar testes do programa
 */

public class main{
    public static void main(String[] args) {
        int escolha = 1; String id;
        int opcao=0;
        ArrayList<Cliente> listaDeClientes = new ArrayList<Cliente>();
        ArrayList<Tecnico> listaDeTecnicos = new ArrayList<Tecnico>();

        System.out.println("------- SISTEMA DE GERENCIAMENTO ------- ");

        do{
            Scanner input = new Scanner(System.in);
            System.out.println("1. CADASTRAR TÉCNICO");
            System.out.println("2. LISTAR ID DOS TÉCNICOS");
            System.out.println("3. FAZER LOGIN DO TÉCNICO");
            System.out.println("4. GERAR RELATÓRIO");
            System.out.println("4. PROCURAR CLIENTE PELO ID");
            System.out.println("5. PROCURAR TECNICO");
            System.out.println("6. LISTAR ID DOS CLIENTES");
            System.out.println("0. Parar");
            System.out.print("Digite uma opçao: ");
            escolha = input.nextInt();
            switch (escolha) {
                case 1:
                    String fullName; String CpfTecnico; // só vou pedir o nome por enquanto para testar mais rápido
                    Tecnico novoTecnico = new Tecnico();

                    input.nextLine();
                    System.out.print("Digite o nome do tecnico: ");
                    fullName = input.nextLine();
                    //LEVE BUG AQUI, PARA PODER PROSSEGUIR TEM Q DAR 2 ENTER PARA PODER IR PEDIR O CPF
                    input.nextLine();
                    System.out.print("Digite o CPF do tecnico: ");
                    CpfTecnico = input.nextLine();
                    novoTecnico.setFullName(fullName);
                    novoTecnico.setCpf(CpfTecnico);
                    System.out.println(novoTecnico.getCpf());
                    //listaDeTecnicos.add(novoTecnico);

                    novoTecnico = (Tecnico) DAO.getTecnicoDAO().create(novoTecnico);
                    break;

                case 2:
                    DAO.getTecnicoDAO().listObjects(listaDeTecnicos);
                    break;
                case 3:
                    input.nextLine();
                    System.out.print("Digite o ID do técnico: ");
                    id = input.nextLine();
                    if (DAO.getTecnicoDAO().findById(id) != null){

                        do {
                            System.out.println("1. REGISTRAR CLIENTE");
                            System.out.println("2. ATUALIZAR CLIENTES");
                            System.out.println("3. DELETAR CLIENTE");
                            System.out.println("4. LISTAR CLIENTES");
                            System.out.println("4. ATUALIZAR ORDENS DE SERVIÇO");
                            System.out.println("4. GERENCIAR ESTOQUE");
                            System.out.println("0. Parar");
                            System.out.print("Digite uma opçao: ");
                            opcao = input.nextInt();
                            switch (opcao) {
                                case 1:
                                    String name;
                                    String cpf; // só vou pedir o nome por enquanto
                                    Cliente novoCliente = new Cliente();

                                    input.nextLine();
                                    System.out.print("Digite o nome do cliente: ");
                                    name = input.nextLine();
                                    input.nextLine();
                                    System.out.println("Digite o CPF do cliente: ");
                                    cpf = input.nextLine();

                                    novoCliente.setFullName(name);

                                    novoCliente.setCpf(cpf);
                                    //listaDeClientes.add(novoCliente);

                                    novoCliente = (Cliente) DAO.getClienteDAO().create(novoCliente);

                                    break;
                                case 2:
                                    String CpfClienteAtualizar;
                                    String idClienteAtualizar = "";

                                    Cliente clienteAtt = new Cliente();
                                    input.nextLine();
                                    System.out.println("Primeiro digite o CPF do cliente:");
                                    CpfClienteAtualizar = input.nextLine();
                                    idClienteAtualizar = DAO.getClienteDAO().findIdbyCPF(CpfClienteAtualizar);
                                    if (idClienteAtualizar != "") {
                                        input.nextLine();
                                        System.out.println("Cliente encontrado");
                                        System.out.println("Dado a se atualizar:");
                                        System.out.println("1. NOME DO CLIENTE");
                                        System.out.println("2. TELEFONE");
                                        System.out.println("3. ENDEREÇO");
                                        System.out.println("0. SAIR");
                                        escolha = input.nextInt();
                                        switch (escolha) {
                                            case 1:
                                                String newName;
                                                input.nextLine();
                                                System.out.println("Nome atual: " + DAO.getClienteDAO().findById(idClienteAtualizar).getFullName());
                                                System.out.println("Digite um novo nome: ");
                                                newName = input.nextLine();
                                                //pode haver outra logica melhor para essa operação
                                                clienteAtt.setTelephone(DAO.getClienteDAO().findById(idClienteAtualizar).getTelephone());
                                                clienteAtt.setCpf(DAO.getClienteDAO().findById(idClienteAtualizar).getCpf());
                                                clienteAtt.setAddress(DAO.getClienteDAO().findById(idClienteAtualizar).getAddress());
                                                clienteAtt.setId(idClienteAtualizar);
                                                clienteAtt.setFullName(newName);
                                                DAO.getClienteDAO().update(clienteAtt);
                                                break;
                                            case 2:
                                                int newTelefone;
                                                input.nextInt();
                                                System.out.println("Telefone atual: " + DAO.getClienteDAO().findById(idClienteAtualizar).getTelephone());
                                                System.out.println("Digite o novo telefone: ");
                                                newTelefone = input.nextInt();
                                                clienteAtt.setTelephone(newTelefone);
                                                clienteAtt.setCpf(DAO.getClienteDAO().findById(idClienteAtualizar).getCpf());
                                                clienteAtt.setAddress(DAO.getClienteDAO().findById(idClienteAtualizar).getAddress());
                                                clienteAtt.setId(idClienteAtualizar);
                                                clienteAtt.setFullName(DAO.getClienteDAO().findById(idClienteAtualizar).getFullName());
                                                DAO.getClienteDAO().update(clienteAtt);
                                                break;
                                            case 3:
                                                String newAdress;
                                                input.nextLine();
                                                System.out.println("Endereço atual:"+ DAO.getClienteDAO().findById(idClienteAtualizar).getAddress());
                                                System.out.println("Digite o novo endereço: ");
                                                newAdress = input.nextLine();
                                                clienteAtt.setTelephone(DAO.getClienteDAO().findById(idClienteAtualizar).getTelephone());
                                                clienteAtt.setCpf(DAO.getClienteDAO().findById(idClienteAtualizar).getCpf());
                                                clienteAtt.setAddress(newAdress);
                                                clienteAtt.setId(idClienteAtualizar);
                                                clienteAtt.setFullName(DAO.getClienteDAO().findById(idClienteAtualizar).getFullName());
                                                DAO.getClienteDAO().update(clienteAtt);
                                                break;
                                            case 0:
                                                System.out.println("Saindo do menu de atualização cadastral");
                                                break;

                                        }
                                        //while (escolha != 0) ;
                                    }

                                    break;
                                case 3:
                                    int escolhaDesejada = 0;

                                    System.out.println("Deseja deletar o cliente por:\n" +
                                            "1. ID do cliente\n" +
                                            "2. CPF do cliente\n" +
                                            "3. Sair deste menu");
                                    escolhaDesejada = input.nextInt();
                                    switch (escolhaDesejada) {
                                        case 1:
                                            String idClienteDelete;
                                            input.nextLine();
                                            System.out.println("Digite o ID do cliente: ");
                                            idClienteDelete = input.nextLine();
                                            DAO.getClienteDAO().delete(idClienteDelete);
                                            break;
                                        case 2:
                                            String cpfClienteDelete;

                                            input.nextLine();
                                            System.out.println("Digite o CPF do cliente: ");
                                            cpfClienteDelete = input.nextLine();
                                            idClienteDelete = DAO.getClienteDAO().findIdbyCPF(cpfClienteDelete);
                                            DAO.getClienteDAO().delete(idClienteDelete);
                                            break;
                                        default:
                                            System.out.println("Opcao invalida");
                                            break;
                                    }
                                    break;
                                case 0:
                                    System.out.println("Finalizando...");
                                    break;
                                default:
                                    System.out.println("Digite uma opção válida, por favor!");
                                    break;
                            }
                        }while(opcao!=0);

                    }

                    break;
                case 4:
                    input.nextLine();
                    System.out.print("Digite o ID do cliente: ");
                    id = input.nextLine();
                    Cliente novoC = new Cliente();
                    novoC = DAO.getClienteDAO().findById(id);
                    System.out.println("ID do cliente encontrado: "+ novoC.getId());

                    break;
                case 5:
                    Tecnico novoT;
                    String cpf;
                    input.nextLine();
                    System.out.print("Digite o CPF do tecnico: ");
                    cpf = input.nextLine();
                    novoT = DAO.getTecnicoDAO().findByCPF(cpf);
                    System.out.println("Nome do tecnico: "+novoT.getFullName());
                    break;
                case 6:
                    DAO.getClienteDAO().listObjects(listaDeClientes);
                    break;
                case 0:
                    System.out.println("Finalizando...");
                default:
                    System.out.println("Digite uma opção válida, por favor!");
                    break;
            }

        }while(escolha != 0);

    }

}