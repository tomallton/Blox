package com.tomallton.blox;

import java.util.Objects;

/**
 * Stores a key value
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class Entry<K, V> {
    private final K key;
    private final V value;

    private Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key.toString() + "=" + value.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Entry)) {
            return false;
        }
        Entry<?, ?> entry = (Entry<?, ?>) other;

        return key.equals(entry.getKey()) && value.equals(entry.getValue());
    }

    public static <L, R> Entry<L, R> of(L left, R right) {
        return new Entry<>(left, right);
    }
}