package week04;
/**
 * Created by Bilal on 6.11.2016 г..
 */
interface MList<T> {
    void addToFront(T element);
    void addToBack(T element);
    void add(int index, T element);
    T getFirst();
    T getLast();
    T get(int index);
    int size();
    void remove(int index);
    void append(MList app);
    T toLast(int k);
}
