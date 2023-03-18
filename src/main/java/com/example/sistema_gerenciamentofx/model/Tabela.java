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

    public void setRamPrice(double ramPrice) {
        this.ramPrice = ramPrice;
    }

    public double getMotherBoardPrice() {
        return motherBoardPrice;
    }

    public void setMotherBoardPrice(double motherBoardPrice) {
        this.motherBoardPrice = motherBoardPrice;
    }

    public double getSsdPrice() {
        return ssdPrice;
    }

    public void setSsdPrice(double ssdPrice) {
        this.ssdPrice = ssdPrice;
    }

    public double getFontPrice() {
        return fontPrice;
    }

    public void setFontPrice(double fontPrice) {
        this.fontPrice = fontPrice;
    }

    public double getGraphicBoardPrice() {
        return graphicBoardPrice;
    }

    public void setGraphicBoardPrice(double graphicBoardPrice) {
        this.graphicBoardPrice = graphicBoardPrice;
    }

    public double getCleaningService() {
        return cleaningService;
    }

    public void setCleaningService(double cleaningService) {
        this.cleaningService = cleaningService;
    }

    public double getInstallationService() {
        return installationService;
    }

    public void setInstallationService(double installationService) {
        this.installationService = installationService;
    }

    public double getFormattingService() {
        return formattingService;
    }

    public void setFormattingService(double formattingService) {
        this.formattingService = formattingService;
    }
}
