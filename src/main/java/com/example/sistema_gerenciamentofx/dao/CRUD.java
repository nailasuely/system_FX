package com.example.sistema_gerenciamentofx.dao;

import java.util.ArrayList;

/*
Essa interface foi criada de modo a seguir o critério
"Padrão de projeto DAO (Data Access Object) para o CRUD" da
segunda fase do PBL.
 */

public interface CRUD<T> {
    public T create(T objeto);
    public void update(T objeto);
    public void delete(String ID);
    public T findById(String id);
    public void listObjects(ArrayList list);
    public void deleteMany();
    public int amountItems();

}
