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
    /**
     *O atributo <b>clientSatisfaction</b>, do tipo <i>int</i>, serve para poder armazenar a nota dada pelo cliente sobre a satisfação do mesmo com relação ao serviço realizado
     */
    private int clientSatisfaction;
    /**
     * O atributo <b>status</b>, do tipo String, serve para armazenar qual status da ordem naquele momento<br>
     * Eles podem ser:
     * <ul>
     *     <li>
     *         "em espera"
     *     </li>
     *     <li>
     *         "em andamento"
     *     </li>
     *     <li>
     *         "cancelada"
     *     </li>
     *     <li>
     *         "finalizada"
     *     </li>
     * </ul>
     */
    private String status;
    /**
     * O atributo <b>expendTime</b>, do tipo <i>String</i>, serve para poder armazenar o tempo que passou até a
     * ordem ser finalizada, partindo do momento da sua crioção
     */
    private String expendTime;
    /**
     * O atributo <b>description</b>, do tipo <i>String</i>, serve para armazer a descrição daquele serviço, contendo
     * informações como:<ul>
     *     <li>
     *         Preço do serviço prestado
     *     </li>
     *     <li>
     *         Serviços e peças que foram escolhidas pelo cliente
     *     </li>
     *     <li>
     *         Tempo de duração da ordem de serviço
     *     </li>
     *     <li>
     *         Informações de identificação do tecnico e do cliente
     *     </li>
     *     <li>
     *         Forma de pagamento escolhida pelo usuario
     *     </li>
     *     <li>
     *         Satisfação do cliente com o serviço
     *     </li>
     * </ul>
     */
    private String description;

    /**
     * Método construtor da classe, o qual já preenche os atributos: <b>start, status</b>. Além de iniciar a
     * lista que conterá produtos/peças.<br>
     * Essa lista pode ser vazia, a depender do serviço escolhido pelo cliente;
     */
    public OrdemServico() {
        if(start == null){
            start = LocalDate.now();
            end = null;
        }
        if(produtoLists == null){
            produtoLists = new ArrayList<>();
        }
        if(status == null){
            this.status = "espera";
        }
    }

    //PERGUNTAR A NAI
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

    /**
     * Método para obter a lista de produtos presente na ordem de serviço
     * @return <i>List</i> que contém os produtos presente na lista. Lembrando que ela pode estar vazia a depender do serviço escolhido
     */
    public List<Produto> getProdutoLists() {
        return produtoLists;
    }

    /**
     * Serve para preencher com ID da ordem, este que é gerado aleatoriamente no método construtor da classe.
     * @param id Valor de ID para preencher o atributo
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método para obter o ID que esta vinculado a ordem de serviço
     * @return <i>String</i> contendo o valor do ID vinculado a ordem de serviço
     */
    public String getId() {
        return id;
    }

    /**
     * Método para obter a data em que foi iniciada a ordem de serviço
     * @return Objeto do tipo <i>LocalDate</i> contendo a data no formato YYYY-MM-DD
     */
    public LocalDate getStart() {
        return start;
    }

    /**
     * Serve para preencher com valor da data em que foi iniciada a ordem de serviço
     * @param start Objeto do tipo <i>LocalDate</i> contendo uma data
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    /**
     * Método serve para obter a data em que foi completada a ordem.
     * @return Objeto do tipo <i>LocalDate</i> contendo a data em que a ordem foi finalizada, mas caso ainda esteja em andamento
     * ela terá um retorno nulo
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Serve para definir a data em que a ordem foi finalizada, ou cancelada.<br>
     * Valor preenchido automaticamente ao chamar o método
     */
    public void setEnd() {
        this.end = LocalDate.now();
    }

    /**
     * Método para obter o ID do cliente que requeriu a ordem de serviço
     * @return <i>String</i> contendo o ID do cliente
     */
    public  String getClientId() {
        return clientId;
    }

    /**
     * Serve para preencher o atributo do ID do cliente, e então saber quem requisitou o serviço contido nela
     * @param clientId <i>String</i> contendo o valor do ID do cliente
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    /**
     * Método para obter o ID do tecnico que cadastrou a ordem de serviço e irá trabalhar nela
     * @return <i>String</i> contendo o ID do tecnico
     */
    public String getTechnicianID() {
        return technicianID;
    }
    /**
     * Serve para preencher o atributo do ID do tecnico que vai trabalhar na ordem de serviço
     * @param clientId <i>String</i> contendo o valor do ID do cliente
     */
    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    /**
     * Método para obter o tipo do serviço contratado pelo cliente, seja "formatação", ou "montagem/instalação", ou "limpeza", ou "instalação de programas"
     * @return Objeto do tipo <i>Produto</i> contendo a informação do tipo do serviço
     */
    public Produto getType() {
        return type;
    }

    /**
     * Serve para preencher o atributo do tipo do serviço que foi contratado pelo cliente
     * @param type Objeto do tipo <i>Produto</i> contendo informação do tipo do serviço
     */
    public void setType(Produto type) {
        this.type = new Produto();
    }

    /**
     * Método para obter o preço total dos serviços, e/ou produtos, presentes naquela ordem de serviço
     * @return <i>Double</i> contendo um valor que representa o preço daquele serviço
     */
    public double getPrice() {
        return price;
    }

    /**
     * Serve para preencher o atributo <b>price</b>, que guarda o preço do serviço
     * @param price Variavel do tipo <i>double</i>, para ser trabalhada na lógica interna de preenchimento do preço
     */
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

    /**
     * Método para obter o tipo do pagamento que foi escolhido pelo cliente, para pagar pelo serviço contratado
     * @return <i>String</i> contendo a forma escolhida para realizar o pagamento
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Serve para preencher o atributo do tipo de pagamento, <b>paymentType</b>.<br>
     * Antes de realizar o preenchimento, ele verifica se o tipo enviado é valido, e caso este não seja, ocorre uma exceção
     * @param paymentType <i>String</i> contendo a forma de pagamento escolhida pelo cliente para pagar pelo serviço
     */
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

    /**
     * Método para obter a nota que o cliente deu para o serviço realizado
     * @return <i>Int</i> contendo a nota dada pelo cliente
     */
    public int getClientSatisfaction() {
        return clientSatisfaction;
    }

    /**
     * Serve para preencher o atributo,<b>clientSatisfaction</b>, que guarda a nota dada pelo cliente pelo serviço prestado
     * @param clientSatisfaction <i>Int</i> contendo a nota do cliente
     */
    public void setClientSatisfaction(int clientSatisfaction) {
        this.clientSatisfaction = clientSatisfaction;
    }

    /**
     * Método para obter o Status em que se encontra a ordem de serviço, seja ela "em espera", "em andamento", "cancelada", "finalizada"
     * @return <i>String</i> contendo a informação sobre o status da ordem
     */
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