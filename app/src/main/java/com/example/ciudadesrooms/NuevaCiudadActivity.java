package com.example.ciudadesrooms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.viewModel.CiudadViewModel;
import com.example.ciudadesrooms.viewModel.ProvinciaViewModel;

import java.util.ArrayList;
import java.util.List;


public class NuevaCiudadActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_OBJETO_CIUDAD = "es.joseljg.NuevaCiudadActivity.ciudad";
    Spinner sp_nuevac_provincia = null;
    Provincia pseleccionada = null;
    ArrayAdapter<Provincia> adapter = null;
    EditText edt_nuevac_nombre = null;
    EditText edt_nuevac_habitantes = null;
    EditText edt_nuevac_idc = null;
    CiudadViewModel mCiudadViewModel;
    ProvinciaViewModel mProvinciaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_ciudad);
        edt_nuevac_nombre = findViewById(R.id.edt_nuevac_nombre);
        edt_nuevac_habitantes = findViewById(R.id.edt_nuevac_habitantes);
        edt_nuevac_idc = findViewById(R.id.edt_nuevac_idc);
        sp_nuevac_provincia = (Spinner) findViewById(R.id.sp_nuevac_provincia);
        sp_nuevac_provincia.setOnItemSelectedListener(this);
        mProvinciaViewModel = ViewModelProviders.of(this).get(ProvinciaViewModel.class);
        mCiudadViewModel = ViewModelProviders.of(this).get(CiudadViewModel.class);
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
        sp_nuevac_provincia.setAdapter(adapter);
    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void insertarCiudad(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("quieres guardar la ciudad?");
        //alerta1.setMessage(" no -> cancelar, si-> guardar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(pseleccionada == null)
                {
                    mostrarToast("selecciona una provincia");
                    return;
                }

                Ciudad c = null;
                try{
                    String nombre = String.valueOf(edt_nuevac_nombre.getText());
                    int habitantes = Integer.valueOf(String.valueOf(edt_nuevac_habitantes.getText()));
                    int idc = Integer.valueOf(String.valueOf(edt_nuevac_idc.getText()));
                    c = new Ciudad(idc, nombre, habitantes, pseleccionada.getIdprovincia());
                    boolean insercionOK = mCiudadViewModel.insertarCiudad(c);
                    if(insercionOK)
                    {
                        mostrarToast("ciudad guardada correctamente");
                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_OBJETO_CIUDAD, c);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else
                    {
                        mostrarToast("no se pudo guardar la ciudad");
                    }
                }catch (Exception e)
                {
                    mostrarToast("error, revisa los datos introducidos");
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
       Provincia p = (Provincia) sp_nuevac_provincia.getItemAtPosition(position);
       pseleccionada = p;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}