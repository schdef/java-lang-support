package de.schdef.slash.coding.support.util;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator<E>
    implements Iterator<E> {

    private final Enumeration<E> target;

    public EnumerationIterator(Enumeration<E> target) {
        Assert.isNotNull(target, "invalid target: null");
        this.target = target;
    }

    @Override
    public boolean hasNext() {
        return target.hasMoreElements();
    }

    @Override
    public E next() {
        return target.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

}
