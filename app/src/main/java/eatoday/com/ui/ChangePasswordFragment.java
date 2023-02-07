package eatoday.com.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eatoday.com.R;
import eatoday.com.databinding.FragmentChangePasswordBinding;

public class ChangePasswordFragment extends Fragment {
    private FragmentChangePasswordBinding changePasswordBinding;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Callback callback;

    private static final String CHANGE_PASSWORD = "change_password_fragment";
    private static final String ARG_PARAM2 = "param2";

    public interface Callback {
        void onConfirmChangePass();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        changePasswordBinding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        return changePasswordBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePasswordBinding.edtOldPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextInputLayout old_pass = changePasswordBinding.oldTextInputLayout;
                if (changePasswordBinding.edtOldPassword.length() != 0) {
                    old_pass.setEndIconMode(old_pass.END_ICON_PASSWORD_TOGGLE);
                } else {
                    old_pass.setEndIconMode(old_pass.END_ICON_NONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        changePasswordBinding.edtNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextInputLayout new_pass = changePasswordBinding.newTextInputLayout;
                if (changePasswordBinding.edtNewPassword.length() != 0) {
                    new_pass.setEndIconMode(new_pass.END_ICON_PASSWORD_TOGGLE);
                } else {
                    new_pass.setEndIconMode(new_pass.END_ICON_NONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        changePasswordBinding.edtConfirmNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextInputLayout confirm_pass = changePasswordBinding.confirmTextInputLayout;
                if (changePasswordBinding.edtConfirmNewPassword.length() != 0) {
                    confirm_pass.setEndIconMode(confirm_pass.END_ICON_PASSWORD_TOGGLE);
                } else {
                    confirm_pass.setEndIconMode(confirm_pass.END_ICON_NONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        changePasswordBinding.btnConfirmChange.setOnClickListener(v -> {
            onConfirmPassClicked();
        });
        //initialize
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    private void onConfirmPassClicked() {
        String oldPassword = changePasswordBinding.edtOldPassword.getText().toString();
        String newPassword = changePasswordBinding.edtNewPassword.getText().toString();
        String confirmPassword = changePasswordBinding.edtConfirmNewPassword.getText().toString();
        if (!validateForm(changePasswordBinding.edtOldPassword) ||
                !validateForm(changePasswordBinding.edtNewPassword) ||
                !validateForm(changePasswordBinding.edtConfirmNewPassword)) {

            return;
        }
        if (oldPassword.equals(newPassword) || oldPassword.equals(confirmPassword)) {
            Toast.makeText(getContext(),
                    "Mật khẩu cũ và mới phải khác nhau",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(getContext(),
                    "Mật khẩu mới và xác nhận phải giống nhau",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        changePassword(oldPassword, newPassword, confirmPassword);
    }

    private void reAuthUser() {
//        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),  )

    }

    private void changePassword(String oldPass, String newPass, String confirmPass) {
        if (callback != null) {
            callback.onConfirmChangePass();
        }
    }

    private boolean validateForm(EditText editText) {
        boolean valid = true;

        String dataOfEdittext = editText.getText().toString();
        if (TextUtils.isEmpty(dataOfEdittext)) {
            editText.setError("Required");
            valid = false;
        } else {
            editText.setError(null);
        }
        return valid;
    }
}