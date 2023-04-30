package com.example.sistema_gerenciamentofx;

import com.example.sistema_gerenciamentofx.dao.cliente.ListClientesTest;
import com.example.sistema_gerenciamentofx.dao.estoque.ListEstoqueTest;
import com.example.sistema_gerenciamentofx.dao.ordemServico.ListOrdensServicoTest;
import com.example.sistema_gerenciamentofx.dao.tecnico.ListTecnicosTest;
import com.example.sistema_gerenciamentofx.model.ClienteTest;
import com.example.sistema_gerenciamentofx.model.OrdemServicoTest;
import com.example.sistema_gerenciamentofx.model.ProdutoTest;
import com.example.sistema_gerenciamentofx.model.TecnicoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClienteTest.class,
        OrdemServicoTest.class,
        ProdutoTest.class,
        TecnicoTest.class,
        ListClientesTest.class,
        ListOrdensServicoTest.class,
        ListEstoqueTest.class,
        ListTecnicosTest.class
})

public class TestSuit {
}