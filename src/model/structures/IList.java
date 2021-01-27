package model.structures;

import java.util.ArrayList;

public interface IList<T> {
    void add(T element);
    ArrayList<T> asList();
    String toString();
}
