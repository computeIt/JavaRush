package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable{
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = (int)Math.ceil(collection.size()/.75f);
        if(capacity<16)
            capacity = 16;
        map = new HashMap<>(capacity);
        for(E e : collection)
            map.put(e, PRESENT);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(HashMapReflectionHelper.<Integer>callHiddenMethod(map, "capacity"));
        oos.writeFloat(HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor"));
        for(E e : map.keySet())
            oos.writeObject(e);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        for(int i=0; i<size(); i++){
            E e = (E) ois.readObject();
            map.put(e, PRESENT);
        }
    }

    @Override
    public Object clone() {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        }catch (Exception e){
            throw new InternalError();
        }
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e, PRESENT);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty(){
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o){
        return map.containsKey(o);
    }

    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public boolean remove(Object o){
        return null != map.remove(o);
    }
}
