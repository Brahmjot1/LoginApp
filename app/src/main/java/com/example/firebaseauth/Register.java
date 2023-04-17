package com.example.firebaseauth;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;
//    @Override
//    public void onStart() {
//        super.onStart();
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
        setContentView(R.layout.activity_register2);

        EditText txtEmailR=findViewById(R.id.txtEmailR);
        EditText txtPasswordR = findViewById(R.id.txtPasswordR);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView txtGotoSignin= findViewById(R.id.txtGotoSigin);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        final Pattern PASSWORD_PATTERN =
                Pattern.compile( "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$");


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(txtEmailR.getText());
                password = String.valueOf(txtPasswordR.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!PASSWORD_PATTERN.matcher(password).matches())
                    txtPasswordR.setError("Weak Password");
                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Account Created!!", Toast.LENGTH_SHORT).show();

                                        Intent iNext = new Intent(Register.this, Login.class);
                                        startActivity(iNext);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
        txtGotoSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNext = new Intent(Register.this,Login.class);
                startActivity(iNext);
//                finish();
            }
        });

    }
}