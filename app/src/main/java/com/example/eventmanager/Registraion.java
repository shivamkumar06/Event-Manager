package com.example.eventmanager;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Registraion extends AppCompatActivity {
    EditText mfullName,mEmail,mpassword,mphone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);

        mfullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mphone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.loginLink);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        mRegisterBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            createUser();
        });

        mLoginBtn.setOnClickListener(view -> {
            startActivity(new Intent(Registraion.this,login.class));
        });



    }
    private void createUser(){
        String email = mEmail.getText().toString().trim();
        String password = mpassword.getText().toString();
        String fullname = mfullName.getText().toString();
        String phone = mphone.getText().toString();

        if(TextUtils.isEmpty(email)){
            mEmail.setError("email cannot be empty");
            mEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            mpassword.setError("password cannot be empty");
            mpassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){



                @Override
                public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Registraion.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
                        userId = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(userId);
                        HashMap<String,Object> user = new HashMap<>();
                        user.put("fName",fullname);
                        user.put("email",email);
                        user.put("phone",phone);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "onSuccess: succefullu created a user with id: "+ userId);
                            }
                        });
                        startActivity(new Intent(Registraion.this,login.class));
                    }else{
                        Toast.makeText(Registraion.this, "Error:-" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}