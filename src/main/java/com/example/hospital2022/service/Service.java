package com.example.hospital2022.service;

import java.util.List;

public interface Service <T>{
    public List<T> list (String title);
    public T findById(Long id);
    public void save(T t);
    public void delete(T t);
    public T findByTitle(String title);
}
