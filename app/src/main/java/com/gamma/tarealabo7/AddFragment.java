package com.gamma.tarealabo7;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.gamma.tarealabo7.Beans.Nota;
import com.gamma.tarealabo7.Entity.DBHelper;


public class AddFragment extends Fragment {

    TextInputEditText txtCarnet, txtMateria, txtCated, txtNota;
    Button btnIngresar, btnLimpiar;

    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        findViews(v);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = DBHelper.myDB.add(
                        new Nota(txtCarnet.getText().toString(),
                                txtMateria.getText().toString(),
                                txtCated.getText().toString(),
                                Double.valueOf(txtNota.getText().toString())) );
                if (flag){
                    Toast.makeText(getContext(),"Agregado con exito", Toast.LENGTH_SHORT).show();
                    clean();
                    hideKeyboard();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void clean(){
        txtCarnet.setText("");
        txtMateria.setText("");
        txtCated.setText("");
        txtNota.setText("");
        txtCarnet.setFocusableInTouchMode(true);
        txtCarnet.requestFocus();
    }
    void findViews(View c){
        txtCarnet = c.findViewById(R.id.edt_carnet);
        txtMateria = c.findViewById(R.id.edt_materia);
        txtCated = c.findViewById(R.id.edt_cated);
        txtNota = c.findViewById(R.id.edt_nota);
        btnIngresar = c.findViewById(R.id.btn_guardar);
        btnLimpiar = c.findViewById(R.id.btn_limpiar);
    }

    void hideKeyboard(){
        View view = getActivity().findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
