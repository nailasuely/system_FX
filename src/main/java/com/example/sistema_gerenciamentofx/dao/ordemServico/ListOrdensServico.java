package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.DAO;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;
import com.example.sistema_gerenciamentofx.model.Tecnico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListOrdensServico implements OrdemServicoDAO{
    private List<OrdemServico> listaOrdensServico;
    private int indiceClienteParaAtender = 0;


    public ListOrdensServico(){
        this.listaOrdensServico = new ArrayList<OrdemServico>();
    }


    @Override
    public OrdemServico create(OrdemServico ordem, String clienteID, Produto type) {
        LocalDate inicio = LocalDate.now();
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        //lembrar de verificar dps;
        ordem.setId(newIDStrign);
        ordem.setStart(inicio);
        ordem.setClientId(clienteID);
        ordem.setType(type);
        this.listaOrdensServico.add(ordem);

        return ordem;
    }

    public void atualizarStatusAndamento(String cpfTecnico, OrdemServico ordem){
       if (listaOrdensServico != null){
           // aqui o método addservideorder retorna true se o tecnico está com a lista
           // de serviços vazia ou todas finalizadas. Se estiver ele atualiza a ordem para
           // andamento
           if(DAO.getTecnicoDAO().findByCPF(cpfTecnico).addServiceOrder(ordem,
              DAO.getTecnicoDAO().findByCPF(cpfTecnico).getId())){
           listaOrdensServico.get(indiceClienteParaAtender).setStatus("andamento");
           indiceClienteParaAtender++;
           System.out.println(listaOrdensServico);
           }
       }
       else{
           throw new IllegalArgumentException("O técnico não pode aceitar novas ordens no momento");
       }
    }

    // essa classe é apenas para não gerar erros no crud;
    @Override
    public OrdemServico create(OrdemServico ordem) {
        LocalDate inicio = LocalDate.now();
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        //lembrar de verificar dps;
        ordem.setId(newIDStrign);
        ordem.setStart(inicio);
        this.listaOrdensServico.add(ordem);
        return null;
    }

    @Override
    public void update(OrdemServico ordem) {
        boolean status = false;
        for (int i=0; i< this.listaOrdensServico.size();i++){
            if(listaOrdensServico.get(i).getId() == ordem.getId()){
                this.listaOrdensServico.set(i, ordem);
                return;
            }
        }
        if(!status){
            throw new IllegalArgumentException("Ordem não detectado no banco de dados");
        }

    }

    @Override
    public void delete(String ID) {
        boolean status = false;
        for (int i=0; i< this.listaOrdensServico.size();i++){
            if(listaOrdensServico.get(i).getId() == ID){
                this.listaOrdensServico.remove(i);
                return;
            }
        }
        if(!status){
            throw new IllegalArgumentException("Cliente não detectado no banco de dados");
        }
    }

    @Override
    public OrdemServico findById(String id) {
        for(OrdemServico ordem: this.listaOrdensServico){
            if(ordem.getId().equals(id)){
                return ordem;
            }
        }
        return null;
    }



    @Override
    public void listObjects() {
        if(this.listaOrdensServico.size()>0){
            Tecnico tecnico;
            for(OrdemServico ordem: this.listaOrdensServico){
                System.out.println("ID da ordem: "+ordem.getId());
                tecnico = DAO.getTecnicoDAO().findById(ordem.getTechnicianID());
                System.out.println("Tecnico da ordem: " + tecnico.getFullName());
            }
        }
    }

    @Override
    public void deleteMany() {
        this.listaOrdensServico = new ArrayList<>();
    }

    @Override
    public int amountItems() {
        return listaOrdensServico.size();
    }

    public OrdemServico openOrderByTechnician(String cpf){
        String idTecnico;
        idTecnico = DAO.getTecnicoDAO().findIdbyCPF(cpf);
        if(this.listaOrdensServico.size()>0){
            for(OrdemServico ordem: this.listaOrdensServico){
                if(ordem.getTechnicianID().equals(idTecnico)){
                    return ordem;
                }
            }
        }
        return null;
    }
}
