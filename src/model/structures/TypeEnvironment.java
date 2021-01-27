package model.structures;

import model.types.IType;
import model.values.IValue;
import model.values.StringValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TypeEnvironment implements IDictionary<String, IType> {
    private Map<String, IType> elements;

    public TypeEnvironment(){
        elements = new HashMap<>();
    }

    public IType get(String key){
        return elements.get(key);
    }

    public void put(String key, IType value){
        elements.put(key, value);
    }

    public boolean containsKey(String key){
        return elements.containsKey(key);
    }

    @Override
    public void remove(String key){
        elements.remove(key);
    }


    @Override
    public Map<String, IType> getContent(){
        return elements;
    }

    @Override
    public IDictionary<String, IType> deepCopy(){
        IDictionary<String, IType> newTypeEnvironment = new TypeEnvironment();
        for (HashMap.Entry<String, IType> element: elements.entrySet()) {
            newTypeEnvironment.put((element.getKey()), element.getValue());
        }
        return newTypeEnvironment;
    }

    @Override
    public Set<String> keySet(){
        return elements.keySet();
    }

    @Override
    public String toString(){
        StringBuilder symbolsString = new StringBuilder();
        for (HashMap.Entry<String, IType> element: elements.entrySet()){
            String key = element.getKey();
            IType value = element.getValue();
            symbolsString.append(key).append("->").append(value.toString()).append("\n");
        }
        return symbolsString.toString();
    }
}
