package com.pelmorex.irprototype.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public final class UiUtil {
    private UiUtil() { }

    public static void clearFocusOnOutsideTap(@NonNull Activity activity, @NonNull MotionEvent event, @NonNull Class<?> c) {
        if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
            View v = activity.getCurrentFocus();
            if ( v != null && c.isAssignableFrom(v.getClass()) ) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if ( !outRect.contains((int)event.getRawX(), (int)event.getRawY()) ) {
                    v.clearFocus();
                    ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
    }
}
