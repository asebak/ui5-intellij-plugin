package com.atsebak.ui5.locale;

import com.intellij.BundleBase;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ResourceBundle;

import static com.intellij.reference.SoftReference.dereference;

public final class UI5Bundle {

    @NonNls
    private static final String BUNDLE = "com.atsebak.ui5.locale.UI5Bundle";
    private static Reference<ResourceBundle> bundleReference;

    /**
     * Private Constructor
     */
    private UI5Bundle() {
    }

    /**
     * @param key
     * @param params
     * @return
     */
    public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
        return BundleBase.message(getBundle(), key, params);
    }

    /**
     * @return
     */
    private static ResourceBundle getBundle() {
        ResourceBundle bundle = dereference(bundleReference);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE);
            bundleReference = new SoftReference<ResourceBundle>(bundle);
        }
        return bundle;
    }

    /**
     * @param key The Key
     * @return The Localized Text
     */
    public static String getString(@PropertyKey(resourceBundle = BUNDLE) final String key) {
        return getBundle().getString(key);
    }
}
