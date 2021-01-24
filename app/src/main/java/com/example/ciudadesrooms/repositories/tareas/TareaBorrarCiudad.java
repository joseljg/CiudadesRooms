package com.example.ciudadesrooms.repositories.tareas;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.repositories.CiudadRepository;

import java.util.concurrent.Callable;


public class TareaBorrarCiudad implements Callable<Boolean> {
    private Ciudad c = null;

    public TareaBorrarCiudad(Ciudad c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            CiudadRepository.mCiudadDao.delete(c);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
