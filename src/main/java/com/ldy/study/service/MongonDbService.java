package com.ldy.study.service;

public interface MongonDbService<T> {
    public void add(T obj, String collName) throws Exception;

    public void delete(T obj, String collName) throws Exception;

    public void update(T obj, String collName) throws Exception;

    public void query(T obj, String collName) throws Exception;

    public void queryAll(Class<T> cls, String collName) throws Exception;

    void createCollection(Class<T> cls, String collName);
}
