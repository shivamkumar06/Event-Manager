package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FoodDetails extends AppCompatActivity {
    Button foodRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        foodRegister = findViewById(R.id.foodRegister);

        foodRegister.setOnClickListener(view -> {
            startActivity(new Intent(FoodDetails.this,RegisterEvents.class));
        });
    }
}