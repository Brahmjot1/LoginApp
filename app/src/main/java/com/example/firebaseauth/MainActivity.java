package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    com.google.firebase.auth.FirebaseUser user;
    com.google.firebase.auth.FirebaseAuth auth;
    Button btnLogout;
    TextView txtEmailMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth= com.google.firebase.auth.FirebaseAuth.getInstance();
        btnLogout=findViewById(R.id.btnLogout);
        txtEmailMain=findViewById(R.id.userDetail);
        user=auth.getCurrentUser();

        if (user==null)
        {
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
        else
        {
            txtEmailMain.setText(user.getEmail());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.google.firebase.auth.FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}