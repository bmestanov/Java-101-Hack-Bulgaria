package week05;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K extends Comparable<K>, V> {
    private static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private int size;
    private List<Entry<K, V>> entrySet;

    public HashMap(int initialCapacity) {
        this.size = initialCapacity;
        this.entrySet = new ArrayList<>(size);
    }

    public void put(K key, V value) {
        int index = key.hashCode() % size;
        int iter = index;
        // Doing linear search
        while (entrySet.get(iter) != null) {
            iter = ++iter % size;
            if (iter == index) {
                throw new IllegalStateException("The map is full!");
            }
        }
        entrySet.add(index, new Entry<K, V>(key, value));
    }

    public V get(K key) {
        int index = key.hashCode() % entrySet.size();
        int iter = index;
        while (!entrySet.get(iter).getKey().equals(key)) {
            iter = ++iter % size;
            if (iter == index) {
                return null;
            }
        }

        return entrySet.get(iter).getValue();
    }

    public void remove(K key) {
        int index = key.hashCode() % entrySet.size();
        int iter = index;
        while (!entrySet.get(iter).getKey().equals(key)) {
            iter = ++iter % size;
            if (iter == index) {
                return; // No item found
            }
        }

        entrySet.remove(iter);
    }

    public boolean containsKey(K key) {
        int index = key.hashCode() % entrySet.size();
        int iter = index;
        while (!entrySet.get(iter).getKey().equals(key)) {
            iter = ++iter % size;
            if (iter == index) {
                return false; // No item found
            }
        }

        return true;
    }

    public List<Entry<K, V>> getEntrySet() {
        return entrySet;
    }
}
