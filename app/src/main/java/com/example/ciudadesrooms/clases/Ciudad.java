package com.example.ciudadesrooms.clases;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ciudades")
public class Ciudad implements Serializable {

    // atributos  -------------------------------------------------------------------------------------------
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idciudad")
    private int idciudad;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "habitantes")
    private int habitantes;

    @NonNull
    @ColumnInfo(name = "idprovincia")
    private int idprovincia;

    //constructores --------------------------------------------------------------------------------------------

  public Ciudad(@NonNull int idciudad, @NonNull String nombre, int habitantes, @NonNull int idprovincia) {
        this.idciudad = idciudad;
        this.nombre = nombre;
        this.habitantes = habitantes;
        this.idprovincia = idprovincia;
    }

    @Ignore
    public Ciudad(@NonNull String nombre, int habitantes, int idprovincia) {
        this.nombre = nombre;
        this.habitantes = habitantes;
        this.idprovincia = idprovincia;
    }
    //---------------------------------------------------------------------------------------------------------

    public int getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(int idciudad) {
        this.idciudad = idciudad;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    //----------------------------------------------------------------------
    @Override
    public String toString() {
        return "Ciudad [idciudad=" + idciudad + ", nombre=" + nombre + ", habitantes=" + habitantes + ", idprovincia="
                + idprovincia + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idciudad;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ciudad other = (Ciudad) obj;
        if (idciudad != other.idciudad)
            return false;
        return true;
    }
   //---------------------------------------------------------------------------
}
