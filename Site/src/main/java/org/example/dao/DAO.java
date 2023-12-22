package org.example.dao;

import org.example.entity.User;

import java.util.List;

public interface DAO <T>{
    void save(T t);
    void delete(T t);
    T getById(Integer id);
    List<T> getAll();
    void update(T t);
}
