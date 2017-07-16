package com.example.raul.myboomnavdrawer.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raul.myboomnavdrawer.R;

/**
 * Created by malik on 7/14/2017.
 */

public class TimeTableFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_time_table, container, false);
        return view;
    }
}
