package com.example.sistema_gerenciamentofx.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


public class OrdemServico {
    private static int id;
    /*
    private static Date start;
    private static Date end;
    */
    private static LocalDate start;
    private static LocalDate end;
    private static int clientId;
    private static String type;
    //TROCAR ESSE DE BAIXO POR UM MAP - DICIONARIO
    //private static ArrayList<String> itemsList;
    /*Usando dicionario, coloca como chave a peca, e como valor a quantidade dela*/
    private static Map<String, Integer> itemsList;
    private static double price;
    private static String paymentType;
    private static int clientSatisfaction;
    private static String status;
    /*
    Troca do expendTime de Date, para String, visando colocar um valor ja formatado
    *
    */
    private static String expendTime;
    private static String description;

    public OrdemServico() {
        if(start == null){
            start = LocalDate.now();
        }
        if(itemsList == null){
            itemsList = new HashMap<String, Integer>();
        }
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        OrdemServico.id = id;
    }

    public static LocalDate getStart() {
        return start;
    }

    public static void setStart(LocalDate start) {
        OrdemServico.start = start;
    }

    public static LocalDate getEnd() {
        return end;
    }
    //LEMBRAR
    //chamar a setEnd no metodo finalizar
    public static void setEnd(LocalDate end) {
        OrdemServico.end = LocalDate.now();
    }

    public static int getClientId() {
        return clientId;
    }

    public static void setClientId(int clientId) {
        OrdemServico.clientId = clientId;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        OrdemServico.type = type;
    }

    /*
    public static ArrayList<String> getItemsList() {
        return itemsList;
    }

    public static void setItemsList(ArrayList<String> itemsList) {
        OrdemServico.itemsList = itemsList;
    }
    */

    public static double getPrice() {
        return price;
    }

    public static void setPrice(double price) {
        OrdemServico.price = price;
    }

    public static String getPaymentType() {
        return paymentType;
    }

    public static void setPaymentType(String paymentType) {
        OrdemServico.paymentType = paymentType;
    }

    public static int getClientSatisfaction() {
        return clientSatisfaction;
    }

    public static void setClientSatisfaction(int clientSatisfaction) {
        OrdemServico.clientSatisfaction = clientSatisfaction;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        OrdemServico.status = status;
    }

    public static String getExpendTime() {
        return expendTime;
    }
/*
    O SET TA SENDO PREENCHIDO PELO METODO CALCULATE EXPEND TIME
    public static void setExpendTime(Date expendTime) {
        OrdemServico.expendTime = expendTime;
    }
*/
    public static String getDescription() {
        return description;
    }

    /*
    public static String generateInvoice(String type, HashMap itemsList) {
        double finalPrice = calculatePrice(String type, HashMap itemsList);
        if (type == "instalacao" || type == "montagem"){
            Collection <Integer> values = itemsList.values();
            ArrayList<Integer> valuesList = new ArrayList<>(values);
            Integer quantItems=0;
            for (Integer quant: valuesList) {
                quantItems +=quant;
            }
            if (type == "instalacao"){
                return "Tipo do serviço: "+type + ", quantidade de programas: "+quantItems+", o custo total foi de: " + finalPrice;
            }
            else if(type == "montagem"){
                String partsList = "";
                for (String peca: itemsList.entrySet()){
                    partsList +=peca;
                    partsList +=", ";
                }
                return "Tipo do serviço: "+type + ", quantidade de pecas: "+quantItems+", lista de peças: "+partsList+"o custo total foi de: " + finalPrice;
            }
        }
        else{
            return "Tipo do serviço: "+type + ", o custo total foi de: " + finalPrice;
        }


    }*/

    /*
    Faz o cálculo da data de início e data final, por ser um metodo chamado
    dentro do metodo de finalizar... ele pega a data final ja na sua chamada
    */
    public static String calculateExpendTime(LocalDate start, LocalDate end){
        if (end == null){
            end = LocalDate.now();
        }
        Period generateExpendTime = Period.between(start, end);
        int days = generateExpendTime.getDays();
        int months = generateExpendTime.getMonths();
        if (months>0){
            return "Foram gastos "+ days+" dias e " + months + "meses";
        }
        else{
            return "Foram gastos " + days + "dias";
        }
    }


    /*
    public static double calculatePrice(String type, HashMap itemsList){
        Tabela prices = new Tabela();
        double finalPrice=0;
        if(type == "limpeza"){
            return prices.getCleaningService();
        }
        else if(type == "formatacao"){
            return prices.getFormattingService();
        }
        else if(type == "instalacao"){
            int quant=0;
            quant = (int)itemsList.get("instalacao");
            return prices.getInstallationService() * quant;
        }
        else if(type == "montagem"){
            for (String peca: itemsList.entrySet()) {
                if(peca == "ram"){
                    finalPrice+= prices.getRamPrice() * (double) itemsList.get(peca);
                }
                else if(peca == "placa_mae"){
                    finalPrice+= prices.getMotherBoardPrice() * (double) itemsList.get(peca);
                }
                else if(peca == "placa_de_video"){
                    finalPrice += prices.getGraphicBoardPrice() * (double) itemsList.get(peca);
                }
                else if(peca == "ssd"){
                    finalPrice += prices.getSsdPrice() * (double) itemsList.get(peca);
                }
                else if(peca == "fonte"){
                    finalPrice += prices.getFontPrice() * (double) itemsList.get(peca);
                }
            }
            return finalPrice;
        }
        else{
            return 0;
        }
    }
    */

}