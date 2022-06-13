package com.hisu.hisushop.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hisu.hisushop.R;
import com.hisu.hisushop.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding mRegisterBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);

        return mRegisterBinding.getRoot();
    }
}