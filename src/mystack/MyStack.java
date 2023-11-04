package mystack;

import java.util.Arrays;

public class MyStack<E> {
    private Object[] elements;
    private int size;
    private int capacity;

    public MyStack(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
        this.capacity = initialCapacity;
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public void push(E value) {
        if (size == capacity) {
            int newCapacity = capacity * 2;
            Object[] newElements = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
            capacity = newCapacity;
        }

        elements[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return (E) elements[0];
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E value = (E) elements[size - 1];
        elements[size - 1] = null;
        size--;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Size: " + stack.size());
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Size after pop: " + stack.size());

        stack.remove(0);
        System.out.println("Size after remove: " + stack.size());

        stack.clear();
        System.out.println("Size after clear: " + stack.size());
    }
}
