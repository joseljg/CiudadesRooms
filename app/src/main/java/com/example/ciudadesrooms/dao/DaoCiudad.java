package com.example.ciudadesrooms.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ciudadesrooms.clases.Ciudad;

import java.util.List;

@Dao
public interface DaoCiudad {
    @Insert
    void insert(Ciudad ciudad);
/*
    @Insert
    void insert(List<Ciudad> ciudades);
*/
    @Delete
    void delete(Ciudad ciudad);
/*
    @Delete
    void delete(List<Ciudad> ciudades);
*/
    @Query("DELETE FROM ciudades")
    void deleteAll();

    @Update
    void update(Ciudad ciudad);
/*
    @Update
    void update(List<Ciudad> ciudades);
*/
    @Query("SELECT * from ciudades ORDER BY nombre ASC")
    LiveData<List<Ciudad>> cogerTodasCiudades();

    @Query("SELECT * FROM ciudades WHERE nombre like :nombrec")
    LiveData<Ciudad> CogerCiudad(String nombrec);

}
