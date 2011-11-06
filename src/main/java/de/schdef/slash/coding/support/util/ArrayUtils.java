package de.schdef.slash.coding.support.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class ArrayUtils {

    public static <T> T[] emptyIfNull(T[] candidate, T[] empty) {
        return candidate != null ? candidate : empty;
    }

    public static <T> T[] toArray(Iterator<?> elements, Class<T> arrayType) {
        T[] elementsAsArray = null;
        if (elements != null) {
            List<?> elementsAsList = asList(elements);
            elementsAsArray = toArray(elementsAsList, arrayType);
        }
        return elementsAsArray;
    }

    public static String[] toStringArray(Collection<?> elements) {
        return toArray(elements, String.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Collection<?> elements, Class<T> arrayType) {
        T[] elementsAsArray = null;
        if (elements != null) {
            elementsAsArray = (T[]) Array.newInstance(arrayType, elements.size());
            elementsAsArray = elements.toArray(elementsAsArray);
        }
        return elementsAsArray;
    }

    public static <T> List<T> asList(Enumeration<T> elements) {
        return elements != null ? asList(new EnumerationIterator<T>(elements)) : null;
    }

    public static <T> List<T> asList(Iterator<T> elements) {
        List<T> elementsAsList = null;
        if (elements != null) {
            elementsAsList = new ArrayList<T>();
            while (elements.hasNext()) {
                elementsAsList.add(elements.next());
            }
        }
        return elementsAsList;
    }

    public static <T> List<T> asList(T... elements) {
        List<T> elementsAsList = null;
        if (elements != null) {
            elementsAsList = new ArrayList<T>(elements.length);
            for (T element : elements) {
                elementsAsList.add(element);
            }
        }
        return elementsAsList;
    }

    public static <T> Set<T> asSet(T... elements) {
        Set<T> elementsAsSet = null;
        if (elements != null) {
            elementsAsSet = new HashSet<T>(elements.length);
            for (T element : elements) {
                elementsAsSet.add(element);
            }
        }
        return elementsAsSet;
    }

    private ArrayUtils() {
    }

}
