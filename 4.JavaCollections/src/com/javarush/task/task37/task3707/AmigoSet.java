package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {

    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection){
        int capacity = (int) Math.ceil(collection.size()/.75f);
        map = new HashMap<>(Math.max(16, capacity));
        collection.forEach(el->map.put(el, PRESENT));
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e, PRESENT);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return PRESENT == map.remove(o);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Object clone() {
        try{
            AmigoSet<E> set = new AmigoSet<>();
            set.map = (HashMap<E, Object>) map.clone();
            return set;
        } catch (Throwable t){
            throw new InternalError(t);
        }
    }

    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        output.writeInt(capacity);
        output.writeFloat(loadFactor);
        output.writeInt(size());
        for (E e : map.keySet()) {
            output.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        int capacity = input.readInt();
        float loadFactor = input.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        int size = input.readInt();
        for(int i = 0; i < size; i++){
            map.put((E) input.readObject(), PRESENT);
        }
    }
}
