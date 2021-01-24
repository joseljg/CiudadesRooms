package com.example.ciudadesrooms.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ciudadesrooms.clases.Provincia;

import java.util.List;

@Dao
public interface DaoProvincia {
    @Insert
    void insert(Provincia provincia);
/*
    @Insert
    void insert(List<Provincia> provincias);
*/
    @Delete
    void delete(Provincia provincia);

/*
    @Delete
    void delete(List<Provincia> provincias);
*/

    @Query("DELETE FROM provincias")
    void deleteAll();

    @Update
    void update(Provincia provincia);

    /*
    @Update
    void update(List<Provincia> provincias);
    */

    @Query("SELECT * from provincias ORDER BY nombre ASC")
    LiveData<List<Provincia>> cogerTodasProvincias();

    @Query("SELECT * FROM provincias WHERE nombre like :nombrep")
    LiveData<Provincia> CogerProvincia(String nombrep);

}
