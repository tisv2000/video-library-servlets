package com.github.tisv2000.video_library.util;

import java.util.ResourceBundle;

public class LocaleBundleUtils {

    private static final String BUNDLE_NAME = "translations";
    public static final String DEFAULT_LOCALE = "en_US";
    private static ResourceBundle bundle;

    static {
        setLocaleName(DEFAULT_LOCALE);
    }

    public static void setLocaleName(String localeName) {
        java.util.Locale locale = new java.util.Locale(localeName.substring(0, 2), localeName.substring(3, 5));
        bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getString(String... keys) {
        if (keys.length == 1) return bundle.getString(keys[0]);

        StringBuilder builder = new StringBuilder();
        for (String key : keys) {
            builder.append(bundle.getString(key)).append(" ");
        }
        return builder.toString();
    }
}
