package com.aston.project.app.utils.customcollections;

import java.util.AbstractList;

public class CustomArrayList<E> extends AbstractList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return elementAt(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        E previous = elementAt(index);
        elements[index] = element;
        return previous;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        ensureCapacity(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        E removed = elementAt(index);
        int elementsAfter = size - index - 1;

        if (elementsAfter > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsAfter);
        }

        elements[--size] = null;
        modCount++;
        return removed;
    }

    @Override
    public void clear() {
        for (int index = 0; index < size; index++) {
            elements[index] = null;
        }
        size = 0;
        modCount++;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= elements.length) {
            return;
        }

        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне границ списка: " + index);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне границ списка: " + index);
        }
    }

    @SuppressWarnings("unchecked")
    private E elementAt(int index) {
        return (E) elements[index];
    }
}
