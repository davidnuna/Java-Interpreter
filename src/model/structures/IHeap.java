package model.structures;

import model.values.IValue;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IHeap<K, V> extends Map<K, V> {
    int alloc(V value);
    void setContent(Map<Integer, IValue> map);
    Map<K, V> getContent();
    V remove(Object key);
    V replace(K Key, V value);
    V get(Object key);
    Set<K> keySet();
    boolean containsKey(Object key);
    Collection<V> values();
    String toString();
}