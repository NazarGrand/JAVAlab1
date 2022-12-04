package org.example.lab2.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlMapper<T> implements IMapper<T> {
    private ObjectMapper objectMapper;

    public XmlMapper() {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        objectMapper = new com.fasterxml.jackson.dataformat.xml.XmlMapper(xmlModule);
    }

    @Override
    public void writeObject(String fileName, T object) {
        try{
            objectMapper.writeValue(new File(fileName), object);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public T readObject(String fileName, Class<T> tClass) {
        try{
            var res = objectMapper.readValue(new File(fileName), tClass);
            return res;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeList(String fileName, List<T> object) {
        try{
            objectMapper.writeValue(new File(fileName), object);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> readObjectsList(String fileName, Class<T> tClass) {
        try{
            List<T> res = objectMapper.readerForListOf(tClass).readValue(new File(fileName));
            return res;
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
