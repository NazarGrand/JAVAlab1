package org.example.lab2.serialize;


import org.example.lab2.exception.ValidationException;

import java.io.IOException;
import java.util.List;


public interface IMapper<T> {
    void writeObject(String fileName, T object) throws IOException;

    T readObject(String fileName, Class<T> tClass) throws IOException, InstantiationException, IllegalAccessException, ValidationException;

    void writeList(String fileName, List<T> object);

    List<T> readObjectsList(String fileName, Class<T> tClass);

}
