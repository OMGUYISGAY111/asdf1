import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> {

    private T[] item;
    private int size;
    private int front_index;
    private int back_index;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        front_index = 0;
        back_index = 0;
        size = 0;
    }

    private void resize(int capacity) {
        T[] newList = (T[]) new Object[capacity];
        for (int i = front_index;i < back_index;i++) {
            newList[Math.floorMod(i,newList.length)]=item[Math.floorMod(i,item.length)];
        }
        item = newList;
    }

    public void addFirst(T x) {
        front_index -= 1;
        if (size == item.length) {
            resize(item.length * 2);
        }
        item[Math.floorMod(front_index,item.length)] = x;
        size += 1;
    }

    public void addLast(T x) {
        if (size == item.length) {
            resize(item.length * 2);
        }
        item[back_index] = x;
        back_index += 1;
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
        if (isEmpty()) {
            return;
        }
        List<T> returnList = new ArrayList<>();
        for (int i = front_index;i < back_index;i++) {
            System.out.print(item[Math.floorMod(i,item.length)] + " ");
            //returnList.add(item[Math.floorMod(i,item.length)]);
        }
        return;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (item.length >= size * 4 && item.length/2 >= 8) {
            resize(item.length/2);
            back_index -= (front_index - (front_index % item.length));
            front_index = front_index % item.length;
        }
        T temp;
        temp = item[Math.floorMod(front_index,item.length)];
        item[Math.floorMod(front_index,item.length)] = null;
        size -= 1;
        //uh
        front_index += 1;

        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (item.length >= size * 4 && item.length/2 >= 8) {
            resize(item.length/2);
            back_index -= (front_index - (front_index % item.length));
            front_index = front_index % item.length;
        }
        T temp;
        temp = item[Math.floorMod(back_index-1,item.length)];
        item[Math.floorMod(back_index-1,item.length)] = null;
        size -= 1;
        if (!isEmpty()) {
            back_index -= 1;
        }
        return temp;
    }

    public T get(int index) {
        if (isEmpty() || index < 0 || index > size) {
            return null;
        }
        return item[Math.floorMod(index + front_index,item.length)];
    }
}
