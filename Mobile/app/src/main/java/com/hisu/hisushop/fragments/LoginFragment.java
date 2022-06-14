package com.hisu.hisushop.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hisu.hisushop.MainActivity;
import com.hisu.hisushop.R;
import com.hisu.hisushop.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding mLoginBinding;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainActivity = (MainActivity) getActivity();
        mLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);

        toggleClearIconOnEditText(mLoginBinding.edtUserName);
        toggleClearIconOnEditText(mLoginBinding.edtPwd);

        mLoginBinding.btnLogin.setOnClickListener(view -> login());

        return mLoginBinding.getRoot();
    }

    private void toggleClearIconOnEditText(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0)
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
                else
                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });

        rightDrawableAction(editText);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void rightDrawableAction(EditText editText) {
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

    private boolean validateUserAccount(String username, String password) {
        return true;
    }

    private void login() {
        ProgressDialog dialog = new ProgressDialog(getContext());

        String username = mLoginBinding.edtUserName.getText().toString().trim();
        String password = mLoginBinding.edtPwd.getText().toString().trim();

        dialog.setMessage("We're working on it! Please wait...");
        dialog.show();

        if(validateUserAccount(username, password)) {
            dialog.dismiss();
            mainActivity.setFragment(new HomePageFragment());
        }
    }
}