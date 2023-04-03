package com.example.sistema_gerenciamentofx.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


public class OrdemServico {

    private String id;
    /*
    private static Date start;
    private static Date end;
    */
    private LocalDate start;
    private LocalDate end;
    private String clientId;
    private String technicianID;
    private String type;
    //TROCAR ESSE DE BAIXO POR UM MAP - DICIONARIO
    //private static ArrayList<String> itemsList;
    /*Usando dicionario, coloca como chave a peca, e como valor a quantidade dela*/
    private static HashMap<String, Integer> itemsList;
    private double price;
    private String paymentType;
    private int clientSatisfaction;
    private static String status;
    /*
    Troca do expendTime de Date, para String, visando colocar um valor ja formatado
    *
    */
    private String expendTime;
    private String description;

    public OrdemServico() {
        if(start == null){
            start = LocalDate.now();
        }
        if(itemsList == null){
            itemsList = new HashMap<String, Integer>();
        }
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }
    //LEMBRAR
    //chamar a setEnd no metodo finalizar
    public void setEnd(LocalDate end) {
        this.end = LocalDate.now();
    }

    public  String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Integer> getItemsList() {
        return itemsList;
    }

    public void setItemsList(HashMap<String, Integer> itemsList) {
        this.itemsList = itemsList;
    }


    /*
    public static ArrayList<String> getItemsList() {
        return itemsList;
    }

    public static void setItemsList(ArrayList<String> itemsList) {
        OrdemServico.itemsList = itemsList;
    }
    */

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getClientSatisfaction() {
        return clientSatisfaction;
    }

    public void setClientSatisfaction(int clientSatisfaction) {
        this.clientSatisfaction = clientSatisfaction;
    }

    public static String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        OrdemServico.status = status;
    }

    public String getExpendTime() {
        return expendTime;
    }
/*
    O SET TA SENDO PREENCHIDO PELO METODO CALCULATE EXPEND TIME
    public static void setExpendTime(Date expendTime) {
        OrdemServico.expendTime = expendTime;
    }
*/
    public String getDescription() {
        return description;
    }


    public String generateInvoice(String type, HashMap itemsList) {
        //double finalPrice = calculatePrice(String type, HashMap itemsList);
        if (type == "instalacao" || type == "montagem"){
            /*Collection <Integer> values = itemsList.values();
            ArrayList<Integer> valuesList = new ArrayList<>(values);
            Integer quantItems=0;
            for (Integer quant: valuesList) {
                quantItems +=quant;
            }*/
            if (type == "instalacao"){
                //return "Tipo do serviço: "+type + ", quantidade de programas: "+quantItems+", o custo total foi de: " + finalPrice;
                return "Tipo do serviço: "+type + ", quantidade de programas: "+0+", o custo total foi de: " + 0;
            }
            else if(type == "montagem"){
                /*String partsList = "";
                for (String peca: itemsList.entrySet()){
                    partsList +=peca;
                    partsList +=", ";
                }*/
                //return "Tipo do serviço: "+type + ", quantidade de pecas: "+quantItems+", lista de peças: "+partsList+"o custo total foi de: " + finalPrice;
                return "Tipo do serviço: "+type + ", quantidade de pecas: "+ 0 +", lista de peças: "+ 0 +"o custo total foi de: " + 0;
            }
        }
        else{
            //return "Tipo do serviço: "+type + ", o custo total foi de: " + finalPrice;
            return "Tipo do serviço: "+type + ", o custo total foi de: " + 0;
        }
        return null;

    }

    /*
    Faz o cálculo da data de início e data final, por ser um metodo chamado
    dentro do metodo de finalizar... ele pega a data final ja na sua chamada
    */
    public Period calculateExpendTime(LocalDate start){
        LocalDate end = null;
        if (end == null){
            end = LocalDate.now();
        }
        Period generateExpendTime = Period.between(start, end);
        int days = generateExpendTime.getDays();
        int months = generateExpendTime.getMonths();
         /*
        if (months>0){
            return "Foram gastos "+ days+" dias e " + months + "meses";
        }
        else{
            return "Foram gastos " + days + "dias";
        }
        */
        return generateExpendTime;
    }

    public void setExpendTime(String expendTime) {
        this.expendTime = expendTime;
    }

    //AO FINALIZAR A ORDEM, CHAMA ESSE METODO PARA FAZER O PROCESSO DE FINALIZAÇÃO
    public void finalize(LocalDate start, int satisfactionClient, String paymentForm){
        Period tempo = calculateExpendTime(start);
        int days = tempo.getDays();
        int months = tempo.getMonths();
        if (months>0){
            this.setExpendTime("Foram gastos "+ days+" dias e " + months + "meses");
        }
        else{
            this.setExpendTime("Foram gastos " + days + "dias");
        }
        this.setEnd(end = LocalDate.now());
        this.setStatus("finalizada");
        this.setClientSatisfaction(satisfactionClient);
        this.setPaymentType(paymentForm);
        //FALTA POR A PARTE DO PRECO PRA FUNCIONAR
        /*
        this.setPrice(this.calculatePrice(this.getType(), this.getItemsList()));
         */


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