package com.example.ciudadesrooms.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.dao.DaoCiudad;
import com.example.ciudadesrooms.repositories.tareas.TareaBorrarCiudad;
import com.example.ciudadesrooms.repositories.tareas.TareaInsertarCiudad;
import com.example.ciudadesrooms.repositories.tareas.TareaObtenerCiudades;
import com.example.ciudadesrooms.roomDatabase.CiudadRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CiudadRepository {
    public static DaoCiudad mCiudadDao;
    private LiveData<List<Ciudad>> mAllciudades;

    public CiudadRepository(Application application) {
        CiudadRoomDatabase db = CiudadRoomDatabase.getDatabase(application);
        mCiudadDao = db.ciudadDao();
        mAllciudades= mCiudadDao.cogerTodasCiudades();
    }

    public LiveData<List<Ciudad>> getAllCiudades()
    {
        return mAllciudades;
    }


    public static boolean insertarCiudad(Ciudad c) {
        FutureTask t = new FutureTask(new TareaInsertarCiudad(c));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

    //---------------------------------------------------------------------------

    public static LiveData<List<Ciudad>> obtenerCiudades()
    {
        LiveData<List<Ciudad>> ciudadesDevueltas = null;
        FutureTask t = new FutureTask (new TareaObtenerCiudades());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ciudadesDevueltas= (LiveData<List<Ciudad>>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ciudadesDevueltas;
    }
    //---------------------------------------------------------------------------
    public static boolean   borrarCiudad(Ciudad c) {
        FutureTask t = new FutureTask(new TareaBorrarCiudad(c));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK = false;
        try {
            borradoOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return borradoOK;
        }
    }

}
