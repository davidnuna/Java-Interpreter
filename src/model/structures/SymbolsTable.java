package model.structures;

import exceptions.UndeclaredVariable;
import model.values.IValue;
import model.values.StringValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SymbolsTable implements IDictionary<String, IValue> {
    private Map<String, IValue> elements;

    public SymbolsTable(){ elements = new HashMap<>(); }

    @Override
    public IValue get(String key){ return elements.get(key); }

    @Override
    public void put(String key, IValue value){ elements.put(key, value); }

    @Override
    public void remove(String key){
        elements.remove(key);
    }

    @Override
    public boolean containsKey(String key){ return elements.containsKey(key); }

    @Override
    public Map<String, IValue> getContent() {
        return elements;
    }

    @Override
    public IDictionary<String, IValue> deepCopy(){
        IDictionary<String, IValue> newSymbolsTable = new SymbolsTable();
        for (HashMap.Entry<String, IValue> element: (newSymbolsTable.getContent()).entrySet()) {
            newSymbolsTable.put((element.getKey()), ((IValue)element.getValue()).deepCopy());
        }
        return newSymbolsTable;
    }

    @Override
    public Set<String> keySet(){
        return elements.keySet();
    }

    @Override
    public String toString(){
        StringBuilder symbolsString = new StringBuilder();
        for (HashMap.Entry<String, IValue> element: elements.entrySet()){
            String key = element.getKey();
            IValue value = element.getValue();
            symbolsString.append(key).append("->").append(value.toString()).append("\n");
        }
        return symbolsString.toString();
    }
}
