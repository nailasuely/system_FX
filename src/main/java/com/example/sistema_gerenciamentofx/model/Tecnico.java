package com.example.sistema_gerenciamentofx.model;
import java.util.ArrayList;
public class Tecnico extends Pessoa {
    private static ArrayList<OrdemServico> serviceOrders;


    /*
    Esse método é responsável por adicionar serviços para o tecnico. Dessa forma, pensando
    no requisito que um tecnico só pode aceitar uma ordem quando todas as suas ordens anteriores já
    estiverem finalizadas, existe uma verificação.
    Primeiro, é verificado se a lista de ordens está vazia. Nesse caso, a ordem é adiconada. Caso
    contrário, um for percorre a lista para verificar se todas ordens estão finalizadas. Se sim, a nova
    ordem é adicionada.
     */
    public void addServiceOrder(OrdemServico servico) {
        int quantidadeServicos = serviceOrders.size();
        int servicosFinalizados = 0;
        if (serviceOrders.isEmpty()){
             serviceOrders.add(servico);
         }
         else{
            for(OrdemServico servicos: serviceOrders){
                if (OrdemServico.getStatus() == "Finalizado"){
                    servicosFinalizados += 1;
                }
            }
            if(quantidadeServicos == servicosFinalizados){
                serviceOrders.add(servico);
            }
         }

    }

    public void finalizeServiceOrder(OrdemServico servico){



    }





}
