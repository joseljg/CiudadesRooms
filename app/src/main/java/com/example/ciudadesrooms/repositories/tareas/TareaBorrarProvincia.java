package com.example.ciudadesrooms.repositories.tareas;

import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.repositories.ProvinciaRepository;

import java.util.concurrent.Callable;


public class TareaBorrarProvincia implements Callable<Boolean> {
    private Provincia p = null;

    public TareaBorrarProvincia(Provincia p) {
        this.p = p;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            ProvinciaRepository.mProvinciaDao.delete(p);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
