package de.schdef.slash.coding.support.util;

public class StringUtils {

    /**
     * String trim wrapper that accepts also null parameter
     *
     * @param str
     * @return if str is not null returns str.trim(), else returns null
     */
    public static String trim(String str) {
        if (str == null) {
            return null;
        } else {
            return str.trim();
        }
    }

    /**
     * If String length is bigger then required  - truncate
     *
     * @param str original string
     * @param length  required length
     * @return
     */
    public final static String truncateString(String str, int length) {
        if (str == null) {
            return null;
        }

        if (length < 1) {
            return str;
        }
        int strLength = str.length();
        if (strLength > length) {
            return str.substring(0, length);
        } else {
            return str;
        }
    }

    private StringUtils() {
    }
}
