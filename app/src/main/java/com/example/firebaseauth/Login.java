package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    com.google.firebase.auth.FirebaseAuth mAuth;
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        com.google.firebase.auth.FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        com.google.firebase.auth.FirebaseAuth mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        TextView txtRegister=findViewById(R.id.txtGoToRegister);
        Button btnLogin=findViewById(R.id.btnLogin);
        EditText txtEmail=findViewById(R.id.txtEmailSignin);
        EditText txtPassword=findViewById(R.id.txtPasswordSignin);
        TextView txtForgotPassword=findViewById(R.id.txtForgotPasswordS);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNext = new Intent(Login.this,Register.class);
                startActivity(iNext);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                email= String.valueOf(txtEmail.getText());
                password= String.valueOf(txtPassword.getText());

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful())
                            {
                               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                               startActivity(intent);
                               finish();
                            }
                            else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });

      txtForgotPassword.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view)
          {
              Intent intent = new Intent(Login.this,ForgotPassword.class);
              startActivity(intent);
              finish();
          }
      });

    }
}