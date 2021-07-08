package com.example.eventmanager;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button logout, profilebtn,myEventBtn,myEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        profilebtn = findViewById(R.id.profilebtn);
        myEventBtn = findViewById(R.id.myEventBtn);
        myEvents = findViewById(R.id.event);

        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,login.class));
        });

        profilebtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,profile.class));
        });

        myEventBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,AllEvents.class));
        });

        myEvents.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,MyEvents.class));
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this,login.class));
        }

    }
}