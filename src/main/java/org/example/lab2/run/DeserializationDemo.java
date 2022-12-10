package org.example.lab2.run;

import org.example.lab2.model.Bus;
import org.example.lab2.serialize.JsonMapper;
import org.example.lab2.serialize.TxtMapper;
import org.example.lab2.serialize.XmlMapper;

public class DeserializationDemo {
    public static void main(String[] args) {
        Bus bus1, bus2, bus3;
        bus1 = new JsonMapper<Bus>().readObject("bus.json", Bus.class);
        bus2 = new XmlMapper<Bus>().readObject("bus.xml", Bus.class);
        bus3 = new TxtMapper<Bus>().readObject("bus.txt", Bus.class);

        System.out.println(bus1);
        System.out.println(bus2);
        System.out.println(bus3);
    }
}
