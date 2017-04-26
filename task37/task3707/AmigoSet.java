package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

/*Твое собственное множество AmigoSet реализует интерфейс Serializable. Однако, не сериализуется правильно.
1. Реализуй свою логику сериализации и десериализации.
Вспоминай, какие именно приватные методы нужно добавить, чтоб сериализация пошла по твоему сценарию.
Для сериализации:
* сериализуй сет
* сериализуй capacity и loadFactor у объекта map, они понадобятся для десериализации.
Т.к. эти данные ограничены пакетом, то воспользуйся утилитным классом HashMapReflectionHelper, чтобы достать их.
Для десериализации:
* вычитай все данные
* создай мапу используя конструктор с capacity и loadFactor
2. Помнишь, что такое transient?

Требования:
1. В классе AmigoSet должен содержаться private метод writeObject с одним параметром типа ObjectOutputStream.
2. В классе AmigoSet должен содержаться private метод readObject с одним параметром типа ObjectInputStream.
3. Объект сериализованный с помощью метода writeObject должен быть равен объекту десериализованному с помощью метода readObject.
4. В методе writeObject должен быть вызван метод defaultWriteObject на объекте типа ObjectOutputStream полученном в качестве параметра.
5. В методе readObject должен быть вызван метод defaultReadObject на объекте типа ObjectInputStream полученном в качестве параметра.*/

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
        oos.writeObject(new HashSet<>((Collection<Integer>) map.keySet()));
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        addAll((Collection) ois.readObject());
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
