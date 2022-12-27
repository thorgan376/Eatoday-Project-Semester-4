package eatoday.com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import eatoday.com.databinding.ActivityMainBinding;

public class ProfileFragment extends Fragment {
    private FragmentManager fragmentManager;
    private ActivityMainBinding binding;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.foods);
        button.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_my, new MyPostFragment());
            fragmentTransaction.commit();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        return view;
    }
}