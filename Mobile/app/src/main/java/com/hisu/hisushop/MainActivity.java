package com.hisu.hisushop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hisu.hisushop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());

        mMainBinding.btnLogin.setOnClickListener(view -> login());
    }

    private void login() {
        String email = mMainBinding.edtEmail.getText().toString().trim();
        String password = mMainBinding.edtPwd.getText().toString().trim();

        dialog.setMessage("Working on it...");
        dialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    dialog.dismiss();
                    showDialog("Success", authResult.getUser().getEmail());
                })
                .addOnFailureListener(e -> {
                    dialog.dismiss();
                    showDialog("Error!", e.getMessage());
                });
    }

    private void showDialog(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Ok", null).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }

    @Override
    protected void onDestroy() {
        if (firebaseAuth != null)
            firebaseAuth.signOut();

        super.onDestroy();
    }
}