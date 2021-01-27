package model.structures;

import model.values.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileTable implements IDictionary<StringValue, BufferedReader> {
    private Map<StringValue, BufferedReader> elements;

    public FileTable(){ elements = new HashMap<>(); }

    @Override
    public BufferedReader get(StringValue key){ return elements.get(key); }

    @Override
    public void put(StringValue key, BufferedReader value){
        elements.put(key, value);
    }

    @Override
    public boolean containsKey(StringValue key){
        return elements.containsKey(key);
    }

    @Override
    public void remove(StringValue key){
        elements.remove(key);
    }

    @Override
    public Map<StringValue, BufferedReader> getContent(){
        return elements;
    }

    @Override
    public IDictionary<StringValue, BufferedReader> deepCopy(){
        IDictionary<StringValue, BufferedReader> newFileTable = new FileTable();
        for (HashMap.Entry<StringValue, BufferedReader> element: (newFileTable.getContent()).entrySet()) {
            newFileTable.put((StringValue)(element.getKey()).deepCopy(), element.getValue());
        }
        return newFileTable;
    }

    @Override
    public Set<StringValue> keySet(){
        return elements.keySet();
    }

    @Override
    public String toString(){
        StringBuilder fileString = new StringBuilder();
        for (HashMap.Entry<StringValue, BufferedReader> element: elements.entrySet()){
            StringValue key = element.getKey();
            fileString.append(key.toString()).append("\n");
        }
        return fileString.toString();
    }
}
