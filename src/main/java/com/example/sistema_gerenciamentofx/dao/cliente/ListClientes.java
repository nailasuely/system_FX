package com.example.sistema_gerenciamentofx.dao.cliente;
import com.example.sistema_gerenciamentofx.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ListClientes implements ClienteDAO{

    private List<Cliente> listaClientes;
    private int id;
    public ListClientes() {
        this.listaClientes = new ArrayList<Cliente>();
        this.id = 0;
    }
    public Cliente create(ArrayList<Cliente> clientes,Cliente client, String adress, String CPF,String nome, int telefone){
        client.setId(this.id);
        client.setAddress(adress);
        client.setCpf(CPF);
        client.setFullName(nome);
        client.setTelephone(telefone);
        if(findByCpfIsTrue(CPF)){
            System.out.println("Esta pessoa ja existe no sistema");
            return null;
        }
        else {
            this.id++;
            this.listaClientes.add(client);
            return client;
        }
    }



    @Override
    public void update(Cliente cliente, String dadoAtt, ) {
        //BUSCA PELO CPF, PARA ACHAR O ID, AI COM ISSO FAZ OS ifs PARA VER
        //QUAL A DADO QUER TROCAR, A PARTIR DA STRING dadoATT QUE GUARDA O QUE QUER ATUALIZAR
        //PODE ATT TUDO, OU UM SO DADO
        //OPCAO COLOCAR UMA ARRAY DE STRINGS, PQ AI DEPOIS NA HORA DE SELECIONAR PODE SER MELHOR
        //PODE ENT ADD VARIAS COISAS QUE VAI ATT ALI NAQUELE MOMENTO
        //OU IMPLEMENTAÇÃO COM A hashMap
    }

    @Override
    public void delete(Cliente objeto) {

    }

    @Override
    public Cliente findById(int id) {
        return null;
    }

    @Override
    public Cliente listObjects(ArrayList list) {
        return null;
    }

    @Override
    public Cliente findByCPF(String cpf) {
        return null;
    }

    @Override
    public boolean findByCpfIsTrue(String cpf) {
        return false;
    }
}
