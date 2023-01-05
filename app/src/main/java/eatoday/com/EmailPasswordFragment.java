package eatoday.com;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import eatoday.com.databinding.FragmentLoginBinding;

public class EmailPasswordFragment extends Fragment {
    private static final String TAG = "EmailPassword";
    //declare authentication
    private FirebaseAuth emailPasswordAuth;

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
                //signIn method
            }
        });

        loginBinding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginBinding.edtEmailInfo.getText().toString();
                String password = loginBinding.edtPasswordInfo.getText().toString();
                //createAccount method
            }
        });

        // Initialize Firebase Auth
        emailPasswordAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = emailPasswordAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void createNewAccount(String email, String password) {
        emailPasswordAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithE&P:success");
//                            FirebaseUser user = emailPasswordAuth.getCurrentUser();

                        } else {
                            Log.d(TAG, "createUserWithE&P:failure", task.getException());
                            Toast.makeText(getContext(),
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        emailPasswordAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authenticate with email and password successful");
                        } else {
                            //Sign in fails, display a message to the user
                            Log.w(TAG, "signInWithE&P:failure",task.getException());
                            Toast.makeText(getContext(), "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signOut() {
        emailPasswordAuth.signOut();
    }

    private void sendEmailVerification() {

    }

    private void reload() {
        emailPasswordAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(),
                            "Reload successful",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG,"",task.getException());
                    Toast.makeText(getContext(),
                            "Failed to reload user",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }); //reload user information - Testing only
    }

    private void updateUI(FirebaseUser user) {
        //updateUI when user sign in or create new account successfully
    }
}
