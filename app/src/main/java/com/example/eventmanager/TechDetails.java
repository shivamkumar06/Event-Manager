package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TechDetails extends AppCompatActivity {
    Button techRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_details);

        techRegister = findViewById(R.id.techRegister);

        techRegister.setOnClickListener(view -> {
            startActivity(new Intent(TechDetails.this,RegisterEvents.class));
        });
    }
}