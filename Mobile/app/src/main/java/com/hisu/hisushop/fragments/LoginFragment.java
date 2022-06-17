package com.hisu.hisushop.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.hisu.hisushop.MainActivity;
import com.hisu.hisushop.R;
import com.hisu.hisushop.databinding.FragmentLoginBinding;
import com.hisu.hisushop.util.EditTextUtil;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding mLoginBinding;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainActivity = (MainActivity) getActivity();
        mLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);

        EditTextUtil.toggleClearIconOnEditText(mainActivity, mLoginBinding.edtUserName);
        EditTextUtil.toggleClearIconOnEditText(mainActivity, mLoginBinding.edtPwd);

        mLoginBinding.btnLogin.setOnClickListener(view -> login());
        mLoginBinding.tvSwitchToRegister.setOnClickListener(view -> switchToRegister());

        return mLoginBinding.getRoot();
    }

    private void login() {
//        ProgressDialog dialog = new ProgressDialog(getContext());

        String username = mLoginBinding.edtUserName.getText().toString().trim();
        String password = mLoginBinding.edtPwd.getText().toString().trim();

//        dialog.setMessage("We're working on it! Please wait...");
//        dialog.show();

        if (validateUserAccount(username, password)) {
//            dialog.dismiss();
            mainActivity.setFragment(new HomePageFragment());
        }
    }

    private boolean validateUserAccount(String username, String password) {

        resetEditTextBackground();

        if (TextUtils.isEmpty(username)) {
            EditTextUtil.showError(
                    mainActivity,
                    mLoginBinding.edtUserName, "Username can't be empty!"
            );
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            EditTextUtil.showError(
                    mainActivity,
                    mLoginBinding.edtUserName, "Invalid username! Please try again!"
            );
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            EditTextUtil.showError(
                    mainActivity,
                    mLoginBinding.edtPwd, "Please enter password!"
            );
            return false;
        }

        return true;
    }

    private void resetEditTextBackground() {
        mLoginBinding.edtPwd.setBackground(
                ContextCompat.getDrawable(mainActivity, R.drawable.edit_text_border_rounded)
        );

        mLoginBinding.edtUserName.setBackground(
                ContextCompat.getDrawable(mainActivity, R.drawable.edit_text_border_rounded)
        );
    }

    private void switchToRegister() {
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(mainActivity.getFragmentContainerID(), new RegisterFragment())
                .commit();
    }
}