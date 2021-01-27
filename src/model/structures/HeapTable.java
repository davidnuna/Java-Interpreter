package model.structures;

import model.values.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HeapTable extends HashMap<Integer, IValue> implements IHeap<Integer, IValue> {
    private final AtomicInteger free;

    public HeapTable(){
        super();
        free = new AtomicInteger(0);
    }

    @Override
    synchronized public int alloc(IValue value){
        int newFree = free.incrementAndGet();
        super.put(Integer.valueOf(newFree), value);
        return newFree;
    }

    @Override
    synchronized  public Map<Integer, IValue> getContent(){
        return this;
    }

    @Override
    synchronized public void setContent(Map<Integer,IValue> map) {
        super.clear();
        for(Integer key : map.keySet()) {
            this.put(key, map.get(key));
        }
        free.set(super.size());
    }

    @Override
    public String toString(){
        StringBuilder heapString = new StringBuilder();
        for (HashMap.Entry<Integer , IValue> element: super.entrySet()){
            Integer key = element.getKey();
            IValue value = element.getValue();
            heapString.append(key.toString()).append("->").append(value.toString()).append("\n");
        }
        return heapString.toString();
    }
}
