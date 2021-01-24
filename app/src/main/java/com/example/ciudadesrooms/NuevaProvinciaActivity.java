package com.example.ciudadesrooms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.viewModel.ProvinciaViewModel;


public class NuevaProvinciaActivity extends AppCompatActivity {

    EditText edt_nombrep = null;
    EditText edt_idp = null;
    ProvinciaViewModel mProvinciaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_provincia);
        edt_nombrep = findViewById(R.id.edt_nombrep);
        edt_idp = findViewById(R.id.edt_idp);
        mProvinciaViewModel = ViewModelProviders.of(this).get(ProvinciaViewModel.class);
    }

    public void insertarProvincia(View view) {
        String nombrep = String.valueOf(edt_nombrep.getText());
        int id = Integer.valueOf(String.valueOf(edt_idp.getText()));
        if(nombrep.isEmpty())
        {
            edt_nombrep.setError("escribe algo en el nombre de la provincia");
            return;
        }
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("guardar la provincia?");
        //alerta1.setMessage(" no -> cancelar, si-> guardar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Provincia p = new Provincia(id, nombrep);
                boolean insercionOK = mProvinciaViewModel.insertarProvincia(p);
                mostrarToast(insercionOK);
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta1.show();
    }

    public void mostrarToast(boolean insercionOK)
    {
        if(insercionOK)
        {
            Toast.makeText(this,"provincia guardada correctamente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"No se pudo guardar la provincia", Toast.LENGTH_SHORT).show();
        }
    }
}