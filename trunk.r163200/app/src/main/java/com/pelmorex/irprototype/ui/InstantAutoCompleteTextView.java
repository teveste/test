package com.pelmorex.irprototype.ui;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.pelmorex.irprototype.R;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class InstantAutoCompleteTextView extends AutoCompleteTextView {
    private Drawable textFieldDeleteCross;

    public InstantAutoCompleteTextView(Context context) { super(context); initialize(context); }
    public InstantAutoCompleteTextView(Context context, AttributeSet arg1) { super(context, arg1); initialize(context); }
    public InstantAutoCompleteTextView(Context context, AttributeSet arg1, int arg2) { super(context, arg1, arg2); initialize(context); }

    public boolean isTextEmpty() { return getText().toString().equals(""); }

    public void clearText() { setText(""); }

    public void setDropDownVisibility(boolean isDropDownVisible) {
        if ( isDropDownVisible && !isPopupShowing() ) { showDropDown(); }
        else if ( !isDropDownVisible && isPopupShowing() ) { dismissDropDown(); }
    }

    @Override
    public boolean enoughToFilter() { return true; }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if ( focused && getAdapter() != null ) { performFiltering(getText(), 0); showDropDown(); }
    }

    private void initialize(Context context) {
        textFieldDeleteCross = ContextCompat.getDrawable(context, R.drawable.android_ic_clear);
        textFieldDeleteCross.setBounds(0, 0,
                textFieldDeleteCross.getIntrinsicWidth() / 2,
                textFieldDeleteCross.getIntrinsicHeight() / 2);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setRightDrawableVisibility(!isTextEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ( getCompoundDrawables()[2] != null
                        && event.getAction() != MotionEvent.ACTION_UP
                        && event.getX() > getWidth() - getPaddingRight() - textFieldDeleteCross.getIntrinsicWidth() ) {
                    clearText();
                    return true;
                }
                return false;
            }
        });
    }

    private void setRightDrawableVisibility(boolean isRightDrawableVisible) {
        Drawable[] cd = getCompoundDrawables();
        setCompoundDrawables(cd[0], cd[1], isRightDrawableVisible ? textFieldDeleteCross : null, cd[3]);
    }
}
