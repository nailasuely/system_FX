package com.example.sistema_gerenciamentofx.dao;

import java.util.ArrayList;

/*
Essa interface foi criada de modo a seguir o critério
"Padrão de projeto DAO (Data Access Object) para o CRUD" da
segunda fase do PBL.
 */
/**
 * Interface criada para conter os métodos do CRUD (Create, Read, Update, Delete), afim de melhor organizar o código, já que
 * esses métodos são padrões as outras classes DAO, de armazenamento e manipulação, presentes no programa.<br>
 * Essa interface contém o nome dos métodos os quais a implementação é específica de cada uma das classes que utilizada essa interface
 * @param <T> Parametro a ser preenchido com o tipo do objeto em que a classe que faz a implementação da interface faz uso
 * @author Naila Suele e Rhian Pablo
 * @since 2023
 */
public interface CRUD<T> {
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para adicionar o objeto ao sistema de armazenamento
     * @param objeto Colocado como objeto do tipo <i>T</i>, para generalizar, em que esse <i>T</i>
     *               irá virar objeto de um tipo existente no sistema
     * @return Objeto do tipo <i>T</i>, em que na aplicação dessa interface generica assumirá outro tipo, não mais generico
     */
    public T create(T objeto);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método é responsável por fazer uma atualização do objeto ja armazenado
     * @param objeto Colocado como objeto do tipo <i>T</i>, para generalizar, em que esse <i>T</i>
     *               irá virar objeto de um tipo existente no sistema
     */
    public void update(T objeto);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para retirar um objeto do sistema de armazenamento
     * @param ID <i>String</i> contendo um valor de ID ao se referir a ordem de serviço, e ao se referir tanto a <b>tecnico</b>,
     *           como a <b>cliente</b>, tem de ser passado o <i><b>CPF</b></i>
     */
    public void delete(String ID);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para encontrar aquele objeto partindo do ID associado ao mesmo
     * @param id <i>String</i> contendo um valor de ID
     * @return Objeto do tipo que foi procurado
     */
    public T findById(String id);
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para listar os objetos armazenados
     */
    public void listObjects();
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para deletar todos os objetos armazenados na sua implementação
     */
    public void deleteMany();
    /**
     * Assinatura de método que serve para acesso ao método contido na implementação.<br>
     * Tal método serve para poder saber a quantidade de objetos que estão armazenados no sistema
     * @return Um inteiro contendo a quantidade de itens armazenadas naquela implementação
     */
    public int amountItems();

}
