package apap.tugaskelompok.sibusiness.rest;

public class Pair<T1, T2> {
    public final T1 key;
    public final T2 value;

    public Pair(T1 first, T2 second) {
        this.key = first;
        this.value = second;
    }

    public T1 getKey() {
        return key;
    }

    public T2 getValue() {
        return value;
    }
}
