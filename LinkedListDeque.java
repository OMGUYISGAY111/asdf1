import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> {

    private class Node{
        public Node(T _item,Node _pre,Node _next) {
            item = _item;
            pre = _pre;
            next = _next;
        }
        public T item;
        public Node pre;
        public Node next;
    }

    private Node Rfinder;
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null,sentinel,sentinel);
        size = 0;
    }

    public void addFirst(T x) {
        if (sentinel.next != null) {
            sentinel.next = new Node(x,sentinel,sentinel.next);
            sentinel.next.next.pre = sentinel.next;
        } else {
            sentinel.next = new Node(x,sentinel,sentinel);
            sentinel.pre = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T x) {
        if (sentinel.next != null) {
            sentinel.pre.next = new Node(x,sentinel.pre,sentinel);
            sentinel.pre = sentinel.pre.next;
        } else {
            sentinel.pre = new Node(x,sentinel,sentinel);
            sentinel.next = sentinel.pre;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        //List<T> returnList = new ArrayList<>();
        Node outputNode = sentinel.next;
        if (size == 0) {
            return;
        }
        while (outputNode.next != sentinel) {
            System.out.print(outputNode.item + " ");
            //returnList.add(outputNode.item);
            outputNode = outputNode.next;
        }
        System.out.print(outputNode.item);
        //returnList.add(outputNode.item);
        return;
    }

    public T removeFirst() {
        Node temp;
        T res;
        if (isEmpty()) {
            return null;
        } else if (size >= 2) {
            temp = sentinel.next;
            sentinel.next.next.pre = sentinel;
            sentinel.next = sentinel.next.next;
            res = temp.item;
            temp.item = null;
        } else {
            temp = sentinel.next;
            res = temp.item;
            sentinel.next = sentinel;
            sentinel.pre = sentinel;
            temp.item = null;
        }
        size -= 1;
        return res;
    }

    public T removeLast() {
        Node temp;
        T res;
        if (isEmpty()) {
            return null;
        } else if (size >= 2) {
            temp = sentinel.pre;
            res = temp.item;
            sentinel.pre.pre.next = sentinel;
            sentinel.pre = sentinel.pre.pre;
            temp.item = null;
        } else {
            temp = sentinel.pre;
            res = temp.item;
            sentinel.pre = sentinel;
            sentinel.next = sentinel;
            temp.item = null;
        }
        size -= 1;
        return res;
    }

    public T get(int index) {
        Node outputNode = sentinel.next;
        int count = 0;
        if (size == 0 || index >= size || index < 0) {
            return null;
        }

        while(count != index) {
            outputNode = outputNode.next;
            count++;
        }
        return outputNode.item;
    }

    public T getRecursive(int index) {
        T res;
        if (size == 0 || index >= size || index < 0) {
            return null;
        }
        if (Rfinder == null) {
            Rfinder = sentinel.next;
        }
        if (index == 0) {
            res = Rfinder.item;
            Rfinder = null;
            return res;
        } else {
            Rfinder = Rfinder.next;
            return getRecursive(index - 1);
        }
    }
}
