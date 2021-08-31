package com.example.taskmaster;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragmentTaskActivity extends Fragment {
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "body";
    private static final String ARG_PARAM3 = "state";
    private String mTitle;
    private String mBody;
    private String mState;
    public fragmentTaskActivity(){}
    public static fragmentTaskActivity newInstance(String mTitle, String mBody, String mState) {
        fragmentTaskActivity fragment = new fragmentTaskActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mTitle);
        args.putString(ARG_PARAM2, mBody);
        args.putString(ARG_PARAM2, mState);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM1);
            mBody = getArguments().getString(ARG_PARAM2);
            mState = getArguments().getString(ARG_PARAM3);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_task, container, false);
    }

}