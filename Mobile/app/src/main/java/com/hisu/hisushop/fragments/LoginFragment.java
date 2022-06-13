package com.hisu.hisushop.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hisu.hisushop.R;
import com.hisu.hisushop.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding mLoginBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);

        return mLoginBinding.getRoot();
    }
}