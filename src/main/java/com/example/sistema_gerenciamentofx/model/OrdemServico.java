package com.example.sistema_gerenciamentofx.model;
import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.dao.estoque.SemEstoqueException;

import java.io.Serializable;
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
public class OrdemServico implements Serializable {
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
     * O atributo <b>type</b> serve para poder identificar o tipo da ordem, seja ela:<br>
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
     * O atributo <b>produtoLists</b> serve para armazenar, por meio de um dicionário (<i>HashMap</i>), a lista de itens, ou serviço, desejados pelo cliente para serem realizados
     */
    HashMap<Produto, Integer> produtoLists;
    /**
     * O atributo <b>price</b> serve para poder armazenar o preço total daquele serviço, o qual vai ser cobrado para o cliente ao finalizar a ordem de serviço
     */
    private double price = 0;
    /**
     * O atributo <b>paymentType</b> serve para poder armazenar a forma de pagamento escolhida pelo cliente<br>
     * Formas permitidas:
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
            produtoLists = new HashMap<>();
        }
        if(status == null){
            this.status = "espera";
        }
    }

    /**
     * Método serve para poder adicionar itens a lista de produtos que serão utilizados na ordem de serviço.<br>
     * Há verificação interna para conferir se o produto a ser adicionado, esta na lista dos produtos padrão presente no
     * sistema. Além disso ainda é executado o método para poder realizar a retirada do produto do estoque.<br>
     * Caso tenha sido passado um objeto do tipo <i>Produto</i> representando algo diferente de produto, é gerado uma exceção.
     * @param produto Objeto do tipo <i>Produto</i> que indica qual produto está desejando inserir na "lista" de produtos da ordem
     * @param quantidade <i>Int</i> que indica a quantidade daquele produto que vai ser adicionada na "lista"
     * @throws SemEstoqueException Representa a classe de erros, em que pode ser apresentada a mensagem de erro
     * caso o produto não seja encontrado no estoque, ou caso não tenha quantidade daquele produto no estoque
     * @throws ProdutoErradoException Representa a classe de erros, em que pode ser apresentada a mensagem de erro caso
     * tente adicionar na "lista" algo que não seja um produto.
     */
    public void setListaProdutos(Produto produto, int quantidade) throws SemEstoqueException, ProdutoErradoException, Exception {
        // caso seja outro tipo de produto, isso será adicionado no if depois
        if (produto.getNome().equals("ram") || produto.getNome().equals("placa mae") ||
            produto.getNome().equals("fonte") || produto.getNome().equals("hd/ssd")) {
            Integer qtdAtual = produtoLists.get(produto);
            if (qtdAtual == null) {
                produtoLists.put(produto, quantidade);
            } else {
                produtoLists.put(produto, qtdAtual + quantidade);
            }
            DAO.getEstoqueDAO().retirarEstoque(produto, quantidade);
        } else {
            throw new ProdutoErradoException("Apenas produtos podem ser adicionados nessa lista. Você tentou adicionar: " + produto.getNome());
        }
    }

    /**
     * Método para obter a lista de produtos presente na ordem de serviço
     * @return <i>List</i> que contém os produtos presente na lista. Lembrando que ela pode estar vazia a depender do serviço escolhido
     */
    public HashMap<Produto, Integer> getProdutoLists() {
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
    public void setEnd(LocalDate end) {
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
     * @param technicianID <i>String</i> contendo o valor do ID do tecnico.
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
        this.type = type;
    }

    /**
     * Método para obter o preço total dos serviços, e/ou produtos, presentes naquela ordem de serviço
     * @return <i>Double</i> contendo um valor que representa o preço daquele serviço
     */
    public double getPrice() {
        // Lembrando que nessa lista só é adicionada PRODUTOS/PEÇAS, só vai ser maior doq
        // zero se for uma montagem.
        double price = 0.0;
        if (this.type != null){
        if (this.type.getNome().equals("montagem")) {
            if (!this.produtoLists.isEmpty()) {
                for (Produto produto : this.produtoLists.keySet()) {
                    int quantidade = this.produtoLists.get(produto);
                    price += produto.getPreco() * quantidade;
                }
            }
        } else if (this.type != null) {
            price += type.getPreco();
        }}
        return price;
    }


    /**
     * Serve para preencher o atributo <b>price</b>, que guarda o preço do serviço
     * @param price Variavel do tipo <i>double</i>, para ser trabalhada na lógica interna de preenchimento do preço
     */
    public void setPrice(double price) {
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
                    "colocou não é válido no sistema: " + paymentType);
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

    /**
     * Serve para preencher o atributo <b>status</b> com a informação a respeito do status atual da ordem, dentre 4 possibilidades: "em espera", "em andamento", "cancelada", "finalizada"
     * @param status <i>String</i> que contém o status da ordem
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Método para obter o tempo em que a ordem levou do momento da sua criação, até a sua finalização
     * @return <i>String</i> padrão para caso tenha levado dias, ou levado meses.
     * <ul>
     *     <li>
     *         Duração menor que 1 mês: "Foram gastos 5 dias"
     *     </li>
     *     <li>
     *         Duração maior que 1 mês: "Foram gastos 5 dias e 2 meses"
     *     </li>
     * </ul>
     */
    public String getExpendTime() {
        return expendTime;
    }

    /**
     * Método para obter a descrição do serviço, obtida após a finalização da ordem de serviço.<br>
     * Caso a ordem ainda não esteja com status de finalizada, o retorno é nulo
     * @return <i>String</i> contendo a descrição da ordem de maneira detalhada e formatada
     */
    public String getDescription() {
        return description;
    }

    /**
     * Método para gerar a descrição da ordem de serviço, contendo as principais informações que estão contidas na ordem.<br>
     * Dentre as informações:<ul>
     *     <li>
     *         Nome do cliente e do tecnico
     *     </li>
     *     <li>
     *         Produtos, e/ou serviço contratado pelo cliente
     *     </li>
     *     <li>
     *         Tempo de duração da ordem, até ela ser finalizada
     *     </li>
     *     <li>
     *         Preço total da ordem de serviço
     *     </li>
     * </ul>
     * Exemplo de apresentação quando o cliente escolhe o serviço de montagem:<br>
     *"------NOTA FISCAL DA ORDEM------<br>
     * Peça/produto   Quantidade     Preço un.<br>
     * Placa mãe -------- 2 ------- R$100.0<br>
     * ram -------- 2 ------- R$20.0<br>
     * ======================================<br>
     * Quantidade total de itens: 4<br>
     * Preço total da ordem de serviço: R$120<br>
     * Tecnico responsável: Rhian Pablo<br>
     * Cliente requisitante: Naila Suele<br>
     * Tempo de duração da ordem: Foram gastos 5 dias<br>
     * ID da ordem de serviço: "<br>

     * @return <i>String</i> contendo as informações citadas, de maneira formatada para ser apresentada no sistema
     */
    public String generateInvoice() throws Exception{

        if (type.getNome().equals("montagem")){
            String partsList = "";
            Integer quantidadeItens = 0;
            for (Produto peca: this.produtoLists.keySet()) {
                partsList += peca.getNome();
                partsList += " ------- "+this.produtoLists.get(peca)+" ------- R$"+peca.getPreco() + "\n";
                quantidadeItens +=this.produtoLists.get(peca);
            }
            this.description = "-------NOTA FISCAL DA ORDEM-------" + "\n"+
                    "Peça/produto   Quantidade   Preço un." + "\n"+
                    partsList +
                    "======================================" +"\n"+
                    "Quantidade total de itens: " + quantidadeItens+ "\n"+
                    "Preço total da ordem de serviço: R$" + this.price +"\n"+
                    "Tecnico responsável: " + DAO.getTecnicoDAO().findById(this.technicianID).getFullName() +"\n"+
                    "Cliente requisitante: " + DAO.getClienteDAO().findById(this.clientId).getFullName() +"\n" +
                    "Forma de pagamento: "+ this.paymentType +"\n"+
                    "Tempo de duração da ordem: " + this.expendTime +"\n"+
                    "ID da ordem de serviço: " + this.id;


            return "-------NOTA FISCAL DA ORDEM-------" + "\n"+
                    "Peça/produto   Quantidade   Preço un." + "\n"+
                    partsList +
                    "======================================" +"\n"+
                    "Quantidade total de itens: " + quantidadeItens+ "\n"+
                    "Preço total da ordem de serviço: R$" + this.price +"\n"+
                    "Tecnico responsável: " + DAO.getTecnicoDAO().findById(this.technicianID).getFullName() +"\n"+
                    "Cliente requisitante: " + DAO.getClienteDAO().findById(this.clientId).getFullName() +"\n" +
                    "Forma de pagamento: "+ this.paymentType +"\n"+
                    "Tempo de duração da ordem: " + this.expendTime +"\n"+
                    "ID da ordem de serviço: " + this.id;
        }
        else{
            this.description = "------NOTA FISCAL DA ORDEM------" + "\n"+
                    "Serviço                    Preço un." + "\n"+
                    type.getNome()+" --------------- R$" + type.getPreco()+"\n"+
                    "======================================" +"\n"+
                    "Preço total da ordem de serviço: R$" + type.getPreco()+ "\n"+
                    "Tecnico responsável: " + DAO.getTecnicoDAO().findById(this.technicianID).getFullName() +"\n"+
                    "Cliente requisitante: " + DAO.getClienteDAO().findById(this.clientId).getFullName() +"\n"+
                    "Forma de pagamento: "+ this.paymentType +"\n"+
                    "Tempo de duração da ordem: " + this.expendTime + "\n"+
                    "ID da ordem de serviço: "+ this.id;


            return "------NOTA FISCAL DA ORDEM------" + "\n"+
                    "Serviço                    Preço un." + "\n"+
                    type.getNome()+" --------------- R$" + type.getPreco()+"\n"+
                    "======================================" +"\n"+
                    "Preço total da ordem de serviço: R$" + type.getPreco()+ "\n"+
                    "Tecnico responsável: " + DAO.getTecnicoDAO().findById(this.technicianID).getFullName() +"\n"+
                    "Cliente requisitante: " + DAO.getClienteDAO().findById(this.clientId).getFullName() +"\n"+
                    "Forma de pagamento: "+ this.paymentType +"\n"+
                    "Tempo de duração da ordem: " + this.expendTime + "\n"+
                    "ID da ordem de serviço: "+ this.id;
        }
    }

    /*
    Faz o cálculo da data de início e data final, por ser um metodo chamado
    dentro do metodo de finalizar... ele pega a data final ja na sua chamada
    */

    /**
     * Método para realizar o cálculo de tempo entre a criação da ordem de serviço, até o momento da sua finalização
     * @return Objeto do tipo <i>Period</i>, o qual contém o valor de tempo em quantidade de dias, meses, e anos.<br>
     */
    public Period calculateExpendTime(){
        LocalDate end = null;
        if (end == null){
            end = LocalDate.now();
        }
        return Period.between(this.start, end);
    }

    /**
     * Serve para poder preencher o atributo <i>expendTime</i>, que é responsavel por armazenar o tempo de duração daquela ordem de serviço
     * @param expendTime <i>String</i> que contém uma frase informando o tempo que levou para realizar tal procedimento.<br>
     * Duração menor que 1 mês: "Foram gastos 5 dias"<br>
     * Duração maior que 1 mês: "Foram gastos 5 dias e 2 meses"
     *
     */
    public void setExpendTime(String expendTime) {
        this.expendTime = expendTime;
    }

    //AO FINALIZAR A ORDEM, CHAMA ESSE METODO PARA FAZER O PROCESSO DE FINALIZAÇÃO

    /**
     * Método que serve para realizar os procedimentos finais e padrões de quando se finaliza uma ordem de serviço.<br>
     * Este facilita o processo, ao chamar um método e ele gerenciar outros
     * @param satisfactionClient <i>Int</i>, que guarda a nota de satisfação do cliente com relação ao serviço prestado
     *                           </u>
     * @param paymentForm <i>String</i> que contém a escolha do cliente com relação a forma de pagamento, qual tipo de pagamento ela vai querer usar
     */
    public void finalize(int satisfactionClient, String paymentForm){
        Period tempo = calculateExpendTime();
        int days = tempo.getDays();
        int months = tempo.getMonths();
        if (months>0){
            this.setExpendTime("Foram gastos "+ days+" dias e " + months + " meses");
        }
        else{
            this.setExpendTime("Foram gastos " + days + " dias");
        }
        this.setEnd(LocalDate.now());
        this.setStatus("finalizada");
        this.setClientSatisfaction(satisfactionClient);
        this.setPaymentType(paymentForm);

        this.setPrice(this.getPrice());
        //FALTA POR A PARTE DO PRECO PRA FUNCIONAR
        /*
        this.setPrice(this.calculatePrice(this.getType(), this.getItemsList()));
         */


    }

    /**
     * Metodo que é sobrescrito ao metodo original.<br>
     * Serve para personalizar a mensagem de saida, ao chamar a impressão do objeto sem mais outras informações
     * @return <i>String</i> contendo algumas informações sobre uma ordem de serviço
     */
    public String toString() {
        return "OrdemServico{\n" +
                "id: " + id + '\n' +
                "start: " + start +'\n' +
                "end: " + end + '\n' +
                "status: " + status +'\n' +
                "clientId: " + clientId + '\n' +
                "technicianID: " + technicianID + '\n' +
                "type: " + type + '\n' +
                "itemsList: " + produtoLists + '\n' +
                "paymentType: " + paymentType + '\n' +
                "ExpendTime: " + expendTime + '\n' +
                "ClientSatisfaction: " + clientSatisfaction + '\n' +
                "Description: " + description +
                '}';
    }




}