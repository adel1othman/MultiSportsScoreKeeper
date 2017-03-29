package com.example.android.multisportsscorekeeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.multisportsscorekeeper.R;

/**
 * Created by Adel on 3/28/2017.
 */

public class WelcomeFragment extends Fragment {
    /*public static BasketballFragment newInstance() {
        BasketballFragment fragment = new BasketballFragment();
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.welcome, container, false);
        return rootView;
    }
}