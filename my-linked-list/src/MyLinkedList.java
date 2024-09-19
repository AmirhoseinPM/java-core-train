

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList <T> implements Collection<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    private class Node<T> {
        private T value;
        private MyLinkedList.Node next;

        public Node(T value) {
            this.value = value;
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        Node current = first;
        while (current != null) {
            if (current.value == o) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<T>(this);
    }
    private class MyLinkedListIterator<T> implements Iterator<T> {
        public Node<T> current;

        public MyLinkedListIterator(MyLinkedList linkedList) {
            current = linkedList.first;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public T next() {
            Node<T> c = current;
            current = current.next;
            return c.value;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        T[] array = (T[]) objects;
        Node<T> current = first;
        int index = 0;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    public void addLast(T value){
        var newNode = new Node(value);
        if (isEmpty())
            first = last = newNode;
        else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public void addFirst(T value) {
        var newNode = new Node(value);
        if (isEmpty())
            last = first = newNode;
        else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    @Override
    public boolean remove(Object o) {
        T t = (T) o;
        Node<T> current = first;
        while (current != null)
            if (current.value == t) {
                Node<T> previous = getPrevious(current);
                previous.next = current.next;
                current.next = null; // allow garbage collector to del current node
                current.value = null;
                size--;
                return true;
            }
            else {
                current = current.next;
            }
        return false;
    }

    public void removeFirst() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first == last)
            first = null;
        else {
            Node second = first.next;
            first.next = null;  // garbage collector can delete first node
            first.value = null;  // garbage collector can delete first node
            first = second;
        }
        size--;
    }

    public void removeLast() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException();
        if (first == last)
            first = last = null;
        else {
            Node previous = getPrevious(last);
            last = previous;
            last.next = null;
        }
        size--;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        Node<T>  current = first;
        while(current != null) {
            str.append(current.value.toString() + " -> ");
            current = current.next;
        }
        str.append("null");
        return str.toString();
    }

    private Node getPrevious(Node node) {
        Node current = first;
        while (current != null){
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }

    @Override
    public void clear() {
        for(int i=0; i < size; i++)
            removeLast();
    }

    public T get(int index) {
        if (index >= size)
            return null;
        Node<T> current = first;
        int i = 0;
        while(i < index) {
            current = current.next;
            i++;
        }
        return current.value;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var o: c)
            if (!contains(o))
                return false
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (var o: c)
            add((T) o);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removedAll;
        for (var o: c) {
            var removed = remove(0);
            if (!removed)
                    removedAll = false;
        }
        return removedAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }



}
