package de.schdef.slash.coding.support.util;

public final class Assert {

    public static void isNotNull(Object value, String message,
	    Object... messageParams) {
	if (value == null) {
	    throw new IllegalArgumentException(message);
	}
    }

    public static void isFalseArg(boolean expression, String message,
	    Object... messageParams) {
	isTrueArg(!expression, message, messageParams);
    }

    public static void isTrueArg(boolean expression, String message,
	    Object... messageParams) {
	if (!expression) {
	    throw new IllegalArgumentException(message);
	}
    }

    public static void isNullState(Object value, String message,
	    Object... messageParams) {
	if (value != null) {
	    throw new IllegalStateException(message);
	}
    }

    public static void isNotNullState(Object value, String message,
	    Object... messageParams) {
	if (value == null) {
	    throw new IllegalStateException(message);
	}
    }

    public static void isFalseState(boolean expression, String message,
	    Object... messageParams) {
	isTrueState(!expression, message, messageParams);
    }

    public static void isTrueState(boolean expression, String message,
	    Object... messageParams) {
	if (!expression) {
	    throw new IllegalStateException(formatMessage(message,
		    messageParams));
	}
    }

    public static void isEquals(Object first, Object second, String message,
	    Object... messageParams) {
	if (first == null ? second == null : first.equals(second)) {
	    throw new IllegalStateException(formatMessage(message,
		    messageParams));
	}
    }

    private Assert() {
    }

    private static String formatMessage(String message, Object... messageParams) {
	if (messageParams == null || messageParams.length == 0) {
	    return message;
	}

	return String.format(message, messageParams);
    }

}
