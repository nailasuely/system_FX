package com.example.sistema_gerenciamentofx;


import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;

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
        System.out.println("------- SISTEMA DE GERENCIAMENTO ------- ");
        do{
            Scanner input = new Scanner(System.in);
            // testando os métodos da classe cliente;
            System.out.println("1. REGISTRAR CLIENTE");
            System.out.println("2. LISTAR ID DOS CLIENTES");
            System.out.println("3. DELETAR CLIENTE");
            System.out.println("4. PROCURAR CLIENTE");
            System.out.println("0. Parar");
            System.out.print("Digite uma opçao: ");
            escolha = input.nextInt();
            switch (escolha) {
                case 1:
                    Cliente novoCliente = new Cliente();
                    novoCliente = (Cliente) DAO.getClienteDAO().create(novoCliente);
                    listaDeClientes.add(novoCliente);
                    break;
                case 2:
                    DAO.getClienteDAO().listObjects(listaDeClientes);
                    break;
                case 3:
                    input.nextLine();
                    System.out.print("Digite o ID do cliente: ");
                    id = input.nextLine();
                    DAO.getClienteDAO().delete(id);
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