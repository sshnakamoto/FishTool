package com.github.nakamotossh.fishtool.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nakamotossh.fishtool.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaunaFragment extends Fragment {


    public FaunaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.item_specs, container, false);
//        LinearLayout linearLayout = root.findViewById(R.id.test);
        return root;
    }

}
