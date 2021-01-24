package com.example.ciudadesrooms.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.repositories.CiudadRepository;

import java.util.List;

public class CiudadViewModel extends AndroidViewModel {

    private CiudadRepository mRepository;
    private LiveData<List<Ciudad>> mAllCiudades;

    public CiudadViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CiudadRepository(application);
        mAllCiudades = mRepository.getAllCiudades();
    }

    public LiveData<List<Ciudad>> obtenerciudades()
    {
        LiveData<List<Ciudad>> mAllCiudades = mRepository.getAllCiudades();
        return mAllCiudades;
    }

    public boolean insertarCiudad(Ciudad c)
    {
        boolean insercionOK = mRepository.insertarCiudad(c);
        return insercionOK;
    }

    public boolean borrarProvincia(Ciudad c)
    {
        boolean borradoOK = mRepository.borrarCiudad(c);
        return borradoOK;
    }

}
