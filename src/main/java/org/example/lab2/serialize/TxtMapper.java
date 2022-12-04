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
    public T readObject(String fileName, Class<T> tClass) throws IOException, InstantiationException, IllegalAccessException, ValidationException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        String objectLine = sb.toString();
        return  null;//(T) tClass.newInstance().toObject(objectLine);
    }

    @Override
    public void writeList(String fileName, List<T> object) {

    }

    @Override
    public List<T> readObjectsList(String fileName, Class<T> tClass) {
        return null;
    }
}
