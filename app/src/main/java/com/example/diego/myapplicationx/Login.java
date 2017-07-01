package com.example.diego.myapplicationx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText email,pass;
    Button login,salir;
    FirebaseAuth.AuthStateListener miauListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        login = (Button) findViewById(R.id.email_sign_in_button);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        salir = (Button) findViewById(R.id.email_exit_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSesion(email.getText().toString(),pass.getText().toString());
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        miauListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    DisplayToast("Cargando...");
                    startActivity(new Intent(Login.this, MainActivityFinal.class));
                    finish();
                }
            }
        };
    }

    public void IniciarSesion(String email, String password){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    DisplayToast("Sesión iniciada");
                }else{
                    DisplayToast("Permiso denegado");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(miauListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(miauListener !=null){
            FirebaseAuth.getInstance().removeAuthStateListener(miauListener);
        }
    }

    private void DisplayToast(String mensaje) {
        Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
