package com.gamma.tarealabo7;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamma.tarealabo7.Adapter.GradesAdapter;
import com.gamma.tarealabo7.Beans.Nota;
import com.gamma.tarealabo7.Entity.DBHelper;

import java.util.ArrayList;


public class ListFragment extends Fragment {

    ArrayList<Nota> notas;
    GradesAdapter gradesAdapter;
    RecyclerView mRecycler;
    LinearLayoutManager lManager;

    public ListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notas = new ArrayList<>();
        notas = DBHelper.myDB.findGrades();
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        mRecycler = v.findViewById(R.id.main_recycler);
        mRecycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(container.getContext());
        mRecycler.setLayoutManager(lManager);

        gradesAdapter = new GradesAdapter(getActivity(), notas);
        mRecycler.setAdapter(gradesAdapter);

        return v;
    }

}
