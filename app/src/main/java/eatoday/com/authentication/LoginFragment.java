package eatoday.com.authentication;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eatoday.com.HomeFragment;
import eatoday.com.ProfileFragment;
import eatoday.com.R;
import eatoday.com.databinding.FragmentHomeBinding;
import eatoday.com.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private static final String SIGN_IN_METHOD = "SignInMethod";
    //declare authentication
    private FirebaseAuth mAuth;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment = new HomeFragment();

    private FragmentLoginBinding loginBinding;

    public void replaceFragment (Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(

                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        );
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(null).commit();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return loginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Buttons
        loginBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare
                Fragment fragment = new ProfileFragment();
                String email = loginBinding.edtEmailInfo.getText().toString();
                String password = loginBinding.edtPasswordInfo.getText().toString();

                signIn(email, password);
                replaceFragment(fragment);

            }
        });

        loginBinding.btnNotRegisterSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginBinding.edtEmailInfo.getText().toString();
                String password = loginBinding.edtPasswordInfo.getText().toString();
//                createNewAccount(email, password);
            }
        });

        loginBinding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void createNewAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(SIGN_IN_METHOD, "createUserWithE&P:success");
//                            FirebaseUser user = emailPasswordAuth.getCurrentUser();

                        } else {
                            Log.d(SIGN_IN_METHOD, "createUserWithE&P:failure", task.getException());
                            Toast.makeText(getContext(),
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(SIGN_IN_METHOD, "Authenticate with email and password successful");
                            Toast.makeText(getContext(), "Authentication success",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //Sign in fails, display a message to the user
                            Log.e(SIGN_IN_METHOD, "signInWithE&P:failure",task.getException());
                            Toast.makeText(getContext(), "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
    }

    private void sendEmailVerification() {

    }

    private void reload() {
        mAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.v(SIGN_IN_METHOD,"Reload successfully",task.getException());
                    Toast.makeText(getContext(),
                            "Đăng nhập thành công",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(SIGN_IN_METHOD,"Reload error",task.getException());
                    Toast.makeText(getContext(),
                            "Lỗi đăng nhập",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }); //reload user information - Testing only
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {

        } else {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        loginBinding = null;
    }
}