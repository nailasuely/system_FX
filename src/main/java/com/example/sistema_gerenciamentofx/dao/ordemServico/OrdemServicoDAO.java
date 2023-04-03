package com.example.sistema_gerenciamentofx.dao.ordemServico;

import com.example.sistema_gerenciamentofx.dao.CRUD;
import com.example.sistema_gerenciamentofx.model.OrdemServico;

public interface OrdemServicoDAO extends CRUD<OrdemServico> {
    public OrdemServico openOrderByTechnician(String cpf);
}
