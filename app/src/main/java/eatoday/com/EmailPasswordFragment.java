package eatoday.com;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordFragment extends Fragment {
    private static final String TAG = "EmailPassword";
    //declare authentication
    private FirebaseAuth emailPasswordAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // start initialize_auth
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

                        if (!task.isSuccessful()) {
                            Log.d(TAG, "Authentication failed");
                        }

                    }
                });
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
        });
    }
}
