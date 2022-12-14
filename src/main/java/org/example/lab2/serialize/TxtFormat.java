package org.example.lab2.serialize;

import org.example.lab2.exception.ValidationException;

public interface TxtFormat<T> {
    String toStringSerialize();
    T toObject(String string) throws ValidationException;
}
