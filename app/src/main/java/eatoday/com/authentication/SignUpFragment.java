package eatoday.com.authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import eatoday.com.databinding.FragmentSignUpBinding;
import eatoday.com.R;

public class SignUpFragment extends Fragment {

    private static final String SIGN_UP_METHOD = "Sign up method";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth mAuth;
    private FragmentSignUpBinding signUpBinding;
    private Callback callback;

    public interface Callback {
        void onSignUp();
        void alreadyRegistered();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false);
        return signUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Button

        signUpBinding.btnSignUp.setOnClickListener(v -> {
            String firstName = signUpBinding.edtFirstName.getText().toString();
            String lastName = signUpBinding.edtLastName.getText().toString();
            String dayOfBirthDate = signUpBinding.dayOfBirthDate.getText().toString();
            String monthOfBirthDate = signUpBinding.monthOfBirthDate.getText().toString();
            String yearOfBirthDate = signUpBinding.yearOfBirthDate.getText().toString();
            String emailAddress = signUpBinding.edtEmailAddress.getText().toString();
            String password = signUpBinding.edtPassword.getText().toString();
            String reEnterPassword = signUpBinding.edtReEnterPassword.getText().toString();

            createNewAccount(emailAddress, password);
        });

        signUpBinding.btnRegisteredLogIn.setOnClickListener(v -> {
            if (callback != null) {
                callback.alreadyRegistered();
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    private void createNewAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        Log.d(SIGN_UP_METHOD, "createUserWithE&P:success");
                        if (callback != null) {
                            callback.onSignUp();
                        }
                    } else {
                        Log.d(SIGN_UP_METHOD, "createUserWithE&P:failure", task.getException());
                        Toast.makeText(getContext(),
                                "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String firstName = signUpBinding.edtFirstName.getText().toString();
            if( TextUtils.isEmpty(firstName)) {
                signUpBinding.edtFirstName.setError("Required");
                valid = false;
            } else {
                signUpBinding.edtFirstName.setError(null);
            }
        String lastName = signUpBinding.edtLastName.getText().toString();
            if( TextUtils.isEmpty(lastName)) {
                signUpBinding.edtLastName.setError("Required");
                valid = false;
            } else {
                signUpBinding.edtLastName.setError(null);
            }
        String dayOfBirthDate = signUpBinding.dayOfBirthDate.getText().toString();
            if( TextUtils.isEmpty(dayOfBirthDate)) {
                signUpBinding.dayOfBirthDate.setError("Required");
                valid = false;
            } else {
                signUpBinding.dayOfBirthDate.setError(null);
            }
        String monthOfBirthDate = signUpBinding.monthOfBirthDate.getText().toString();
            if( TextUtils.isEmpty(monthOfBirthDate)) {
                signUpBinding.monthOfBirthDate.setError("Required");
                valid = false;
            } else {
                signUpBinding.monthOfBirthDate.setError(null);
            }
        String yearOfBirthDate = signUpBinding.yearOfBirthDate.getText().toString();
            if( TextUtils.isEmpty(yearOfBirthDate)) {
                signUpBinding.yearOfBirthDate.setError("Required");
                valid = false;
            } else {
                signUpBinding.yearOfBirthDate.setError(null);
            }
        String emailAddress = signUpBinding.edtEmailAddress.getText().toString();
            if( TextUtils.isEmpty(emailAddress)) {
                signUpBinding.edtEmailAddress.setError("Required");
                valid = false;
            } else {
                signUpBinding.edtEmailAddress.setError(null);
            }
        String password = signUpBinding.edtPassword.getText().toString();
            if( TextUtils.isEmpty(password)) {
                signUpBinding.edtPassword.setError("Required");
                valid = false;
            } else {
                signUpBinding.edtPassword.setError(null);
            }
        String reEnterPassword = signUpBinding.edtReEnterPassword.getText().toString();
            if( TextUtils.isEmpty(reEnterPassword)) {
                signUpBinding.edtReEnterPassword.setError("Required");
                valid = false;
            } else {
                signUpBinding.edtReEnterPassword.setError(null);
            }
        return valid;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        signUpBinding = null;
    }
}