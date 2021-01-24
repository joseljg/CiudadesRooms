package com.example.ciudadesrooms.repositories.tareas;

import androidx.lifecycle.LiveData;

import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.repositories.ProvinciaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class TareaObtenerProvincias implements Callable <LiveData<List<Provincia>>> {
    @Override
    public LiveData<List<Provincia>> call() throws Exception {
        try{
            LiveData<List<Provincia>> provincias = ProvinciaRepository.mProvinciaDao.cogerTodasProvincias();
            return provincias;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
