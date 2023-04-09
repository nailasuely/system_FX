package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;

import java.util.List;

public interface OrdemServicoDAO extends CRUD<OrdemServico> {
    public OrdemServico openOrderByTechnician(String cpf);

    public OrdemServico create(OrdemServico ordem, String clienteID, Produto type);

    public void atualizarStatusAndamento(String cpfTecnico, OrdemServico ordem);

    public List<OrdemServico> getList();
}
