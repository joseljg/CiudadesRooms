package com.example.ciudadesrooms.clases;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciudadesrooms.MostrarDetalleCiudadActivity;
import com.example.ciudadesrooms.R;

import java.util.List;


public class CiudadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_OBJETO_CIUDAD =  "com.example.CiudadViewHolder.objeto_ciudad";
    public TextView txt_rv_nombrec = null;
    public TextView txt_rv_habitantes = null;
    public TextView txt_rv_provincia = null;
    final ListaCiudadesAdapter lcAdapter;

    public CiudadViewHolder(@NonNull View itemView, ListaCiudadesAdapter mAdapter) {
        super(itemView);
        txt_rv_nombrec = (TextView)  itemView.findViewById(R.id.txt_rv_nombrec);
        txt_rv_habitantes = (TextView)  itemView.findViewById(R.id.txt_rv_habitantes);
        txt_rv_provincia = (TextView)  itemView.findViewById(R.id.txt_rv_provincia);
        this.lcAdapter = mAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int mPosition = getAdapterPosition();
        // int mPosition = getLayoutPosition();
        List<Ciudad> ciudades = this.lcAdapter.getListaCiudades();
        Ciudad ciudad = ciudades.get(mPosition);
        lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarDetalleCiudadActivity.class);
        intent.putExtra(EXTRA_OBJETO_CIUDAD, ciudad);
        lcAdapter.getC().startActivity(intent);
    }
}
