package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DanceDetails extends AppCompatActivity {

    Button danceRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_details);

        danceRegister = findViewById(R.id.danceRegister);

        danceRegister.setOnClickListener(view -> {
            startActivity(new Intent(DanceDetails.this,RegisterEvents.class));
        });
    }
}