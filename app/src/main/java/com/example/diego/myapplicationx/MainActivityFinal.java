package com.example.diego.myapplicationx;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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


public class MainActivityFinal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Apoderado apoderado;
    public String Comunicados;
    private TextView textView;
    private EditText editText;
    private Button boton;
    private Button signout;
    public int k;
    final String[] emisores = new String[3];
    final String[] fechas = new String[3];
    final String[] informacion = new String[3];
    ArrayList<Comunicado> Coms = new ArrayList<>();


    DatabaseReference mDbR = FirebaseDatabase.getInstance().getReference();
    FirebaseUser fbu = FirebaseAuth.getInstance().getCurrentUser();
    public String UserID = fbu.getUid();
    DatabaseReference dbApoderado = mDbR.child(UserID);
    DatabaseReference Com = mDbR.child("Comunicados");
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_final);

        progressBar=(ProgressBar)findViewById(R.id.simpleProgressBar);
        progressBar.setMax(100);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"profesorjefe@proyecto.apps"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Tema a tratar");
                i.putExtra(Intent.EXTRA_TEXT   , "Estimado, \n");
                try {
                    startActivity(Intent.createChooser(i, "Enviar mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    DisplayToast("No tiene ninguna configuracion de correo instalada en su Dispotivo.");
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        k = 0;
        //Comunicados = "";
        Com.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Coms.add(new Comunicado(dataSnapshot1));
                }/*
                Comunicados = "\n\n";
                for(int i = 0; i<3 ; i++){
                    Comunicados = Comunicados+"\t\tDe: " + emisores[i] + "\n" +"\t\tFecha :" + fechas[i] + "\n\n\t" + informacion[i] + "\n\n\n";
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        dbApoderado.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                apoderado = new Apoderado(dataSnapshot);
                DisplayToast("Sesión iniciada");
                progressBar.setProgress(100);
                ((ViewGroup)progressBar.getParent()).removeView(progressBar);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedor,new FragInicio()).commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void DisplayToast(String mensaje) {
        Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_SHORT).show();
    }


    protected void onPostExecute(final Void result){
        // Update your views here
        progressBar.setVisibility(View.GONE);
    }

    public Apoderado getApoderado(){
        return apoderado;
    }
    public DatabaseReference getmDbR(){return mDbR;}
    public String getComunicados(){return Comunicados;}
    public ArrayList<Comunicado> getComs(){
        return Coms;
    }
    public String[] getEmisores(){
        return emisores;
    }
    public String[] getFechas(){
        return fechas;
    }
    public String[] getInformacion(){
        return informacion;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_final, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(id == R.id.nav_Inicio){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new FragInicio()).commit();
        } else if (id == R.id.nav_Notas) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new FragDos()).commit();
        } else if (id == R.id.nav_Asistencia) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new FragUno()).commit();
        } else if (id == R.id.nav_Comunicados) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new FragTres()).commit();
        } else if (id == R.id.nav_Acercade) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new FragQuad()).commit();
        } else if (id == R.id.nav_Cerrarsesion) {
                finish();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivityFinal.this,Login.class);
                startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
