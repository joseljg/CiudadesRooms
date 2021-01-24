package com.example.ciudadesrooms.repositories.tareas;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.repositories.CiudadRepository;

import java.util.concurrent.Callable;


public class TareaInsertarCiudad implements Callable<Boolean> {
    private Ciudad c = null;

    public TareaInsertarCiudad(Ciudad c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            CiudadRepository.mCiudadDao.insert(c);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
