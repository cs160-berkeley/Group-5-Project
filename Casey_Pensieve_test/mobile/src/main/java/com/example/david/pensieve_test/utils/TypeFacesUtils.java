package com.example.david.pensieve_test.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;

import java.util.Hashtable;

// TODO: Auto-generated Javadoc

/**
 * The Class TypeFacesUtils.
 */
public class TypeFacesUtils {

    /**
     * The Constant cache.
     */
    private static final Hashtable<String, Typeface> typefaceCache = new Hashtable<String, Typeface>();
    private static float density = 1;

    static {
        density = Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * Gets the type face.
     *
     * @param context the context
     * @param assetPath the asset path
     * @return the type face
     */
    public static Typeface getTypeface(Context context, String assetPath) {
        synchronized (typefaceCache) {
            if (!typefaceCache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), assetPath);
                    typefaceCache.put(assetPath, t);
                } catch (Exception e) {
                    return null;
                }
            }
            return typefaceCache.get(assetPath);
        }
    }

    public static int dpToPx(final int dp) {
        return (int) (dp * density);
    }
}
