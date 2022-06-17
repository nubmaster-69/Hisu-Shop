package com.hisu.hisushop.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.hisu.hisushop.MainActivity;
import com.hisu.hisushop.R;
import com.hisu.hisushop.databinding.FragmentRegisterBinding;
import com.hisu.hisushop.util.EditTextUtil;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding mRegisterBinding;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainActivity = (MainActivity) getActivity();
        mRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);

        EditTextUtil.toggleClearIconOnEditText(mainActivity, mRegisterBinding.edtRegisUsername);
        EditTextUtil.toggleClearIconOnEditText(mainActivity, mRegisterBinding.edtRegisPwd);
        EditTextUtil.toggleClearIconOnEditText(mainActivity, mRegisterBinding.edtRegisConfirmPwd);

        mRegisterBinding.tvSwitchToLogin.setOnClickListener(view -> switchToLogin());
        mRegisterBinding.btnRegister.setOnClickListener(view -> register());

        return mRegisterBinding.getRoot();
    }

    private void switchToLogin() {
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                .replace(mainActivity.getFragmentContainerID(), new LoginFragment())
                .commit();
    }

    private void register() {
//        ProgressDialog dialog = new ProgressDialog(getContext());

        String username = mRegisterBinding.edtRegisUsername.getText().toString().trim();
        String password = mRegisterBinding.edtRegisPwd.getText().toString().trim();
        String confirmPwd = mRegisterBinding.edtRegisPwd.getText().toString().trim();

//        dialog.setMessage("We're working on it! Please wait...");
//        dialog.show();

        if (validateUserAccount(username, password, confirmPwd)) {
//            dialog.dismiss();
            mainActivity.setFragment(new HomePageFragment());
        }
    }

    private boolean validateUserAccount(String username, String password, String confirmPwd) {

        resetEditTextBackground();

        if (TextUtils.isEmpty(username)) {
            EditTextUtil.showError(
                    mainActivity,
                    mRegisterBinding.edtRegisUsername, "Username can't be empty!"
            );
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            EditTextUtil.showError(
                    mainActivity,
                    mRegisterBinding.edtRegisUsername, "Invalid username! Please try again!"
            );
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            EditTextUtil.showError(
                    mainActivity,
                    mRegisterBinding.edtRegisPwd, "Please enter password!"
            );
            return false;
        }

        if (TextUtils.isEmpty(confirmPwd)) {
            EditTextUtil.showError(
                    mainActivity,
                    mRegisterBinding.edtRegisConfirmPwd, "Please confirm your password!"
            );
            return false;
        }

        if (!confirmPwd.equalsIgnoreCase(password)) {
            EditTextUtil.showError(
                    mainActivity,
                    mRegisterBinding.edtRegisConfirmPwd, "Password doesn't match!"
            );
            return false;
        }

        return true;
    }

    private void resetEditTextBackground() {
        mRegisterBinding.edtRegisUsername.setBackground(
                ContextCompat.getDrawable(mainActivity, R.drawable.edit_text_border_rounded)
        );

        mRegisterBinding.edtRegisPwd.setBackground(
                ContextCompat.getDrawable(mainActivity, R.drawable.edit_text_border_rounded)
        );

        mRegisterBinding.edtRegisConfirmPwd.setBackground(
                ContextCompat.getDrawable(mainActivity, R.drawable.edit_text_border_rounded)
        );
    }
}