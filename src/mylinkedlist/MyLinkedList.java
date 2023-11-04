package mylinkedlist;

public class MyLinkedList<E> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        E data;
        Node prev;
        Node next;

        Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void add(E value) {
        Node newNode = new Node(value, last, null);
        if (last != null) {
            last.next = newNode;
        }
        last = newNode;
        if (first == null) {
            first = newNode;
        }
        size++;
    }

    public void remove(int index) {
        Node current = getNode(index);
        if (current == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node prevNode = current.prev;
        Node nextNode = current.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            first = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            last = prevNode;
        }

        current.data = null;
        size--;
    }

    public void clear() {
        while (first != null) {
            Node next = first.next;
            first.data = null;
            first.prev = null;
            first.next = null;
            first = next;
        }
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        Node node = getNode(index);
        if (node == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return node.data;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    public static void main(String[] args) {
        MyLinkedList<String> ll = new MyLinkedList<>();
        ll.add("a1");
        ll.add("a2");
        ll.add("a3");
        ll.add("a4");
        ll.add("a5");
        ll.add("a6");
        ll.add("a7");
        ll.add("a8");
        ll.add("a9");
        ll.add("a10");
        ll.add("a11");
        ll.add("a12");

        System.out.println("ll.get(10) = " + ll.get(10));

        ll.remove(5);
        System.out.println("MyLinkedList after remove index 5:");
        for (int i = 0; i < ll.size(); i++) {
            System.out.println("ll.get(" + i + ") = " + ll.get(i));
        }
    }
}
