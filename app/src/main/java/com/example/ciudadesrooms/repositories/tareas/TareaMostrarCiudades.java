package com.example.ciudadesrooms.repositories.tareas;

import androidx.lifecycle.LiveData;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.repositories.CiudadRepository;

import java.util.List;
import java.util.concurrent.Callable;


public class TareaMostrarCiudades  implements Callable <LiveData<List<Ciudad>>> {
    @Override
    public LiveData<List<Ciudad>> call() throws Exception {
        try{
            LiveData<List<Ciudad>> ciudades = CiudadRepository.mCiudadDao.cogerTodasCiudades();
            return ciudades;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
