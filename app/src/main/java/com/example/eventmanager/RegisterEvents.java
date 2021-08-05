package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RegisterEvents extends AppCompatActivity {
    EditText eventName, place , date;
    Button EventRegisterBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_events);

        eventName = findViewById(R.id.eventname);
        place = findViewById(R.id.place);
        date = findViewById(R.id.date);
        EventRegisterBtn = findViewById(R.id.eventRegisterBtn);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.pb2);

        EventRegisterBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String EventName = eventName.getText().toString();
            String venue = place.getText().toString();
            String time = date.getText().toString();

            HashMap<String,Object> event = new HashMap<>();
            event.put("Event_Name",EventName);
            event.put("venue",venue);
            event.put("date",date);
            userId = mAuth.getCurrentUser().getUid();
            CollectionReference collectionReference = fStore.collection("users");
            collectionReference.document(userId).collection("Registered_Events").add(event);

            startActivity(new Intent(RegisterEvents.this,AllEvents.class));

        });

    }

}