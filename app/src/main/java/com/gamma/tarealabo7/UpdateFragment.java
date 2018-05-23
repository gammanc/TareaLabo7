package com.gamma.tarealabo7;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.gamma.tarealabo7.Beans.Nota;
import com.gamma.tarealabo7.Entity.DBHelper;


public class UpdateFragment extends Fragment {

    TextInputEditText txtCarnet, txtNota;
    Button btnBuscar, btnActualizar, btnLimpiar;

    public UpdateFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update, container, false);
        findViews(v);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nota p = DBHelper.myDB.findGrade(txtCarnet.getText().toString());
                if(p==null) {
                    Toast.makeText(getContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    clean();
                }else {
                    txtNota.setText(String.valueOf(p.getNota()));
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNota.getText().toString().trim().isEmpty()){
                    double nota = Double.valueOf(txtNota.getText().toString());
                    if(nota < 0 || nota >10){
                        Toast.makeText(getContext(), "Nota no válida", Toast.LENGTH_SHORT).show();
                    } else {
                        DBHelper.myDB.editGrade(txtCarnet.getText().toString(), Double.valueOf(txtNota.getText().toString()));
                        hideKeyboard();
                        clean();
                    }
                } else {
                    Toast.makeText(getContext(), "Ingrese una nota", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
        return v;
    }

    void findViews(View c){
        txtCarnet = c.findViewById(R.id.edt_carnet);
        txtNota = c.findViewById(R.id.edt_nota);
        btnBuscar = c.findViewById(R.id.btn_buscar);
        btnActualizar = c.findViewById(R.id.btn_actualizar);
        btnLimpiar = c.findViewById(R.id.btn_limpiar);
    }

    void clean(){
        txtCarnet.setText("");
        txtNota.setText("");
        txtCarnet.setFocusableInTouchMode(true);
        txtCarnet.requestFocus();
    }

    void hideKeyboard(){
        View view = getActivity().findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
