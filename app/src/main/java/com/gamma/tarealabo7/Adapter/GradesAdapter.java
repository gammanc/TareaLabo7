package com.gamma.tarealabo7.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gamma.tarealabo7.Beans.Nota;
import com.gamma.tarealabo7.R;

import java.util.ArrayList;

/**
 * Created by emers on 23/5/2018.
 */

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.GradesViewHolder> {

    Context context;
    ArrayList<Nota> notas;

    public GradesAdapter(Context context, ArrayList<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    public static class GradesViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView txtTitle, txtSub, txtGrade;
        public GradesViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.list_item);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtSub = itemView.findViewById(R.id.txt_subtitle);
            txtGrade = itemView.findViewById(R.id.txt_grade);
        }
    }

    @NonNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new GradesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
        final Nota n = notas.get(position);
        holder.txtTitle.setText(n.getCarnet());
        holder.txtSub.setText(n.getMateria()+" - "+n.getCatedratico());
        holder.txtGrade.setText(String.valueOf(n.getNota()));
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return notas != null ? notas.size() : 0;
    }
}
