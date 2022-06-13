package com.hisu.hisushop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hisu.hisushop.databinding.FragmentSplashScreenBinding;

public class SplashScreenFragment extends Fragment {

    private FragmentSplashScreenBinding mSplashScreenBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mSplashScreenBinding = FragmentSplashScreenBinding.inflate(
                inflater, container, false
        );

        return mSplashScreenBinding.getRoot();
    }
}