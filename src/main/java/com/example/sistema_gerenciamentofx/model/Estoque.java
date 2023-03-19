package com.example.sistema_gerenciamentofx.model;

import java.util.ArrayList;

public class Estoque {
    private int ramAmount;
    private int motherBoardAmount;
    private int fontAmount;
    private int graphicBoardAmount;
    private int ssdAmount;
    private static ArrayList<String> buyOrders;
    private static ArrayList<String> otherItems;

    public boolean verifyAmount(int quantidade, String product){
        if (product == "ram" && this.ramAmount >= quantidade){
            return true;
        }
        else if (product == "ssd" && this.ssdAmount >= quantidade){
            return true;
        }
        else if (product == "placa-mae" && this.motherBoardAmount >= quantidade){
            return true;
        }
        else if (product == "fonte" && this.fontAmount >= quantidade){
            return true;
        }
        else if(product == "placa_de_video" && this.graphicBoardAmount >= quantidade) {
            return true;
        }
        else{
            return false;
        }
    }

    public String alert(){
        /*
        pode ser viavel colocar valores idependentes para cada produto
        pq ai, os que tem mais vendas coloca um valor maior, e os que tem menos
        vendas coloca um valor menor
         */

        if(this.ssdAmount<=10 || this.ramAmount<=10 || this.motherBoardAmount<=10 || this.graphicBoardAmount <=10 || this.fontAmount <=10){
            return "!!!ALERTA!!!\nNivel de reserva do estoque atingido\nRecomenda-se realizar reabastecimento";
        }
        else{
            return null;
        }
    }
    /*
    SOBRE O METODO ABAIXO
        na classe OrdemServico vai chamar ela, para verificar se pode iniciar a ordem
        pensando na quantidade de estoque, ja que se nao tive estoque nao é possivel iniciar a ordem
        daí, esse metodo, ja verifica se tem estoque para isso, e se tiver retira a quantidade do estoque
        além de pegar e retornar true indicando que a ordem pode seguir com seu cadastro
    */
    public boolean removePart(String product, int quantidade){
        if(verifyAmount(quantidade, product)){
            if (product == "ram") {
                this.ramAmount -= quantidade;
            } else if (product == "ssd") {
                this.ssdAmount -= quantidade;
            } else if (product == "placa-mae") {
                this.motherBoardAmount -= quantidade;
            } else if (product == "fonte") {
                this.fontAmount -= quantidade;
            } else if (product == "placa_de_video") {
                this.graphicBoardAmount -= quantidade;
            }
            return true;
        }
        else{
            System.out.println("Quantidade maior que a disponivel no estoque");
            return false;
        }

    }

    public void addPart(String product, int quantidade){
        if (product == "ram") {
            this.ramAmount += quantidade;
        } else if (product == "ssd") {
            this.ssdAmount += quantidade;
        } else if (product == "placa-mae") {
            this.motherBoardAmount += quantidade;
        } else if (product == "fonte") {
            this.fontAmount += quantidade;
        } else if (product == "placa_de_video") {
            this.graphicBoardAmount += quantidade;
        }
    }
}
