package io.mindspice.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Objects;


public class Pair<U, V> {
    private U first;
    private V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <U, V> Pair<U, V> of(U obj1, V obj2) {
        return new Pair<>(obj1, obj2);
    }

    @JsonCreator
    private Pair() { }

    ;

    @JsonGetter("first")
    public U first() {
        return first;
    }

    @JsonGetter("second")
    public V second() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first + ","
                + "second=" + second +
                "}";
    }
}