package me.bnnq.homework.services.abstractions;

public interface IRepository<T>
{
    T[] getAll();
    T getById(int id);
    void add(T item);
    void update(T item);
    void delete(int id);
}
