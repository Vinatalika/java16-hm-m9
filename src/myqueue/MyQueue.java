package myqueue;

import java.util.Arrays;

public class MyQueue<E> {
    private Object[] elements;
    private int size;
    private int capacity;
    private int front;
    private int rear;

    public MyQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
        this.capacity = initialCapacity;
        this.elements = new Object[initialCapacity];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    public void add(E value) {
        if (size == capacity) {
            // If the queue is full, double its capacity
            int newCapacity = capacity * 2;
            elements = Arrays.copyOf(elements, newCapacity);

            if (rear < front) {
                // If the queue wraps around, move the elements to the beginning
                System.arraycopy(elements, 0, elements, capacity, rear + 1);
                rear += capacity;
            }
            capacity = newCapacity;
        }

        rear = (rear + 1) % capacity;
        elements[rear] = value;
        size++;
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
        front = 0;
        rear = -1;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return (E) elements[front];
    }

    public E poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E value = (E) elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("Size: " + queue.size());
        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());
        System.out.println("Size after poll: " + queue.size());

        queue.clear();
        System.out.println("Size after clear: " + queue.size());
    }
}
