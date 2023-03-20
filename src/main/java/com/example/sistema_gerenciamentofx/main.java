package com.example.sistema_gerenciamentofx;

import java.util.Scanner;

/* Essa main foi criada de modo a realizar testes do programa a interface gráfica
 */

public class main{
    public static void main(String[] args) {
        int escolha = 1;
        System.out.println("------- SISTEMA DE GERENCIAMENTO ------- ");
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("1. REGISTRAR CLIENTE");
            System.out.println("2. REGISTRAR TÉCNICO");
            System.out.println("3. CRIAR ORDEM DE SERVIÇO");
            System.out.println("4. GERAR RELATÓRIOS");
            System.out.println("0. Parar");
            System.out.print("Digite uma opçao: ");
            escolha = input.nextInt();
            switch (escolha) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

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