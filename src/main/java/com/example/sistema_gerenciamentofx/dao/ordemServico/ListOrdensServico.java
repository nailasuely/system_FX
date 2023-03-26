package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListOrdensServico implements OrdemServicoDAO{
    private List<OrdemServico> listaOrdensServico;
    public ListOrdensServico(){
        this.listaOrdensServico = new ArrayList<OrdemServico>();
    }
    @Override
    public OrdemServico create(OrdemServico ordem) {
        UUID newID = UUID.randomUUID();
        String newIDStrign = newID.toString();
        LocalDate inicio = LocalDate.now();

        ordem.setId(newIDStrign);
        ordem.setStart(inicio);
        this.listaOrdensServico.add(ordem);

        return ordem;
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
            throw new IllegalArgumentException("Cliente não detectado no banco de dados");
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
    public void listObjects(ArrayList list) {
        if(this.listaOrdensServico.size()>0){
            for(OrdemServico ordem: this.listaOrdensServico){
                System.out.println("ID da ordem: "+ordem.getId());
                System.out.println("Tecnico da ordem: " + ordem);
            }
        }
    }
}
