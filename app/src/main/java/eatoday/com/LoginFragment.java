package eatoday.com;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import eatoday.com.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private static final String EMAIL_PASSWORD = "EmailPassword";
    private static final String GOOGLE = "GoogleSignIn";
    private static final String FACEBOOK = "FacebookSignIn";
    //declare authentication
    private FirebaseAuth mAuth;

    private FragmentLoginBinding loginBinding;

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
                String email = loginBinding.edtEmailInfo.getText().toString();
                String password = loginBinding.edtPasswordInfo.getText().toString();
                signIn(email, password);
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
                            Log.d(EMAIL_PASSWORD, "createUserWithE&P:success");
//                            FirebaseUser user = emailPasswordAuth.getCurrentUser();

                        } else {
                            Log.d(EMAIL_PASSWORD, "createUserWithE&P:failure", task.getException());
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
                            Log.d(EMAIL_PASSWORD, "Authenticate with email and password successful");
                            Toast.makeText(getContext(), "Authentication success",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //Sign in fails, display a message to the user
                            Log.w(EMAIL_PASSWORD, "signInWithE&P:failure",task.getException());
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
                    Toast.makeText(getContext(),
                            "Reload successful",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(EMAIL_PASSWORD,"",task.getException());
                    Toast.makeText(getContext(),
                            "Failed to reload user",
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
}