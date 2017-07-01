package com.example.diego.myapplicationx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button boton;
    private Button signout;

    DatabaseReference mDbR = FirebaseDatabase.getInstance().getReference();
    FirebaseUser fbu = FirebaseAuth.getInstance().getCurrentUser();
    String UserID = fbu.getUid();
    DatabaseReference dbApoderado = mDbR.child(UserID);
    DatabaseReference nAp = dbApoderado.child("nombre_apoderado");
    Apoderado apoderado;
    ArrayList<Alumno> weas = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        dbApoderado.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                apoderado = new Apoderado(dataSnapshot);
                DisplayToast("Carga de datos completa");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.editar);
        boton = (Button) findViewById(R.id.boton);
        signout = (Button) findViewById(R.id.botonSignout);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayToast(apoderado.getAlumnos_apoderado().get(0).getAsistencia().get(0).getFecha());
            }
        });

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



    private void DisplayToast(String mensaje) {
        Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

}
