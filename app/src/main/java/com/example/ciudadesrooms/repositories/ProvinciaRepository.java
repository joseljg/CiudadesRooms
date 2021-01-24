package com.example.ciudadesrooms.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.dao.DaoProvincia;
import com.example.ciudadesrooms.repositories.tareas.TareaActualizarProvincia;
import com.example.ciudadesrooms.repositories.tareas.TareaBorrarProvincia;
import com.example.ciudadesrooms.repositories.tareas.TareaInsertarProvincia;
import com.example.ciudadesrooms.repositories.tareas.TareaObtenerProvincias;
import com.example.ciudadesrooms.roomDatabase.CiudadRoomDatabase;
import com.example.ciudadesrooms.roomDatabase.ProvinciaRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


public class ProvinciaRepository {
    public static DaoProvincia mProvinciaDao;
    private LiveData<List<Provincia>> mAllprovincias;

    public ProvinciaRepository(Application application) {
        ProvinciaRoomDatabase db = ProvinciaRoomDatabase.getDatabase(application);
        mProvinciaDao = db.provinciaDao();
        mAllprovincias= mProvinciaDao.cogerTodasProvincias();
    }
    public LiveData<List<Provincia>> getAllProvincias() {

        return mAllprovincias;
    }

    public static boolean insertarProvincia(Provincia p) {
        FutureTask t = new FutureTask(new TareaInsertarProvincia(p));
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

    public static LiveData<List<Provincia>> obtenerProvincias()
    {
        LiveData<List<Provincia>> provinciasDevueltas = null;
        FutureTask t = new FutureTask (new TareaObtenerProvincias());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            provinciasDevueltas= (LiveData<List<Provincia>>)t.get();
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
        return provinciasDevueltas;
    }
    //---------------------------------------------------------------------------
    public static boolean   borrarProvincia(Provincia p) {
        FutureTask t = new FutureTask(new TareaBorrarProvincia(p));
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

    public static boolean actualizarProvincia(Provincia p) {
        FutureTask t = new FutureTask(new TareaActualizarProvincia(p));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK = false;
        try {
            actualizadoOK = (boolean) t.get();
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
            return actualizadoOK;
        }
    }
}
