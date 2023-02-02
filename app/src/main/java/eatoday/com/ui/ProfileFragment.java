package eatoday.com.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import eatoday.com.authentication.LoginFragment;
import eatoday.com.R;
import eatoday.com.databinding.FragmentProfilesBinding;

public class ProfileFragment extends Fragment {
    private LoginFragment loginFragment = new LoginFragment();
    private FragmentManager fragmentManager;
    private Callback callback;
    private FragmentProfilesBinding profilesBinding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private static final String RELOAD_INFO = "Reload profiles info";

    public interface Callback{
        void onClickFood();
        void onClickUser();
        void onLogOut();
    }
    public void setCallback(Callback callback){
        this.callback = callback;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        profilesBinding.btnChangeuser.setOnClickListener(v -> {
            if(callback != null){
                callback.onClickUser();
            }
        });

        profilesBinding.btnFoods.setOnClickListener(v -> {
            if(callback != null){
                callback.onClickFood();
            }
        });

        profilesBinding.btnLogout.setOnClickListener(v -> {
            if(callback != null){
                mAuth.signOut();
                Toast.makeText(getContext(),
                        "Đăng xuất",
                        Toast.LENGTH_SHORT).show();
                callback.onLogOut();
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            reload();
        }
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

    private void reload() {
        mAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
//                    updateUI(mAuth.getCurrentUser());
                    Log.v(RELOAD_INFO,"Reload profile info successfully",task.getException());
                    updateUI(mAuth.getCurrentUser());
                } else {
                    Log.e(RELOAD_INFO,"Reload profile info error",task.getException());
                    Toast.makeText(getContext(),
                            "Lỗi reload thông tin user",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        }); //reload user information - Testing only
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            profilesBinding.txtName.setText(user.getEmail());
        } else {
            Toast.makeText(getContext(),
                    "User không tồn tại",
                    Toast.LENGTH_SHORT).show();
        }
    }
}