package com.hisu.hisushop.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hisu.hisushop.MainActivity;
import com.hisu.hisushop.databinding.FragmentSplashScreenBinding;

public class SplashScreenFragment extends Fragment {

    public static final long DELAY_TIME = 2 * 1000;

    private FragmentSplashScreenBinding mSplashScreenBinding;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainActivity = (MainActivity) getActivity();

        mSplashScreenBinding = FragmentSplashScreenBinding.inflate(
                inflater, container, false
        );

        checkUserLogInState();

        return mSplashScreenBinding.getRoot();
    }

    private void checkUserLogInState() {
        boolean isLoggedIn = getUserLogInStatus();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            if (isLoggedIn)
                mainActivity.setFragment(new HomePageFragment());
            else
                mainActivity.setFragment(new LoginFragment());

        }, DELAY_TIME);
    }

    private boolean getUserLogInStatus() {
        return false;
    }
}