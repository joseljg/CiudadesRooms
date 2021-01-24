package com.example.ciudadesrooms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.viewModel.ProvinciaViewModel;

import java.util.ArrayList;
import java.util.List;


public class ActualizarProvinciaActivity2 extends AppCompatActivity {

    Provincia pseleccionada = null;
    EditText edt_actualizar_ipd = null;
    EditText edt_actualizar_nombrep = null;
    ProvinciaViewModel mProvinciaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_provincia2);
        edt_actualizar_ipd = (EditText) findViewById(R.id.edt_actualizar_idp);
        edt_actualizar_nombrep = (EditText) findViewById(R.id.edt_actualizar_nombrep);
        mProvinciaViewModel = ViewModelProviders.of(this).get(ProvinciaViewModel.class);

        Intent intent = getIntent();
        if(intent != null)
        {
            pseleccionada = (Provincia) intent.getSerializableExtra(ActualizarProvinciaActivity1.EXTRA_OBJETO_PROVINCIA);
            if(pseleccionada!=null)
            {
                edt_actualizar_ipd.setText(String.valueOf(pseleccionada.getIdprovincia()));
                edt_actualizar_ipd.setEnabled(false);
                edt_actualizar_nombrep.setText(pseleccionada.getNombre());
            }
        }
    }

    public void actualizarProvincia(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("actualizar la provincia?");
        //alerta1.setMessage(" no -> cancelar, si-> actualizar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //actualizar provincia
                int idp = Integer.valueOf(String.valueOf(edt_actualizar_ipd.getText()));
                String nombrep = String.valueOf(edt_actualizar_nombrep.getText());
                Provincia p = new Provincia(idp, nombrep);
                boolean actualizarOK = mProvinciaViewModel.actualizarProvincia(p);
                // recargar combo (o desde la base de datos volver a solicitar todo, o quitar solamente el item borrado)
                if(actualizarOK)
                {
                    ActualizarProvinciaActivity1.adapter.clear();
                  //  LiveData<List<Provincia>> provincias = ProvinciaViewModel.obtenerProvincias();
                 //   ActualizarProvinciaActivity1.adapter.addAll(provincias.getValue());
                    mostrarToast("provincia actualizada correctamente");
                }
                else{
                    mostrarToast("la provincia no se pudo actualizar");
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

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}