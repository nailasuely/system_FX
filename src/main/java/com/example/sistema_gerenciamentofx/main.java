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

        ArrayList<Cliente> listaDeClientes = new ArrayList<Cliente>();
        ArrayList<Tecnico> listaDeTecnicos = new ArrayList<Tecnico>();

        System.out.println("------- SISTEMA DE GERENCIAMENTO ------- ");
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("1. CADASTRAR TÉCNICO");
            System.out.println("2. LISTAR ID DOS TÉCNICOS");
            System.out.println("3. FAZER LOGIN DO TÉCNICO");
            System.out.println("3. GERAR RELATÓRIO");
            System.out.println("0. Parar");
            System.out.print("Digite uma opçao: ");
            escolha = input.nextInt();
            switch (escolha) {
                case 1:
                    String fullName; String CPF; // só vou pedir o nome por enquanto para testar mais rápido
                    Tecnico novoTecnico = new Tecnico();
                    novoTecnico = (Tecnico) DAO.getTecnicoDAO().create(novoTecnico);
                    input.nextLine();
                    System.out.print("Digite o nome do tecnico: ");
                    fullName = input.nextLine();
                    novoTecnico.setFullName(fullName);
                    listaDeTecnicos.add(novoTecnico);
                    break;
                case 2:
                    DAO.getTecnicoDAO().listObjects(listaDeTecnicos);
                    break;
                case 3:
                    input.nextLine();
                    System.out.print("Digite o ID do técnico: ");
                    id = input.nextLine();
                    if (DAO.getTecnicoDAO().findById(id) != null){
                        System.out.println("1. REGISTRAR CLIENTE");
                        System.out.println("2. ATUALIZAR CLIENTES");
                        System.out.println("3. DELETAR CLIENTE");
                        System.out.println("4. LISTAR CLIENTES");
                        System.out.println("4. ATUALIZAR ORDENS DE SERVIÇO");
                        System.out.println("4. GERENCIAR ESTOQUE");
                        System.out.println("0. Parar");
                        System.out.print("Digite uma opçao: ");
                        escolha = input.nextInt();
                        switch (escolha){
                            case 1:
                                String name; String cpf; // só vou pedir o nome por enquanto
                                Cliente novoCliente = new Cliente();
                                novoCliente = (Cliente) DAO.getClienteDAO().create(novoCliente);
                                input.nextLine();
                                System.out.print("Digite o nome do cliente: ");
                                name = input.nextLine();
                                novoCliente.setFullName(name);
                                listaDeClientes.add(novoCliente);

                            case 0:
                                System.out.println("Finalizando...");
                                break;
                            default:
                                System.out.println("Digite uma opção válida, por favor!");
                                break;
                        }while(escolha != 0);

                    };

                    break;
                case 4:
                    input.nextLine();
                    System.out.print("Digite o ID do cliente: ");
                    id = input.nextLine();
                    Cliente novoC = new Cliente();
                    novoC = DAO.getClienteDAO().findById(id);
                    System.out.println("ID do cliente encontrado: "+ novoC.getId());

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