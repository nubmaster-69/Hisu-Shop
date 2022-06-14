package com.hisu.hisushop.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hisu.hisushop.R;
import com.hisu.hisushop.databinding.FragmentHomePageBinding;


public class HomePageFragment extends Fragment {

    private FragmentHomePageBinding mHomePageBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mHomePageBinding = FragmentHomePageBinding.inflate(inflater, container, false);

        return mHomePageBinding.getRoot();
    }
}