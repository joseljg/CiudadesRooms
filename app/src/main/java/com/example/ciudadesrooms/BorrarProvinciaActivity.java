package com.example.ciudadesrooms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.viewModel.ProvinciaViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class BorrarProvinciaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner sp_borrarp = null;
    Provincia pseleccionada = null;
    ArrayAdapter<Provincia> adapter = null;
    ArrayList<Provincia> provincias = null;
    ProvinciaViewModel mProvinciaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_provincia);
        sp_borrarp = (Spinner) findViewById(R.id.sp_borrarp);
        sp_borrarp.setOnItemSelectedListener(this);
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

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void asignarAdaptadorSpinnerProvincias(List<Provincia> lasprovincias) {
        adapter = new ArrayAdapter<Provincia>(this , R.layout.item_provincia, lasprovincias);
        sp_borrarp.setAdapter(adapter);
    }

    public void borrarProvincia(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("borrar la provincia?");
        //alerta1.setMessage(" no -> cancelar, si-> guardar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(pseleccionada == null)
                {
                    mostrarToast("selecciona una provincia");
                    return;
                }
                //borrar provincia
                boolean borradoOK = mProvinciaViewModel.borrarProvincia(pseleccionada);
                if(borradoOK)
                {
                    mostrarToast("provincia borrada correctamente");
                }
                else{
                    mostrarToast("la provincia no se pudo borrar");
                }
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta1.show();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Provincia p = (Provincia) sp_borrarp.getItemAtPosition(position);
        pseleccionada = p;
       //  Toast.makeText(this, p.getNombre(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}