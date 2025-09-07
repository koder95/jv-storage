package core.basesyntax.impl;

import core.basesyntax.Storage;

import java.util.Arrays;
import java.util.Objects;

public class StorageImpl<K, V> implements Storage<K, V> {
    private Object[] keys = new Object[0];
    private Object[] values = new Object[0];

    @Override
    public void put(K key, V value) {
        int index = indexOf(key);
        if (index >= 0) {
            values[index] = value;
            return;
        }
        index = keys.length;
        keys = Arrays.copyOf(keys, keys.length + 1);
        values = Arrays.copyOf(values, values.length + 1);
        keys[index] = key;
        values[index] = value;
    }

    private int indexOf(K key) {
        for (int i = 0; i < keys.length; i++) {
            if (Objects.equals(keys[i], key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        return index < 0 ? null : (V) values[index];
    }

    @Override
    public int size() {
        return keys.length;
    }
}
