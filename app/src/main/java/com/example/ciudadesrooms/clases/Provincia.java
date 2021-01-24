package com.example.ciudadesrooms.clases;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;


@Entity(tableName = "provincias")
public class Provincia implements Serializable {

    // atributos--------------------------------------------------------------------
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idprovincia")
    private int idprovincia ;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    // constructores ------------------------------------------------------------

    public Provincia(@NonNull int idprovincia, @NonNull String nombre) {
        this.idprovincia = idprovincia;
        this.nombre = nombre;
    }

    @Ignore
    public Provincia(@NonNull  String nombre) {
        this.nombre = nombre;
    }

    //---------------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }
    //----------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provincia)) return false;
        Provincia provincia = (Provincia) o;
        return idprovincia == provincia.idprovincia;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idprovincia);
    }

    @Override
    public String toString() {
   /*     return "Provincia{" +
                "idprovincia=" + idprovincia +
                ", nombre='" + nombre + '\'' +
                '}';
     */
        return nombre;
    }
    //-------------------------------------------------------------------------------
}
