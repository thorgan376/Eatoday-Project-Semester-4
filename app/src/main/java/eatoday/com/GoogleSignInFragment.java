package eatoday.com;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import eatoday.com.databinding.FragmentLoginBinding;

public class GoogleSignInFragment extends Fragment {
    private static final String TAG = "GoogleFragment";
    private FirebaseAuth googleAuth;

    private SignInClient signInClient;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = googleAuth.getCurrentUser();
    }

    private void handleSignInResult(Intent data) {
        try {
            SignInCredential credential = signInClient.getSignInCredentialFromIntent(data);
            String idToken = credential.getGoogleIdToken();
            Log.d(TAG, "firebaseAuthWithGoogle" + credential.getId());
        } catch (ApiException e) {
            Log.w(TAG, "Google Sign in failed", e);
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        googleAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential: Success");
                            FirebaseUser user = googleAuth.getCurrentUser();
                        } else {
                            Log.e(TAG, "signInWithCredential: Failure", task.getException());

                        }
                    }
                });
    }

    private void signIn() {

    }
}
