package org.example.lab2.serialize;


import org.example.lab2.exception.ValidationException;

import java.io.*;
import java.util.List;

public class TxtMapper<T > implements IMapper<T> {

    @Override
    public void writeObject(String fileName, T object) {

        try(FileOutputStream fileOutputStream
                = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

//        try(FileWriter fos = new FileWriter(fileName)){
//            fos.write(object.toStringSerialize());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public T readObject(String fileName, Class<T> tClass){
        T result = null;
        try(FileInputStream fileInputStream
                    = new FileInputStream(fileName)) {
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
             result = (T) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void writeList(String fileName, List<T> object) {

    }

    @Override
    public List<T> readObjectsList(String fileName, Class<T> tClass) {
        return null;
    }
}
