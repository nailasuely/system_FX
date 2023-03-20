package com.example.sistema_gerenciamentofx.model;

public class Tabela {
    private double ramPrice;
    private double motherBoardPrice;
    private double ssdPrice;
    private double fontPrice;
    private double graphicBoardPrice;
    private double cleaningService;
    private double installationService;
    private double formattingService;

    public double getRamPrice() {
        return ramPrice;
    }



    public double getMotherBoardPrice() {
        return motherBoardPrice;
    }



    public double getSsdPrice() {
        return ssdPrice;
    }



    public double getFontPrice() {
        return fontPrice;
    }



    public double getGraphicBoardPrice() {
        return graphicBoardPrice;
    }



    public double getCleaningService() {
        return cleaningService;
    }


    public double getInstallationService() {
        return installationService;
    }


    public double getFormattingService() {
        return formattingService;
    }

    /*
    AS FUNÇÕES DE SET ESTÃO REPRESENTADAS PELA UPDATE PRICE

    public void setRamPrice(double ramPrice) {
        this.ramPrice = ramPrice;
    }
    public void setMotherBoardPrice(double motherBoardPrice) {
        this.motherBoardPrice = motherBoardPrice;
    }
    public void setSsdPrice(double ssdPrice) {
        this.ssdPrice = ssdPrice;
    }
    public void setFontPrice(double fontPrice) {
        this.fontPrice = fontPrice;
    }
    public void setGraphicBoardPrice(double graphicBoardPrice) {
        this.graphicBoardPrice = graphicBoardPrice;
    }
    public void setCleaningService(double cleaningService) {
        this.cleaningService = cleaningService;
    }

    public void setInstallationService(double installationService) {
        this.installationService = installationService;
    }

    public void setFormattingService(double formattingService) {
        this.formattingService = formattingService;
    }
    */

    public void updatePrice(String product, double price){
        /*conferir a respeito da string que vai por para escolha depois,
        tipo, o tecnico vai digitar o nome do produto em ingles ou pt-BR
        dependendo disso, ou entao colocar meio q um ID para cada produto
        e ai na hora da escolha passar so o valor, tipo 1 para ramPrice, 2 para ssdPrice, etc
         */
        if (product == "ram"){
            this.ramPrice = price;
        }
        else if (product == "ssd"){
            this.ssdPrice = price;
        }
        else if (product == "placa-mae"){
            this.motherBoardPrice = price;
        }
        else if (product == "fonte"){
            this.fontPrice = price;
        }
        else if(product == "placa_de_video"){
            this.graphicBoardPrice = price;
        }
        else if(product == "formatacao"){
            this.formattingService = price;
        }
        else if(product == "instalacao"){
            this.installationService = price;
        }
        else if(product == "limpeza"){
            this.cleaningService = price;
        }

    }

}
