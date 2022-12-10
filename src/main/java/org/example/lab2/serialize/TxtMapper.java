package org.example.lab2.serialize;

import java.io.*;

public class TxtMapper<T> implements IMapper<T> {

    @Override
    public void writeObject(String fileName, T object) {

        try (FileOutputStream fileOutputStream
                     = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public T readObject(String fileName, Class<T> tClass) {
        T result = null;
        try (FileInputStream fileInputStream
                     = new FileInputStream(fileName)) {
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            result = (T) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}
