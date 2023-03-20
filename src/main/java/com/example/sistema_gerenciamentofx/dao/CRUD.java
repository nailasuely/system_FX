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
    public void delete(T objeto);
    public T findById(int id);
    public T listObjects(ArrayList list);
}
