package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AllEvents extends AppCompatActivity {
    Button techBtn,danceBtn,musicBtn,foodBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        techBtn = findViewById(R.id.techBtn);
        danceBtn = findViewById(R.id.danceBtn);
        musicBtn = findViewById(R.id.musicBtn);
        foodBtn = findViewById(R.id.foodBtn);

        techBtn.setOnClickListener(view -> {
            startActivity(new Intent(AllEvents.this,TechDetails.class));
        });

        danceBtn.setOnClickListener(view -> {
            startActivity(new Intent(AllEvents.this,DanceDetails.class));
        });

        musicBtn.setOnClickListener(view -> {
            startActivity(new Intent(AllEvents.this,MusicDetails.class));
        });

        foodBtn.setOnClickListener(view -> {
            startActivity(new Intent(AllEvents.this,FoodDetails.class));
        });


    }
}