package com.example.sistema_gerenciamentofx.model;
import java.util.ArrayList;
import java.util.Date;

public class OrdemServico {
    private static int id;
    private static Date start;
    private static Date end;
    private static int clientId;
    private static String type;
    private static ArrayList<String> itemsList;
    private static double price;
    private static String paymentType;
    private static int clientSatisfaction;
    private static String status;
    private static Date expendTime;
    private static String description;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        OrdemServico.id = id;
    }

    public static Date getStart() {
        return start;
    }

    public static void setStart(Date start) {
        OrdemServico.start = start;
    }

    public static Date getEnd() {
        return end;
    }

    public static void setEnd(Date end) {
        OrdemServico.end = end;
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

    public static ArrayList<String> getItemsList() {
        return itemsList;
    }

    public static void setItemsList(ArrayList<String> itemsList) {
        OrdemServico.itemsList = itemsList;
    }

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

    public static Date getExpendTime() {
        return expendTime;
    }

    public static void setExpendTime(Date expendTime) {
        OrdemServico.expendTime = expendTime;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        OrdemServico.description = description;
    }
}
