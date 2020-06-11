package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy{

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(Long k){
        if (k == null) return 0;
        int h = k.hashCode();
        return h ^ (h >>> 16);
    }
    public int indexFor(int hash, int length){
        return hash & (length - 1);
    }
    public Entry getEntry(Long key){
        for (Entry e: table){
            for (; e != null; e = e.next) {
                if (e.getKey().compareTo(key) == 0) {
                    return e;
                }
            }
        }
        return null;
    }

    public void resize(int newCapacity){
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) DEFAULT_LOAD_FACTOR * newCapacity;
    }

    public void transfer(Entry[] newTable){
        System.arraycopy(table, 0, newTable, 0, size);
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        if (!containsKey(key)){
            createEntry(hash, key, value, bucketIndex);
        } else {
            getEntry(key).value = value;
        }
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry newEntry = new Entry(hash, key, value, null);

        Entry current = table[bucketIndex];
        if (current == null){
            table[bucketIndex] = newEntry;
        } else {
            Entry next;
            while ((next = current.next) != null){
                current = next;
            }
            current.next = newEntry;
        }
        ++size;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (table != null && size > 0) {
            for (Entry e : table) {
                for (; e != null; e = e.next) {
                    if (e.getValue().equals(value))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        addEntry(hash, key, value, indexFor(hash, table.length));
    }

    @Override
    public Long getKey(String value) {
        if (table != null && size > 0) {
            for (Entry e : table) {
                for (; e != null; e = e.next) {
                    if (e.getValue().equals(value))
                        return e.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry e = getEntry(key);
        return e == null ? null : e.getValue();
    }
}
