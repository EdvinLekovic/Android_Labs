package mk.ukim.finki.lab4_firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etEmail,etPassword;
    private Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail);
        //password need to be at least 6 characters
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if(checkIfEmailOrPasswordIsCorrect(email,password)) {
                    login(email, password);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if(checkIfEmailOrPasswordIsCorrect(email,password)) {
                    register(email, password);
                }
            }
        });
    }

    private void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Registration successful",Toast.LENGTH_LONG).show();
                }
                else{
                    System.out.println(task.getException().getMessage());
                    Toast.makeText(LoginActivity.this,"Registration failed",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnCanceledListener(this, new OnCanceledListener() {
            @Override
            public void onCanceled() {
                System.out.println("here");
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }


    private boolean checkIfEmailOrPasswordIsCorrect(String email,String password){
        return !email.isEmpty() && !password.isEmpty();
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    navigateLoggedInUser();
                }
                else{
                    Toast.makeText(LoginActivity.this,"Login in failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        if(IfLoggedIn()){
            navigateLoggedInUser();
        }

    }

    private boolean IfLoggedIn(){
        return  mAuth.getCurrentUser() != null;
    }

    private void navigateLoggedInUser(){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}