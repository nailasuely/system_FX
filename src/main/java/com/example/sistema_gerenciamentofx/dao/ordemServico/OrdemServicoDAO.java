package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.OrdemServico;
import com.example.sistema_gerenciamentofx.model.Produto;

public interface OrdemServicoDAO extends CRUD<OrdemServico> {
    public OrdemServico openOrderByTechnician(String cpf);

    OrdemServico create(OrdemServico ordem, String clienteID, Produto type);

    public void atualizarStatusAndamento(String cpfTecnico, OrdemServico ordem);
}
