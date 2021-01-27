package model.structures;

import model.values.IValue;

import java.util.ArrayList;

public class OutputStream implements IList<IValue> {
    private final ArrayList<IValue> elements;

    OutputStream(){ elements = new ArrayList<>(); }

    @Override
    public void add(IValue element){ elements.add(element); }

    @Override
    public ArrayList<IValue> asList() { return elements; }

    @Override
    public String toString(){
        StringBuilder outputString = new StringBuilder();
        for (IValue element: elements){
            outputString.append(element.toString()).append("\n");
        }
        return outputString.toString();
    }
}
