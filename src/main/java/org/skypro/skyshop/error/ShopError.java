package org.skypro.skyshop.error;

public class ShopError {
    private static int code = 404;
    private static String message;

    public ShopError(int code, String message) {
        ShopError.code = code;
        ShopError.message = message;
    }

    public static int getCode() {
        return code;
    }

    public static String getMessage() {
        return message;
    }
}
