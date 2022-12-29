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

import javax.security.auth.callback.Callback;

import eatoday.com.databinding.ActivityMainBinding;

public class ProfileFragment extends Fragment {
    private FragmentManager fragmentManager;
    private ActivityMainBinding binding;
    private Callback callback;

    public interface Callback{
        void onClickFood();
    }
    public void setCallback(Callback callback){
        this.callback = callback;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.foods);
        button.setOnClickListener(v -> {
           if(callback != null){
               callback.onClickFood();
           }
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