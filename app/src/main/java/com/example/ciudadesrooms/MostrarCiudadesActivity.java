package com.example.ciudadesrooms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.clases.ListaCiudadesAdapter;
import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.repositories.CiudadRepository;
import com.example.ciudadesrooms.viewModel.CiudadViewModel;
import com.example.ciudadesrooms.viewModel.ProvinciaViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MostrarCiudadesActivity extends AppCompatActivity {

    private static final int PETICION1 = 1;
    private RecyclerView mRecyclerView;
    private ListaCiudadesAdapter mAdapter;
    private List<Ciudad> ciudades;
    private List<Ciudad> ciudadesIniciales;
    CiudadViewModel mCiudadViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ciudades);

        mRecyclerView = findViewById(R.id.rv_ciudades);
        mAdapter = new ListaCiudadesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mCiudadViewModel = ViewModelProviders.of(this).get(CiudadViewModel.class);
        //-----------------------------------------------------------
        LiveData<List<Ciudad>> ciudadesLive = mCiudadViewModel.obtenerciudades();
        if(ciudadesLive != null) {
            ciudadesLive.observe(this, new Observer<List<Ciudad>>() {
                @Override
                public void onChanged(@Nullable final List<Ciudad> lasciudades) {
                    // Update the cached copy of the words in the adapter.
                    ciudades = lasciudades;
                    ciudadesIniciales=lasciudades;
                    mAdapter.setListaCiudades(lasciudades);
                }
            });
        }
        //------------------------------------------------------------
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(ciudades, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT)
                {
                   mostrarToast("ha pulsado izquierda");
                  // Ciudad c = ciudades.get(viewHolder.getAdapterPosition());
                  // CiudadController.borrarCiudad(c);
                    ciudades.remove(viewHolder.getAdapterPosition());
                    mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
                if(direction == ItemTouchHelper.RIGHT)
                {
                    mostrarToast("ha pulsado derecha");
                    ciudades.remove(viewHolder.getAdapterPosition());
                    mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void mostrarToast(String texto) {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1) {
            if (resultCode == RESULT_OK) {
                Ciudad c = (Ciudad) data.getSerializableExtra(NuevaCiudadActivity.EXTRA_OBJETO_CIUDAD);
                ciudades.add(c);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(ciudades.size());
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(ciudades.size());
            }
        }
    }

    public void nuevaCiudad(View view) {
        Intent intent = new Intent(this, NuevaCiudadActivity.class);
        startActivityForResult(intent, PETICION1);
    }

    public void refrescarCiudades(View view) {
          finish();
          startActivity(getIntent());
          // solucion temporal, cierro el activity y lo vuelvo a abrir
    }

}