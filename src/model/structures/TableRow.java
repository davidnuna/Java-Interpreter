package model.structures;

import model.values.IValue;

public class TableRow <F, S>{
    private final F first;
    private final S second;

    public TableRow(F first, S second){
        this.first = first;
        this.second = second;
    }

    public F getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }
}
