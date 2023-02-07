package eatoday.com.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eatoday.com.R;
import eatoday.com.databinding.FragmentChangePasswordBinding;

public class ChangePasswordFragment extends Fragment {
    private FragmentChangePasswordBinding changePasswordBinding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        changePasswordBinding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        return changePasswordBinding.getRoot();
    }
}