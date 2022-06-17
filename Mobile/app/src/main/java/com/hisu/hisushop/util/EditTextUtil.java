package com.hisu.hisushop.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.hisu.hisushop.R;

public class EditTextUtil {

    public static void toggleClearIconOnEditText(Context context, EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /*
                 * Clear all existed drawables before set new one
                 */
                editText.setCompoundDrawables(null, null, null, null);

                editText.setBackground(
                        ContextCompat.getDrawable(context, R.drawable.edit_text_border_rounded)
                );

                if (editable.length() > 0)
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
                else
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });

        rightDrawableAction(editText);
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void rightDrawableAction(EditText editText) {
        editText.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (motionEvent.getRawX() >= editText.getRight() - editText.getTotalPaddingRight()) {
                    editText.clearFocus();
                    editText.setText("");
                    editText.requestFocus();
                    return true;
                }
            }
            return false;
        });
    }

    public static void showError(Context context, EditText field, String errorMsg) {
        field.setError(errorMsg);
        field.setBackground(
                ContextCompat.getDrawable(context, R.drawable.edit_text_border_rounded_err)
        );
        field.requestFocus();
    }
}