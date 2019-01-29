package com.cmcc.util;

import org.apache.commons.lang3.StringUtils;

public class
Assert {

    public static void isTrue(boolean express, String msg) {
        if (!express) {
            throw new RuntimeException(msg);
        }
    }

    public static void notTrue(boolean express, String msg) {
        if (express) {
            throw new RuntimeException(msg);
        }
    }

    public static void isNull(Object object, String msg) {
        if (object != null) {
            throw new RuntimeException(msg);
        }
    }

    public static void notNull(Object object, String msg) {
        if (object == null) {
            throw new RuntimeException(msg);
        }
    }

    public static void isEquals(Object one, Object two, String msg) {
        if (!one.equals(two)) {
            throw new RuntimeException(msg);
        }
    }

    public static void notEmpty(Object object, String msg) {
        if (object == null) {
            throw new RuntimeException(msg);
        }
        if (object instanceof String) {
            String str = (String) object;
            if (StringUtils.isEmpty(str)) throw new RuntimeException(msg);
        }
    }
}
