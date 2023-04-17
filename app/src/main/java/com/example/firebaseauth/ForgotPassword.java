package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button btnContinue;
    private EditText txtForgotEmail;
    private String email;
    com.google.firebase.auth.FirebaseAuth auth = com.google.firebase.auth.FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        txtForgotEmail=findViewById(R.id.txtForgotEmail);
        btnContinue=findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }
        });


    }
    private void validatedata()

    {
        email=txtForgotEmail.getText().toString();
        if(email.isEmpty())
        {
            txtForgotEmail.setError("Reqired");
        }
        else
        {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ForgotPassword.this, "Check Yor Mail", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassword.this,Login.class);
                        startActivity(intent);
                        finish();
                    }

                    else
                    {
                        Toast.makeText(ForgotPassword.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
//    private void forgotPassword()
//    {
//
//    }
}