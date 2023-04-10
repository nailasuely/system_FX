package com.example.sistema_gerenciamentofx.dao.estoque;

/**
 * Está classe serve apenas para ser possível gerar exceções com mensagem personalizada
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class SemEstoqueException extends Exception {
    public SemEstoqueException(String mensagem) {
        super(mensagem);
    }
}

