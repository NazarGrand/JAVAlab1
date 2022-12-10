package org.example.lab2.serialize;


public interface IMapper<T> {
    void writeObject(String fileName, T object);

    T readObject(String fileName, Class<T> tClass);

}
