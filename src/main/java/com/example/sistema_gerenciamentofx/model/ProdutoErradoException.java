package com.example.sistema_gerenciamentofx.model;

/**
 * Está classe é utilizada para gerar exceções com mensagem personalizada.
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public class ProdutoErradoException extends Exception {
        public ProdutoErradoException(String mensagem) {
            super(mensagem);
        }


}
