package com.example.ciudadesrooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.viewModel.CiudadViewModel;
import com.example.ciudadesrooms.viewModel.ProvinciaViewModel;

import java.util.List;


public class ActualizarProvinciaActivity1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_OBJETO_PROVINCIA = "ActualizarProvinciaActivity1.provincia";
    Spinner sp_actualizarp = null;
    static Provincia pseleccionada = null;
    static ArrayAdapter<Provincia> adapter = null;
    LiveData<List<Provincia>> provincias = null;
    ProvinciaViewModel mProvinciaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_provincia1);
        sp_actualizarp = (Spinner) findViewById(R.id.sp_actualizarp);
        sp_actualizarp.setOnItemSelectedListener(this);
        mProvinciaViewModel = ViewModelProviders.of(this).get(ProvinciaViewModel.class);
        //-----------------------------------------------------------
        LiveData<List<Provincia>> provinciasLive = mProvinciaViewModel.obtenerProvincias();
        if(provinciasLive != null) {
            provinciasLive.observe(this, new Observer<List<Provincia>>() {
                @Override
                public void onChanged(@Nullable final List<Provincia> lasprovincias) {
                    asignarAdaptadorSpinnerProvincias(lasprovincias);
                }
            });
        }
        else{
            Toast.makeText(this, "no se han recuperado provincias", Toast.LENGTH_SHORT).show();
        }
    }

    private void asignarAdaptadorSpinnerProvincias(List<Provincia> lasprovincias) {
        adapter = new ArrayAdapter<Provincia>(this , R.layout.item_provincia, lasprovincias);
        sp_actualizarp.setAdapter(adapter);
    }

    public void siguienteactualizarProvincia(View view) {
        if(pseleccionada == null)
        {
            mostrarToast("selecciona una provincia");
            return;
        }

        Intent intent = new Intent(this, ActualizarProvinciaActivity2.class);
        intent.putExtra(EXTRA_OBJETO_PROVINCIA, pseleccionada);
        // Toast.makeText(this,"la provincia seleccionada es " +pseleccionada.getNombre(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Provincia p = (Provincia) sp_actualizarp.getItemAtPosition(position);
        pseleccionada = p;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}