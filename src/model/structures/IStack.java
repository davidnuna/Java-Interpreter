package model.structures;

import java.util.ArrayList;

public interface IStack<T> {
    void push(T element);
    T pop();
    boolean isEmpty();
    ArrayList<T> asList();
    String toString();
}
