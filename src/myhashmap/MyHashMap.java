package myhashmap;
import java.util.Arrays;

public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node<K, V>[] table;
    private int size;

    public MyHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> current = table[index];
            Node<K, V> prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }

            prev.next = newNode;
        }

        size++;

        if ((1.0 * size) / table.length > LOAD_FACTOR) {
            resizeTable();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = new Node[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Node<K, V> current = table[i];
            while (current != null) {
                int index = Math.abs(current.key.hashCode()) % newCapacity;
                Node<K, V> next = current.next;
                current.next = newTable[index];
                newTable[index] = current;
                current = next;
            }
        }

        table = newTable;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> myMap = new MyHashMap<>();
        myMap.put("one", 1);
        myMap.put("two", 2);
        myMap.put("three", 3);
        myMap.put("four", 4);
        myMap.put("five", 5);

        System.out.println("Size: " + myMap.size());
        System.out.println("Value for key 'three': " + myMap.get("three"));

        myMap.remove("two");
        System.out.println("Size after removing 'two': " + myMap.size());

        myMap.clear();
        System.out.println("Size after clearing: " + myMap.size());
    }
}
