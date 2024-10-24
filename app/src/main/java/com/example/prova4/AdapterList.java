package com.example.prova4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Ranking> list = new ArrayList<>();
    private Context context;
    private int posicao = 0;

    public AdapterList(List<Ranking> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        posicao++;

        Ranking ranking = list.get(position);

        TextView txtNome = holder.itemView.findViewById(R.id.txtNome);
        TextView txtPosicao = holder.itemView.findViewById(R.id.txtPosicao);
        TextView txtPontos = holder.itemView.findViewById(R.id.txtPontos);

        txtNome.setText(ranking.getAluno());
        txtPosicao.setText(String.valueOf(posicao + " -"));
        txtPontos.setText(String.valueOf(ranking.getPontos() + "pts"));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private Bitmap decodificar(String base) {

        String[] divide = base.split(",", 2);

        if (divide.length > 1) {

            byte[] decodifica = Base64.decode(divide[1], divide[1].length());


        } else {

            return null;

        }

    }

}
