package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int res=0;
        for(List<V> list : map.values()){
            res += list.size();
        }
        return res;
    }

    @Override
    public V put(K key, V value) {
        if(!map.containsKey(key)) {
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
            return null;
        }
        if(map.get(key).size() < repeatCount){
            List<V> list = map.get(key);
            list.add(value);
            map.put(key, list);
            return map.get(key).get(map.get(key).size()-2);
        }
        //else == if(map.get(key).size() == repeatCount){           - подразумевается, т.к. остальные условия проверены ранее
            List<V> list = new ArrayList<V>();
            list = map.get(key);
            list.remove(0);
            list.add(value);
            map.put(key, list);
            return map.get(key).get(map.get(key).size()-2);
        //}
    }
    //Метод remove должен удалить элемент по ключу key, если по ключу key в мапе имеются значения. Если по этому ключу
    // хранится несколько элементов - должен удаляться элемент из листа с индексом ноль. Если после удаления по ключу
    // хранится лист размером ноль элементов - удали такую пару ключ : лист.
    @Override
    public V remove(Object key) {
        if (!map.containsKey(key))
            return null;
        if (map.get(key).size() == 0) {
            return (V) map.remove(key);
        }
        //if (map.contains(key) && map.get(key).size != 0){     - подразумевается, т.к. остальные условия проверены ранее
        List<V> list = map.get(key);
        V v = list.remove(0);
        if (list.size() == 0) {
            map.remove(key);
            return v;
        }
        map.put((K) key, list);
        return v;
        //}
    }

    @Override
    public Set<K> keySet() {
        //Set<K> keySet() — должен вернуть сет всех ключей, которые есть в мапе map.
        //валидатор почему-то не принял простой return.map.keySet(); и пришлось ему объяснить
        Set<K> result = map.keySet();
        return result;
    }

    @Override
    public Collection<V> values() {
        Collection<V> result = new ArrayList<V>();
        for(List<V> list : map.values()){
            result.addAll(list);
        }
        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for(List<V> list : map.values()){
            if(list.contains(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}
