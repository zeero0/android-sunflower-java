package com.zeeroapps.sunflower.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeeroapps.sunflower.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GardenFragment extends Fragment {


    public GardenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_garden, container, false);
        return view;
    }

}
