package com.example.sistema_gerenciamentofx.dao.cliente;
import com.example.sistema_gerenciamentofx.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListClientes implements ClienteDAO {

    private List<Cliente> listaClientes;

    public ListClientes(){
        this.listaClientes = new ArrayList<Cliente>();
    }

    @Override
    public Cliente create(Cliente cliente) {
        // Gerar id pseudoaleatório;
        UUID newID = UUID.randomUUID();
        String newIDStrign= newID.toString();

        //lembrar de verificar dps;
        cliente.setId(newIDStrign);
        this.listaClientes.add(cliente);

        return cliente;
    }

    @Override
    public void update(Cliente cliente) {
        boolean encontrado = false;
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getId() == cliente.getId()) {
                this.listaClientes.set(i, cliente);
                encontrado = true;
                return;
            }
        }
        throw new IllegalArgumentException("Cliente não detectado no banco de dados");

    }

    @Override
    public void delete(String id) {
        boolean encontrado = false;
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getId().equals(id)) {
                this.listaClientes.remove(i);
                encontrado = true;
                return;
            }
        }
        if(encontrado = false){
            throw new IllegalArgumentException("Cliente não detectado no banco de dados");
        }
    }

    @Override
    public Cliente findById(String id) {
        for (Cliente cliente : this.listaClientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void listObjects(ArrayList list) {
        for (Cliente cliente : this.listaClientes) {
            System.out.println("ID do cliente: " + cliente.getId() );
        }
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
