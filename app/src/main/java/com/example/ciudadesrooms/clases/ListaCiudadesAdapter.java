package com.example.ciudadesrooms.clases;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciudadesrooms.R;

import java.util.ArrayList;
import java.util.List;


public class ListaCiudadesAdapter extends RecyclerView.Adapter<CiudadViewHolder> {

    private Context c;
    private List<Ciudad> listaCiudades;
    private LayoutInflater mInflater;

    public ListaCiudadesAdapter(Context c) {
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public ListaCiudadesAdapter(Context c, List<Ciudad> listaCiudades) {
        this.c = c;
        this.listaCiudades = listaCiudades;
        mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public CiudadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_ciudad, parent, false);
        return new CiudadViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CiudadViewHolder holder, int position) {
        if(listaCiudades!=null) {
            Ciudad ciudadActual = listaCiudades.get(position);
            holder.txt_rv_nombrec.setText("Ciudad: " + ciudadActual.getNombre());
            holder.txt_rv_habitantes.setText(String.valueOf("habitantes: " + ciudadActual.getHabitantes()));
            holder.txt_rv_provincia.setText(String.valueOf("idprovincia: " + ciudadActual.getIdprovincia()));
        }
    }

    @Override
    public int getItemCount() {
        if (listaCiudades != null)
            return listaCiudades.size();
        else return 0;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
        if(this.listaCiudades == null)
        {
           Log.i("ciudades","la lista ciudades es nulo");
        }
        else{
            for(Ciudad c:listaCiudades)
            {
                Log.i("ciudades","ciudad -> " + c.getNombre());
            }
        }
        notifyDataSetChanged();
    }
}