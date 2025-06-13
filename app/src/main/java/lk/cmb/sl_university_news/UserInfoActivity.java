package lk.cmb.sl_university_news;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserInfoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private TextView userNameLabel, emailLabel;
    private Button signOutButton, editInfoButton;

    private String currentName = "Anonymous User";
    private String currentEmail = "No Email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        userNameLabel = findViewById(R.id.user_name_label);
        emailLabel = findViewById(R.id.email_label);
        signOutButton = findViewById(R.id.sign_out_button);
        editInfoButton = findViewById(R.id.edit_info_button);

        // Load user data
        if (currentUser != null) {
            if (currentUser.getDisplayName() != null && !currentUser.getDisplayName().isEmpty()) {
                currentName = currentUser.getDisplayName();
            }
            if (currentUser.getEmail() != null && !currentUser.getEmail().isEmpty()) {
                currentEmail = currentUser.getEmail();
            }
        }

        userNameLabel.setText(currentName);
        emailLabel.setText(currentEmail);

        // Edit Info Button
        editInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditInfoDialog();
            }
        });

        // Sign Out Button
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignOutDialog();
            }
        });
    }

    private void showEditInfoDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_userinfo, null);

        final EditText inputName = dialogView.findViewById(R.id.edit_name_input);
        final EditText inputEmail = dialogView.findViewById(R.id.edit_email_input);

        inputName.setText(currentName);
        inputEmail.setText(currentEmail);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit User Info");
        builder.setView(dialogView);
        builder.setPositiveButton("Save", (dialog, which) -> {
            currentName = inputName.getText().toString().trim();
            currentEmail = inputEmail.getText().toString().trim();

            userNameLabel.setText(currentName);
            emailLabel.setText(currentEmail);

            // Optional: Update Firebase Auth profile (only name can be updated here)
            /*
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(currentName)
                    .build();
            currentUser.updateProfile(profileUpdates);
            */
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showSignOutDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_confirm_signout, null);

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        Button btnOk = dialogView.findViewById(R.id.btn_ok_sign_out);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel_sign_out);

        btnOk.setOnClickListener(v -> {
            mAuth.signOut();
            dialog.dismiss();
            Intent intent = new Intent(UserInfoActivity.this, login_screen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
