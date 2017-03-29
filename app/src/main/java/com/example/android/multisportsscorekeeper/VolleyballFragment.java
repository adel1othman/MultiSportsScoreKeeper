package com.example.android.multisportsscorekeeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Adel on 3/28/2017.
 */

public class VolleyballFragment extends Fragment {
    /*public static VolleyballFragment newInstance() {
        VolleyballFragment fragment = new VolleyballFragment();
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.volleyball, container, false);
        return rootView;
    }
}
