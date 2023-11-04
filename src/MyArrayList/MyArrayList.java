package MyArrayList;

public class MyArrayList<E> {
    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[10];
        size = 0;
    }

    public void add(E value) {
        if (size == data.length) {
            int newSize = (data.length * 3) / 2 + 1;
            Object[] newData = new Object[newSize];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
    }

    public void clear() {
        data = new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return (E) data[index];
    }
    public static void main(String[] args) {
        MyArrayList<String> arrayList = new MyArrayList();
        arrayList.add("a1");
        arrayList.add("a2");
        arrayList.add("a3");
        arrayList.add("a4");
        arrayList.add("a5");


        System.out.println("arrayList.get(4) = " + arrayList.get(4));
        arrayList.remove(2);
        System.out.println("Myarraylist after remove index 2:");

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("arrayList.get(" + i + ") = " + arrayList.get(i));
        }
    }

}
