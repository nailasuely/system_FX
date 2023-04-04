package com.example.sistema_gerenciamentofx.model;
import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.estoque.SemEstoqueException;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;


public class OrdemServico {

    private String id;

    private LocalDate start;
    private LocalDate end;
    private String clientId;
    private String technicianID;
    private String type;

    /*Usando dicionario, coloca como chave a peca, e como valor a quantidade dela*/
    //private static HashMap<String, Integer> itemsList;

    private List<Produto> listaServicos; // pode ser de itens também
    private double price = 0;
    private String paymentType;
    private int clientSatisfaction;
    private static String status;
    private String expendTime;
    private String description;

    public OrdemServico() {
        if(start == null){
            start = LocalDate.now();
        }
       if(listaServicos == null){
            listaServicos = new ArrayList<>();
        }
    }

    // nao sei se essa classe pode realmente ficar aqui pq vai ter manipulação de lista.
    // aqui é tipo o setServiços
    public void setListaServicos(Produto produto, int quantidade) throws SemEstoqueException {
        listaServicos.add(produto);
        DAO.getEstoqueDAO().retirarEstoque(produto, quantidade);

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


    /*public HashMap<String, Integer> getItemsList() {
        return itemsList;
    }*/

    /*public void setItemsList(HashMap<String, Integer> itemsList) {
        this.itemsList = itemsList;
    }*/


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
        if (listaServicos.size() > 0) {
        for (Produto item : listaServicos) {
            price += item.getPreco(); }
        }
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        if (paymentType.equals("transferencia") ||
            paymentType.equals("cartao") ||
            paymentType.equals("dinheiro") ||
            paymentType.equals("pix")) {
            this.paymentType = paymentType;
        } else {
            throw new IllegalArgumentException("O tipo de pagamento que você " +
                    "colocou não é válido no sistema" + paymentType);
        }
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
        OrdemServico.status = "espera";
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
        if (type.equals("instalacao") || type.equals("montagem")){
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
                return "Tipo do serviço: "+type + ", quantidade de pecas: "+ 0 +", lista de peças: "+ 0 +"o custo total foi de: " + 0+"tempo que durou: " + this.getExpendTime();
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
        return Period.between(start, end);
    }

    public void setExpendTime(String expendTime) {
        this.expendTime = expendTime;
    }

    //AO FINALIZAR A ORDEM, CHAMA ESSE METODO PARA FAZER O PROCESSO DE FINALIZAÇÃO
    public void finalize(int satisfactionClient, String paymentForm){
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

    public String toString() {
        return "OrdemServico{\n" +
                "id='" + id + '\n' +
                ", start=" + start +'\n' +

                ", clientId='" + clientId + '\n' +
                ", technicianID='" + technicianID + '\n' +
                ", type='" + type + '\n' +
                ", itemsList=" + listaServicos +
                '}';
    }




}