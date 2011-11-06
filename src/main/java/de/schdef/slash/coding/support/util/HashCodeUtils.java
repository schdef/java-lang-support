package de.schdef.slash.coding.support.util;

public final class HashCodeUtils {

    private static final int  ZERO = 0;

    private static final int SEED = 31;

    public static int hashCode(Object value) {
        return value != null ? value.hashCode() : ZERO;
    }

    public static int hashCode(Object value, int baseHashCode) {
        int valueHashCode = hashCode(value);
        return SEED * baseHashCode + valueHashCode;
    }

    private HashCodeUtils() {
    }

}
