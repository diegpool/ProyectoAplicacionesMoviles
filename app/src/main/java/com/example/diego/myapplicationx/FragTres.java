package com.example.diego.myapplicationx;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragTres extends Fragment {
    TextView texto;
    ArrayList<Comunicado> Coms;
    private GridView gridView;
    private GridAdapterCom adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frag_tres, container, false);
        Coms = ((MainActivityFinal) getActivity()).getComs();
        getActivity().setTitle("Comunicados");
        gridView = (GridView) rootView.findViewById(R.id.gridcom);
        adapter = new GridAdapterCom(getContext(),Coms);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(1);

        return rootView;
    }
}
