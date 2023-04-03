package com.example.sistema_gerenciamentofx;


import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.util.*;

/*
Essa main foi criada de modo a realizar testes do programa
 */

public class main{
    public static void main(String[] args) {
        int escolha = 1; String id;
        int opcao=0;

        ArrayList<Cliente> listaDeClientes = new ArrayList<Cliente>();
        ArrayList<Tecnico> listaDeTecnicos = new ArrayList<Tecnico>();
        ArrayList<Tecnico> listaDeOrdens = new ArrayList<Tecnico>();

        System.out.println("------- SISTEMA DE GERENCIAMENTO ------- ");

        do{
            Scanner input = new Scanner(System.in);
            System.out.println("1. CADASTRAR TÉCNICO");
            System.out.println("2. LISTAR ID DOS TÉCNICOS");
            System.out.println("3. FAZER LOGIN DO TÉCNICO");
            System.out.println("4. PROCURAR CLIENTE PELO ID");
            System.out.println("5. PROCURAR TECNICO");
            System.out.println("6. LISTAR ID DOS CLIENTES");
            System.out.println("0. Parar");
            System.out.print("Digite uma opçao: ");
            escolha = input.nextInt();
            switch (escolha) {
                case 1:
                    String fullName; String CpfTecnico; // só vou pedir o nome por enquanto para testar mais rápido
                    Tecnico novoTecnico = new Tecnico();

                    input.nextLine();
                    System.out.print("Digite o nome do tecnico: ");
                    fullName = input.nextLine();
                    //LEVE BUG AQUI, PARA PODER PROSSEGUIR TEM Q DAR 2 ENTER PARA PODER IR PEDIR O CPF
                    //input.nextLine();
                    System.out.print("Digite o CPF do tecnico: ");
                    CpfTecnico = input.nextLine();
                    if (DAO.getClienteDAO().findByCpfIsTrue(CpfTecnico)){
                        System.out.println("Pessoa ja cadastrada como Cliente");
                        //conferir se é a melhor opção fazer desse jeito
                        System.out.println("Por favor deletar o cliente antes de continuar");
                        break;
                    }

                    novoTecnico.setFullName(fullName);
                    novoTecnico.setCpf(CpfTecnico);

                    //listaDeTecnicos.add(novoTecnico);

                    novoTecnico = (Tecnico) DAO.getTecnicoDAO().create(novoTecnico);
                    break;

                case 2:
                    DAO.getTecnicoDAO().listObjects(listaDeTecnicos);
                    break;
                case 3:
                    input.nextLine();
                    //mudança para login usando o CPF
                    //pode voltar ao id, troquei so pra ficar mais pratico nos testes
                    System.out.print("Digite o CPF do técnico: ");
                    id = input.nextLine(); //VARIAVEL PARA SER TROCADA
                    if (DAO.getTecnicoDAO().findIdbyCPF(id) != null){

                        do {
                            System.out.println("1. REGISTRAR CLIENTE");
                            System.out.println("2. ATUALIZAR CLIENTES");
                            System.out.println("3. DELETAR CLIENTE");
                            System.out.println("4. LISTAR CLIENTES");
                            System.out.println("5. CRIAR ORDENS DE SERVIÇO");
                            System.out.println("6. FINALIZAR ORDEM DE SERVIÇO");
                            System.out.println("7. GERENCIAR ESTOQUE");
                            System.out.println("0. Parar");
                            System.out.print("Digite uma opçao: ");
                            opcao = input.nextInt();
                            switch (opcao) {
                                case 1: // registro de cliente
                                    String name;
                                    String cpf; // só vou pedir o nome por enquanto
                                    Cliente novoCliente = new Cliente();

                                    input.nextLine();
                                    System.out.print("Digite o nome do cliente: ");
                                    name = input.nextLine();
                                    //input.nextLine();
                                    System.out.print("Digite o CPF do cliente: ");
                                    cpf = input.nextLine();
                                    if(DAO.getTecnicoDAO().findByCPFIsTrue(cpf)){
                                        System.out.println("Cliente ja registrado como Tecnico");
                                        break;
                                    }
                                    novoCliente.setFullName(name);

                                    novoCliente.setCpf(cpf);
                                    //listaDeClientes.add(novoCliente);

                                    novoCliente = (Cliente) DAO.getClienteDAO().create(novoCliente);

                                    break;
                                case 2: //atualização de cliente
                                    String CpfClienteAtualizar;
                                    String idClienteAtualizar = "";

                                    Cliente clienteAtt;
                                    input.nextLine();
                                    System.out.println("Primeiro digite o CPF do cliente:");
                                    CpfClienteAtualizar = input.nextLine();
                                    idClienteAtualizar = DAO.getClienteDAO().findIdbyCPF(CpfClienteAtualizar);
                                    if (idClienteAtualizar != "") {
                                        clienteAtt = DAO.getClienteDAO().findById(idClienteAtualizar);
                                        input.nextLine();
                                        System.out.println("Cliente encontrado");
                                        System.out.println("Dado a se atualizar:");
                                        System.out.println("1. NOME DO CLIENTE");
                                        System.out.println("2. TELEFONE");
                                        System.out.println("3. ENDEREÇO");
                                        System.out.println("0. SAIR");
                                        escolha = input.nextInt();
                                        switch (escolha) {
                                            case 1:
                                                String newName;
                                                input.nextLine();
                                                System.out.println("Nome atual: " + clienteAtt.getFullName());
                                                System.out.println("Digite um novo nome: ");
                                                newName = input.nextLine();
                                                clienteAtt.setFullName(newName);
                                                DAO.getClienteDAO().update(clienteAtt);
                                                break;
                                            case 2:
                                                int newTelefone;
                                                input.nextInt();
                                                System.out.println("Telefone atual: " + clienteAtt.getTelephone());
                                                System.out.println("Digite o novo telefone: ");
                                                newTelefone = input.nextInt();
                                                clienteAtt.setTelephone(newTelefone);
                                                DAO.getClienteDAO().update(clienteAtt);
                                                break;
                                            case 3:
                                                String newAdress;
                                                input.nextLine();
                                                System.out.println("Endereço atual:"+ clienteAtt.getAddress());
                                                System.out.println("Digite o novo endereço: ");
                                                newAdress = input.nextLine();
                                                clienteAtt.setAddress(newAdress);
                                                DAO.getClienteDAO().update(clienteAtt);
                                                break;
                                            case 0:
                                                System.out.println("Saindo do menu de atualização cadastral");
                                                break;

                                        }
                                        //while (escolha != 0) ;
                                    }

                                    break;
                                case 3: //deletar um cliente
                                    int escolhaDesejada = 0;

                                    System.out.println("Deseja deletar o cliente por:\n" +
                                            "1. ID do cliente\n" +
                                            "2. CPF do cliente\n" +
                                            "3. Sair deste menu");
                                    escolhaDesejada = input.nextInt();
                                    switch (escolhaDesejada) {
                                        case 1:
                                            String idClienteDelete;
                                            input.nextLine();
                                            System.out.println("Digite o ID do cliente: ");
                                            idClienteDelete = input.nextLine();
                                            DAO.getClienteDAO().delete(idClienteDelete);
                                            break;
                                        case 2:
                                            String cpfClienteDelete;

                                            input.nextLine();
                                            System.out.println("Digite o CPF do cliente: ");
                                            cpfClienteDelete = input.nextLine();
                                            idClienteDelete = DAO.getClienteDAO().findIdbyCPF(cpfClienteDelete);
                                            DAO.getClienteDAO().delete(idClienteDelete);
                                            break;
                                        default:
                                            System.out.println("Opcao invalida");
                                            break;
                                    }
                                    break;
                                case 4:
                                    DAO.getClienteDAO().listObjects(listaDeClientes);
                                    break;
                                case 5: //CRIAR ORDEM DE SERICO
                                    String identificadorCliente;
                                    String tipoServico = "";
                                    String tipoPeca = "";
                                    HashMap<String, Integer> itemsList = new HashMap<String, Integer>();
                                    Integer quantidadePeca;
                                    int escolhaPeca;
                                    int escolhaTipo;
                                    int quantidade=0;
                                    OrdemServico ordem = new OrdemServico();
                                    System.out.println("MENU DE CRIAÇÂO DE ORDEM");
                                    input.nextLine();
                                    System.out.println("Por favor identifique o cliente com seu CPF: ");
                                    identificadorCliente = input.nextLine();
                                    ordem.setClientId(DAO.getClienteDAO().findIdbyCPF(identificadorCliente));
                                    ordem.setTechnicianID(DAO.getTecnicoDAO().findIdbyCPF(id));
                                    //estrutura para evitar que o tecnico digite errado o nome, e
                                    //dê erro no resto do programa
                                    do {
                                        System.out.println("QUAL O TIPO DO SERVICO: \n" +
                                                "1. FORMATACAO\n" +
                                                "2. INSTALACAO\n" +
                                                "3. MONTAGEM\n" +
                                                "4. LIMPEZA\n"+
                                                "5. NENHUM\n");
                                        escolhaTipo = input.nextInt();
                                        if (escolhaTipo == 1) {
                                            tipoServico = "formatacao";}
                                        else if (escolhaTipo == 2) {
                                            tipoServico = "instalacao";
                                            System.out.println("Quantidade de programas: ");
                                            quantidade = input.nextInt();}
                                        else if (escolhaTipo == 3) {
                                            tipoServico = "montagem";
                                            int querMais = 1;
                                            while(querMais == 1) {
                                                System.out.println("Informe a peça: \n" +
                                                        "1. memoria Ram\n" +
                                                        "2. placa mae\n" +
                                                        "3. placa de video\n" +
                                                        "4. hdd/ssd\n" +
                                                        "5. fonte");
                                                escolhaPeca = input.nextInt();
                                                if (escolhaPeca == 1) {
                                                    tipoPeca = "ram";
                                                } else if (escolhaPeca == 2) {
                                                    tipoPeca = "placa_mae";
                                                } else if (escolhaPeca == 3) {
                                                    tipoPeca = "placa_de_video";
                                                } else if (escolhaPeca == 4) {
                                                    tipoPeca = "ssd";
                                                } else if (escolhaPeca == 5) {
                                                    tipoPeca = "fonte";
                                                }
                                                System.out.println("Quantidade desejada: ");
                                                quantidadePeca = input.nextInt();
                                                itemsList.put(tipoPeca, quantidadePeca);
                                                System.out.println("Deseja incluir mais itens:\n" +
                                                        "1. SIM\n" +
                                                        "2. NAO");
                                                querMais = input.nextInt();
                                            }
                                        } else if (escolhaTipo == 4) {
                                            tipoServico = "limpeza";
                                        }
                                    }while (escolhaTipo != 5);
                                    ordem.setType(tipoServico);
                                    ordem.setStatus("em espera");
                                    ordem.setItemsList(itemsList);
                                    DAO.getOrdemServicoDAO().create(ordem);
                                    System.out.println("Ordem criada com sucesso");
                                    DAO.getOrdemServicoDAO().listObjects(listaDeOrdens);
                                    break;
                                case 6: //atualizar ordem de servico
                                    OrdemServico ordemServico = null;
                                    input.nextLine();
                                    String pagamento;
                                    int satisfaction;
                                    String confirmacao;
                                    System.out.println("Deseja finalizar a Ordem Atual? [s]im, [n]ão");
                                    confirmacao = input.nextLine();
                                    if(confirmacao.equals("s")){
                                        ordemServico = DAO.getOrdemServicoDAO().openOrderByTechnician(id);
                                        System.out.println(ordemServico);
                                        input.nextLine();
                                        System.out.println("Forma de pagamento: ");
                                        pagamento = input.nextLine();
                                        System.out.println("Satisfação do cliente: ");
                                        satisfaction = input.nextInt();
                                        ordemServico.finalize(ordemServico.getStart(),satisfaction,pagamento);
                                        DAO.getOrdemServicoDAO().update(ordemServico);
                                    }
                                    input.nextLine();
                                    System.out.println("Imprimir o invoice? [s]im, [n]ão");
                                    confirmacao = input.nextLine();
                                    if(confirmacao.equals("s") & ordemServico !=null){
                                        System.out.println(ordemServico.generateInvoice(ordemServico.getType(), ordemServico.getItemsList()));
                                    }

                                    break;
                                case 7: //gerenciar o estoque

                                    break;
                                case 0:
                                    System.out.println("Finalizando...");
                                    break;
                                default:
                                    System.out.println("Digite uma opção válida, por favor!");
                                    break;
                            }
                        }while(opcao!=0);

                    }

                    break;
                case 4:
                    input.nextLine();
                    System.out.print("Digite o ID do cliente: ");
                    id = input.nextLine();
                    Cliente novoC = new Cliente();
                    novoC = DAO.getClienteDAO().findById(id);
                    if(novoC != null){
                        System.out.println("ID do cliente encontrado: "+ novoC.getId());
                    }
                    break;
                case 5:
                    Tecnico novoT;
                    String cpf;
                    input.nextLine();
                    System.out.print("Digite o CPF do tecnico: ");
                    cpf = input.nextLine();
                    novoT = DAO.getTecnicoDAO().findByCPF(cpf);
                    System.out.println("Nome do tecnico: "+novoT.getFullName());
                    break;
                case 6:
                    DAO.getClienteDAO().listObjects(listaDeClientes);
                    break;
                case 0:
                    System.out.println("Finalizando...");
                default:
                    System.out.println("Digite uma opção válida, por favor!");
                    break;
            }

        }while(escolha != 0);

    }

}