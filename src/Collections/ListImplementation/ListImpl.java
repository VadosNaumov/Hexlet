package src.Collections;

import java.util.*;
import java.lang.reflect.Array;

public class ListImpl<T> implements List<T>  {

    private Item<T> firstInList = null;

    private Item<T> lastInList = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator(0);
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size()];
        for (int i = 0; i < size(); i++) {
            res[i] = get(i);
        }
        return res;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < this.size) {
            a = (T1[]) Array.newInstance(a.getClass().getComponentType(), this.size);
        }

        int i = 0;
        Object[] result = a;

        for(ListImpl.Item x = this.firstInList; x != null; x = x.nextItem) {
            result[i++] = x.element;
        }

        if (a.length > this.size) {
            a[this.size] = null;
        }

        return a;
    }

    @Override
    public boolean add(final T newElement) {
        Item<T> item = new Item<>(newElement, this.lastInList, null);
        if (this.size < 1) {
            this.firstInList = item;
        } else {
            item.getPrevItem().nextItem = item;
        }
        this.lastInList = item;
        size++;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        int res = -1;
        Item<T> item2 = firstInList;
        for (int i = 0; i < size(); i++) {
            if (item2.element.equals(o))
                res = i;
            item2 = item2.nextItem;
        }
        if (res != -1){
            Item<T> item = firstInList;
            for (int i = 0; i < res; i++) {
                item = item.nextItem;
            }
            if (item.prevItem == null && item.nextItem == null) {
                item.element = null;
            } else if (item.prevItem == null) {
                firstInList = firstInList.nextItem;
                item.nextItem.prevItem = item.prevItem;
            } else if (item.nextItem == null) {
                item.prevItem.nextItem = item.nextItem;
            } else {
                item.prevItem.nextItem = item.nextItem;
                item.nextItem.prevItem = item.prevItem;
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final T item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        Item<T> x = firstInList;
        for (Item<T> item = firstInList; item != null; item = x) {
            x = item.nextItem;
            item.element = null;
            item.nextItem = null;
            item.prevItem = null;
        }
        this.firstInList = this.lastInList = null;
        this.size = 0;
    }

    @Override
    public T remove(final int index) throws IndexOutOfBoundsException{
        if (index >= size() || index < 0) throw new IndexOutOfBoundsException();
        T shit = getItemByIndex(index).element;
        Item<T> item = firstInList;
        for (int i = 0; i < index; i++) {
            item = item.nextItem;
        }
        if (item.prevItem == null && item.nextItem == null) {
            item.element = null;
        } else if (item.prevItem == null) {
            firstInList = firstInList.nextItem;
            item.nextItem.prevItem = item.prevItem;
        } else if (item.nextItem == null) {
            item.prevItem.nextItem = item.nextItem;
        } else {
            item.prevItem.nextItem = item.nextItem;
            item.nextItem.prevItem = item.prevItem;
        }
        size--;
        return shit;
    }

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object o) {
        Item<T> item = firstInList;
        for (int i = 0; i < size(); i++) {
            if (item.element != null) {
                if (item.element.equals(o))
                    return i;
            } else {
                if (o == null)
                    return i;
            }
            item = item.nextItem;
        }
        return -1;
    }

    @Override
    public void add(final int index, final T element) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Item<T> itemAdd = new Item<>(element, null, null);
        if (index == 0) {
            Item<T> item2 = getItemByIndex(index);
            getItemByIndex(index).prevItem = itemAdd;
            firstInList = itemAdd;
            itemAdd.nextItem = item2;
        } else if (index < (size() - 1)){
            Item<T> item2 = getItemByIndex(index);
            getItemByIndex(index).getNextItem().prevItem = item2;
            getItemByIndex(index - 1).nextItem = itemAdd;
            item2.prevItem = itemAdd;
            itemAdd.prevItem = getItemByIndex(index - 1);
            itemAdd.nextItem = item2;
        } else if (index == size()){
            lastInList = itemAdd;
            getItemByIndex(index - 1).nextItem = itemAdd;
            itemAdd.prevItem = getItemByIndex(index - 1);
        } else if (index == (size() - 1)){
            Item<T> item2 = lastInList = getItemByIndex(index);
            getItemByIndex(index - 1).nextItem = itemAdd;
            item2.prevItem = itemAdd;
            itemAdd.prevItem = getItemByIndex(index - 1);
            itemAdd.nextItem = item2;
        }
        size++;
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        if (index < 0) throw new IndexOutOfBoundsException();
        T shit = getItemByIndex(index).element;
        getItemByIndex(index).element = element;
        return shit;
    }

    @Override
    public T get(final int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        ListIterator it = listIterator();
        for (int i = 0; i < index; i++) {
            it.next();
        }
        return (T) it.next();
    }

    private Item<T> getItemByIndex(final int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Item<T> item = firstInList;
        for (int i = 0; i < index; i++) {
            item = item.nextItem;
        }
        return item;
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> currentItemInIterator;

        private Item<T> lastReturnedItemFromIterator;

        private int index;

        ElementsIterator(final int index) {
            this.index = index;
            this.currentItemInIterator = getItemByIndex(index);
        }

        @Override
        public boolean hasNext() {
            return ListImpl.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            this.lastReturnedItemFromIterator = this.currentItemInIterator;
            index++;
            this.currentItemInIterator = this.currentItemInIterator.getNextItem();
            return this.lastReturnedItemFromIterator.element;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            index--;
            this.lastReturnedItemFromIterator = this.currentItemInIterator = this.currentItemInIterator == null ?
                    ListImpl.this.lastInList : this.currentItemInIterator.prevItem;
            return this.lastReturnedItemFromIterator.element;
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            if (index == 0) throw new IllegalStateException();
            this.lastReturnedItemFromIterator.element = element;
        }

        @Override
        public int previousIndex(){
            return index - 1;
        }
        @Override
        public int nextIndex() {
            return index;
        }


        @Override
        public void remove() {
            if (this.lastReturnedItemFromIterator == null)
                throw new IllegalStateException();
            ListImpl.this.remove(--index);
            lastReturnedItemFromIterator = null;
        }
    }

    private static class Item<T> {

        private T element;

        private Item<T> nextItem;

        private Item<T> prevItem;

        Item(final T element, final Item<T> prevItem, final Item<T> nextItem) {
            this.element = element;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }

        public Item<T> getNextItem() {
            return nextItem;
        }

        public Item<T> getPrevItem() {
            return prevItem;
        }
    }
}
