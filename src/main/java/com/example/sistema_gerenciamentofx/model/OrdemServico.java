package com.example.sistema_gerenciamentofx.model;
import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.estoque.SemEstoqueException;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * Classe que contém as informações a respeito de uma ordem de serviço, como:
 * <ul>
 *     <li>
 *         id da ordem
 *     </li>
 *     <li>
 *         datas de inicio e fim, assim como tempo de duração do serviço
 *     </li>
 *     <li>
 *         id tanto do cliente, como do tecnico
 *     </li>
 *     <li>
 *         listagem do(s) produto(s), ou serviço escolhido pelo cliente
 *     </li>
 *     <li>
 *         satisfação do cliente
 *     </li>
 *     <li>
 *         status do procedimento daquela ordme, assim como a forma de pagamento
 *     </li>
 * </ul>
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */

public class OrdemServico {
    /**
     * O atributo <b>id</b> serve para salvar a identificação unica da ordem de serviço<br>
     * Cada ordem possui o seu ID gerado aleatoriamente pela biblioteca <i>UUID</i>
     */
    private String id;
    /**
     * O atributo <b>start</b> serve para salvar a data em que a ordem foi criada<br>
     * Preenchido automaticamente pelo construtor da classe
     */
    private LocalDate start;
    /**
     * O atributo <b>end</b> serve para salvar a data em que a ordem de serviço recebeu o status de finalizada<br>
     * Valor preenchido automaticamente ao realizar a mudança de status para <i>finalizado</i>
     */
    private LocalDate end;
    /**
     * O atributo <b>clienteId</b> serve para salvar o <i>ID</i> do cliente, e assim poder identifica-lo, que fez a contratação dos serviços contidos na ordem de serviço
     */
    private String clientId;
    /**
     * O atributo <b>technicianID</b> serve para salvar o <i>ID</i> do tecnico que está
     * responsável pela realização daquela ordem de serviço, permitindo a identicação do mesmo
     */
    private String technicianID;
    /**
     * O atributo <b>>type</b> serve para poder identificar o tipo da ordem, seja ela:<br>
     * <ul>
     *     <li>
     *         Formatação;
     *     </li>
     *     <li>
     *         Instalação de programas;
     *     </li>
     *     <li>
     *         Montagem/instalação;
     *     </li>
     *     <li>
     *         Limpeza
     *     </li>
     * </ul>
     */
    private Produto type;

    /**
     * O atributo <b>produtoLists</b> serve para armazenar a lista de itens, ou serviço, desejados pelo cliente para serem realizados
     */
    private List<Produto> produtoLists; // pode ser de itens também
    /**
     * O atributo <b>price</b> serve para poder armazenar o preço total daquele serviço, o qual vai ser cobrado para o cliente ao finalizar a ordem de serviço
     */
    private double price = 0;
    /**
     * O atributo <b>paymentType</b> serve para poder armazenar a forma de pagamento escolhida pelo cliente<br>
     * Formas que são permitidas:
     * <ul>
     *     <li>
     *          "transferencia"
     *     </li>
     *     <li>
     *         "dinheiro"
     *     </li>
     *     <li>
     *         "cartão"
     *     </li>
     *     <li>
     *         "pix"
     *     </li>
     * </ul>
     */
    private String paymentType;
    
    private int clientSatisfaction;
    private String status;
    private String expendTime;
    private String description;

    public OrdemServico() {
        if(start == null){
            start = LocalDate.now();
        }
        if(produtoLists == null){
            produtoLists = new ArrayList<>();
        }
        if(status == null){
            this.status = "espera";
        }
    }

    public void setListaProdutos(Produto produto, int quantidade) throws SemEstoqueException, ProdutoErradoException {
        // caso seja outro tipo de produto, isso será adicionado no if depois
        if(produto.getNome().equals("ram") ||produto.getNome().equals("placa mae") ||
           produto.getNome().equals("fonte") || produto.getNome().equals("hd/ssd")) {
            produtoLists.add(produto);
            DAO.getEstoqueDAO().retirarEstoque(produto, quantidade);
        }
        else{
            throw new ProdutoErradoException("Apenas produtos podem ser adicionados nessa lista. Você tentou adicionar: " + produto.getNome());
        }

    }

    public List<Produto> getProdutoLists() {
        return produtoLists;
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

    public Produto getType() {
        return type;
    }

    public void setType(Produto type) {
        this.type = new Produto();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        // Lembrando que nessa lista só é adicionada PRODUTOS/PEÇAS, só vai ser maior doq
        // zero se for uma montagem.
        if(type.getNome().equals("montagem")){
            if (produtoLists.size() > 0) {
            for (Produto item : produtoLists) {
                price += item.getPreco(); }
            }
        }
        else if(type != null){
            price += type.getPreco();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getExpendTime() {
        return expendTime;
    }

    public String getDescription() {
        return description;
    }


    public String generateInvoice(Produto type) {
        //double finalPrice = calculatePrice(String type, HashMap itemsList);
        if (type.getNome().equals("instalacao") || type.getNome().equals("montagem")){
            /*Collection <Integer> values = itemsList.values();
            ArrayList<Integer> valuesList = new ArrayList<>(values);
            Integer quantItems=0;
            for (Integer quant: valuesList) {
                quantItems +=quant;
            }*/
            if (type.getNome().equals("instalacao")){
                //return "Tipo do serviço: "+type + ", quantidade de programas: "+quantItems+", o custo total foi de: " + finalPrice;
                return "Tipo do serviço: "+type.getNome() + ", quantidade de programas: "+0+", o custo total foi de: " + 0;
            }
            else if(type.getNome().equals("montagem")){
                /*String partsList = "";
                for (String peca: itemsList.entrySet()){
                    partsList +=peca;
                    partsList +=", ";
                }*/
                //return "Tipo do serviço: "+type + ", quantidade de pecas: "+quantItems+", lista de peças: "+partsList+"o custo total foi de: " + finalPrice;
                return "Tipo do serviço: "+type.getNome() + ", quantidade de pecas: "+ 0 +", lista de peças: "+ 0 +"o custo total foi de: " + 0+"tempo que durou: " + this.getExpendTime();
            }
        }
        else{
            //return "Tipo do serviço: "+type + ", o custo total foi de: " + finalPrice;
            return "Tipo do serviço: "+type.getNome() + ", o custo total foi de: " + 0;
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
                "id=" + id + '\n' +
                "start=" + start +'\n' +
                "status=" + status +'\n' +
                "clientId='" + clientId + '\n' +
                "technicianID='" + technicianID + '\n' +
                "type='" + type.getNome() + '\n' +
                "itemsList=" + produtoLists +
                '}';
    }




}