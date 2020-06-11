package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize = 0;

    public FileStorageStrategy() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public int hash(Long k) {
        if (k == null) return 0;
        int h = k.hashCode();
        return h ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        Entry e = table[index].getEntry();
        for (; e != null; e = e.next) {
            if (e.getKey().compareTo(key) == 0) {
                return e;
            }
        }
        return null;
    }

    public void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];

        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new FileBucket();
        }

        transfer(newTable);
        table = newTable;
        table = null;
        bucketSizeLimit = newCapacity;
    }

    public void transfer(FileBucket[] newTable) {
        int newSize = newTable.length;
        for (FileBucket fileBucket : table) {
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                int newIndex = indexFor(entry.hash, newSize);
                newTable[newIndex].putEntry(entry);
                entry = entry.next;
            }
            fileBucket.remove();
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex].getEntry() == null){
            createEntry(hash, key, value, bucketIndex);
            return;
        }
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));

        maxBucketSize = Math.max(
                maxBucketSize,
                table[bucketIndex].getFileSize()
        );

        if (maxBucketSize > bucketSizeLimit) {
            resize(2 * DEFAULT_INITIAL_CAPACITY);
        }
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = new Entry(hash, key, value, null);
        table[bucketIndex].putEntry(entry);
        ++size;
    }

    @Override
    public boolean containsKey(Long key) {
        Entry entry = getEntry(key);
        return entry != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (table != null && size > 0) {
            for (FileBucket bucket : table) {
                Entry e = bucket.getEntry();
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
            for (FileBucket bucket : table) {
                Entry e = bucket.getEntry();
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
