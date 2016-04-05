package com.xproject.infrastructure;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuiba
 * @param <T>
 */
public interface IRepository<T> {
    boolean add(T entity);
    boolean update(T entity);
    boolean delete(int id);
    T getById(int id);
    List<T> getAll();
    List<T> search(String condition);
}
