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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.security.auth.callback.Callback;

import eatoday.com.authentication.LoginFragment;
import eatoday.com.R;
import eatoday.com.databinding.ActivityMainBinding;
import eatoday.com.databinding.FragmentProfilesBinding;

public class ProfileFragment extends Fragment {
    private LoginFragment loginFragment = new LoginFragment();
    private FragmentManager fragmentManager;
//    private ActivityMainBinding binding;
    private Callback callback;
    private FragmentProfilesBinding profilesBinding;
    private FirebaseAuth mAuth;

    public interface Callback{
        void onClickFood();
        void onClickUser();
    }
    public void setCallback(Callback callback){
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profilesBinding = FragmentProfilesBinding.inflate(inflater, container, false);
        return profilesBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button_foods = (Button) view.findViewById(R.id.btn_foods);
        Button button_account = (Button) view.findViewById(R.id.btn_changeuser);
        button_account.setOnClickListener(v -> {
            if(callback != null){
                callback.onClickUser();
            }
        });

        button_foods.setOnClickListener(v -> {
           if(callback != null){
               callback.onClickFood();
           }
        });

        profilesBinding.btnLogout.setOnClickListener(view1 -> {
            signOut();
        });
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }
    public void replaceFragment (Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction() .setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        );
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

        } else {
            replaceFragment(loginFragment);
        }
    }
}