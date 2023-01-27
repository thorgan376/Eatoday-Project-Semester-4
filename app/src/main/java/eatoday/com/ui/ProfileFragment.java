package eatoday.com.ui;

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

import eatoday.com.R;
import eatoday.com.databinding.ActivityMainBinding;

public class ProfileFragment extends Fragment {
    private FragmentManager fragmentManager;
//    private ActivityMainBinding binding;
    private Callback callback;

    public interface Callback{
        void onClickFood();
        void onClickUser();

    }
    public void setCallback(Callback callback){
        this.callback = callback;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button_foods = (Button) view.findViewById(R.id.btn_foods);
        button_foods.setOnClickListener(v -> {
           if(callback != null){
               callback.onClickFood();
           }
        });
        Button button_account = (Button) view.findViewById(R.id.btn_changeuser);
        button_account.setOnClickListener(v -> {
            if(callback != null){
                callback.onClickUser();
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