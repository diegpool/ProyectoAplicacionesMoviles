package com.example.diego.myapplicationx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class FragInicio extends Fragment {
    Button Cerrar,Notas,Asistencia,Comunicados;
    FragmentManager fragmentManager;
    ImageView img;
    TextView bien;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_frag_inicio, container, false);
        getActivity().setTitle("Inicio");
        img = (ImageView) rootView.findViewById(R.id.bienvenido);
        int id = getContext().getResources().getIdentifier("bienvenido","drawable",getContext().getPackageName());
        img.setImageResource(id);
        fragmentManager = getFragmentManager();
        Cerrar = (Button) rootView.findViewById(R.id.bCerrar);
        Cerrar.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        Cerrar.setText("Cerrar\tSesión");
        Cerrar.setTextColor(Color.WHITE);
        Cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });
        Notas = (Button) rootView.findViewById(R.id.bNotas);
        Notas.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
        Notas.setText("Notas");
        Notas.setTextColor(Color.WHITE);
        Notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.contenedor,new FragDos()).commit();
            }
        });
        Asistencia = (Button) rootView.findViewById(R.id.bAsistencia);
        Asistencia.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
        Asistencia.setText("Asistencia");
        Asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.contenedor,new FragUno()).commit();
            }
        });
        Comunicados = (Button) rootView.findViewById(R.id.bComunicados);
        Comunicados.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        Comunicados.setText("Comunicados");
        Comunicados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.contenedor,new FragTres()).commit();
            }
        });
        return rootView;
    }

    private void DisplayToast(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
