package com.example.ciudadesrooms.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.repositories.CiudadRepository;
import com.example.ciudadesrooms.repositories.ProvinciaRepository;

import java.util.List;

public class ProvinciaViewModel  extends AndroidViewModel {

    private ProvinciaRepository mRepository;
    private LiveData<List<Provincia>> mAllProvincias;

    public ProvinciaViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ProvinciaRepository(application);
        mAllProvincias = mRepository.getAllProvincias();
    }

    public LiveData<List<Provincia>> obtenerProvincias()
    {
        LiveData<List<Provincia>> mAllProvincias = mRepository.obtenerProvincias();
        return mAllProvincias;
    }

    public boolean insertarProvincia(Provincia p)
    {
        boolean insercionOK = mRepository.insertarProvincia(p);
        return insercionOK;
    }

    public  boolean borrarProvincia(Provincia p)
    {
        boolean borradoOK = mRepository.borrarProvincia(p);
        return borradoOK;
    }

    public  boolean actualizarProvincia(Provincia p)
    {
        boolean actualizacionOK = mRepository.actualizarProvincia(p);
        return actualizacionOK;
    }
}
