package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.model.Cliente;
import com.example.sistema_gerenciamentofx.model.OrdemServico;

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

        ordem.setId(newIDStrign);
        this.listaOrdensServico.add(ordem);

        return ordem;
    }

    @Override
    public void update(OrdemServico objeto) {


    }

    @Override
    public void delete(String ID) {

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
                System.out.println("Tecnico da ordem: " + ordem.);
            }
        }
    }
}
