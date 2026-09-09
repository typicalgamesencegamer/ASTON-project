package com.aston.project.app.utils.customcollections;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator(0);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        T[] result = array.length >= size
                ? array
                : (T[]) Array.newInstance(array.getClass().getComponentType(), size);

        for (int index = 0; index < size; index++) {
            result[index] = (T) elements[index];
        }
        if (result.length > size) {
            result[size] = null;
        }
        return result;
    }

    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object element) {
        int index = indexOf(element);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkPositionIndex(index);
        Object[] newElements = collection.toArray();
        if (newElements.length == 0) {
            return false;
        }

        ensureCapacity(size + newElements.length);
        System.arraycopy(elements, index, elements, index + newElements.length, size - index);
        System.arraycopy(newElements, 0, elements, index, newElements.length);
        size += newElements.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean changed = false;
        for (int index = size - 1; index >= 0; index--) {
            if (collection.contains(elements[index])) {
                remove(index);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean changed = false;
        for (int index = size - 1; index >= 0; index--) {
            if (!collection.contains(elements[index])) {
                remove(index);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        for (int index = 0; index < size; index++) {
            elements[index] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return elementAt(index);
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        E previous = elementAt(index);
        elements[index] = element;
        return previous;
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
        return removed;
    }

    @Override
    public int indexOf(Object element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(elements[index], element)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        for (int index = size - 1; index >= 0; index--) {
            if (Objects.equals(elements[index], element)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ArrayListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ArrayListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size) {
            throw new IndexOutOfBoundsException("Некорректный диапазон списка");
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("Начальный индекс больше конечного");
        }

        CustomArrayList<E> result = new CustomArrayList<>();
        for (int index = fromIndex; index < toIndex; index++) {
            result.add(elementAt(index));
        }
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof List<?> other) || size != other.size()) {
            return false;
        }

        Iterator<?> iterator = other.iterator();
        for (int index = 0; index < size; index++) {
            if (!Objects.equals(elements[index], iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int index = 0; index < size; index++) {
            result = 31 * result + (elements[index] == null ? 0 : elements[index].hashCode());
        }
        return result;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= elements.length) {
            return;
        }

        int newCapacity = elements.length * 2;
        while (newCapacity < requiredCapacity) {
            newCapacity *= 2;
        }
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

    private class ArrayListIterator implements ListIterator<E> {
        private int cursor;
        private int lastReturnedIndex = -1;

        private ArrayListIterator(int startIndex) {
            cursor = startIndex;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = cursor;
            return elementAt(cursor++);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = --cursor;
            return elementAt(cursor);
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastReturnedIndex < 0) {
                throw new IllegalStateException();
            }
            CustomArrayList.this.remove(lastReturnedIndex);
            if (lastReturnedIndex < cursor) {
                cursor--;
            }
            lastReturnedIndex = -1;
        }

        @Override
        public void set(E element) {
            if (lastReturnedIndex < 0) {
                throw new IllegalStateException();
            }
            CustomArrayList.this.set(lastReturnedIndex, element);
        }

        @Override
        public void add(E element) {
            CustomArrayList.this.add(cursor, element);
            cursor++;
            lastReturnedIndex = -1;
        }
    }
}
