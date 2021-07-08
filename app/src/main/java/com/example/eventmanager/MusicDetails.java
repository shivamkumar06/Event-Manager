package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MusicDetails extends AppCompatActivity {
    Button musicRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        musicRegister = findViewById(R.id.musicRegister);

        musicRegister.setOnClickListener(view -> {
            startActivity(new Intent(MusicDetails.this,RegisterEvents.class));
        });
    }
}