package com.example.montrealfragrances;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private EditText InputName, InputEmail, InputPassword;
    TextView mLoginBtn;





    private FirebaseAuth mAuth;
    private android.view.View.OnClickListener View;
    // Initialize

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();


        CreateAccountButton = findViewById(R.id.register_btn);
        InputName = findViewById(R.id.register_username_input);
        InputPassword = findViewById(R.id.register_password_input);
        InputEmail = findViewById(R.id.register_email_input);

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }



        CreateAccountButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
           String email= InputEmail.getText().toString().trim();
           String password= InputPassword.getText().toString().trim();
           if (TextUtils.isEmpty(email)){
               InputEmail.setError("enter email");
               return;
           }
           if(TextUtils.isEmpty(password)){
               InputPassword.setError("enter password");
               return;
           }

           mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(RegisterActivity.this,"User created",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                   }else{
                       Toast.makeText(RegisterActivity.this,"error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                   }
               }
           });

        }
    });
}  }
