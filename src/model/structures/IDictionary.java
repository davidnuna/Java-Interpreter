package model.structures;

import java.util.Map;
import java.util.Set;

public interface IDictionary<K, V> {
    V get(K key);
    void put(K key, V value);
    void remove(K key) ;
    boolean containsKey(K key);
    Map<K, V> getContent();
    Set<K> keySet();
    IDictionary<K, V> deepCopy();
    String toString();
}
