package com.github.nakamotossh.fishtool.fragments.parameters;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.nakamotossh.fishtool.extras.ParamUtils;
import com.github.nakamotossh.fishtool.fragments.BaseParameter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmmoniaFragment extends BaseParameter {

    public AmmoniaFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseFragment(getArguments(), ParamUtils.AMMONIA_PARAM);
    }
}
