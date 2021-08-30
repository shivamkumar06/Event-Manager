package com.example.eventmanager;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity {

    EditText mEmail,mpassword;
    Button mloginBtn;
    TextView mRegisterBtn,forgetTextLink;
    FirebaseAuth mAuth;
    ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mloginBtn = findViewById(R.id.loginBtn);
        mRegisterBtn = findViewById(R.id.RegisterLink);
        ProgressBar = findViewById(R.id.PB);
       // forgetTextLink = findViewById(R.id.forgetpassword);


        mAuth = FirebaseAuth.getInstance();

        mloginBtn.setOnClickListener(view -> {
            ProgressBar.setVisibility(view.VISIBLE);
            loginUser();
        });
        mRegisterBtn.setOnClickListener(view -> {
            startActivity(new Intent(login.this,Registraion.class));
        });

//        forgetTextLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText reset = new EditText(view.getContext());
//                AlertDialog.Builder passwordResetDiaolog = new AlertDialog.Builder(view.getContext());
//                passwordResetDiaolog.setTitle("ResetPassword");
//                passwordResetDiaolog.setMessage("Enter your Email to recived the link");
//                passwordResetDiaolog.setView(res);
//            }
//        });
    }



    private void loginUser(){
        String email = mEmail.getText().toString().trim();
        String password = mpassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            mEmail.setError("email cannot be empty");
            mEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            mpassword.setError("password cannot be empty");
            mpassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login.this, "User Logged In Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,MainActivity.class));
                    }else{
                        Toast.makeText(login.this, "Error:-" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}