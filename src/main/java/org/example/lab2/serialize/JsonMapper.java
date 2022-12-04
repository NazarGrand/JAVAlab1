package org.example.lab2.serialize;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class JsonMapper<T> implements IMapper<T> {
    private ObjectMapper objectMapper;
    private Class<?> tClass;


    public JsonMapper() {
        this.tClass = getClass();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void writeObject(String fileName, T object) {
        try {
            objectMapper.writeValue(new File(fileName), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public T readObject(String fileName, Class<T> tClass) {
        try {
            var res = objectMapper.readValue(Paths.get(fileName).toFile(), tClass);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeList(String fileName, List<T> object) {
        try {
            objectMapper.writeValue(new File(fileName), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> readObjectsList(String fileName, Class<T> tClass) {
        try {
            List<T> res = objectMapper.readerForListOf(tClass).readValue(new File(fileName));
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
