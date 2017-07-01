package com.example.diego.myapplicationx;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FragTres extends Fragment {
    DatabaseReference db;
    TextView texto;
    String Comu;
    public int k;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frag_tres, container, false);
        texto = (TextView) rootView.findViewById(R.id.textoComunicado);
        String Comunicado = ((MainActivityFinal) getActivity()).getComunicados();
        System.out.println(Comunicado);
        texto.setText(Comunicado);
        getActivity().setTitle("Comunicados");
        texto.setBackgroundColor(Color.WHITE);

        return rootView;
    }

}
