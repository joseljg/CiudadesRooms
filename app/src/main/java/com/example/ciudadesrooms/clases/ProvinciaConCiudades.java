package com.example.ciudadesrooms.clases;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProvinciaConCiudades {
    @Embedded
    public Provincia provincia;
    @Relation(
            parentColumn = "idprovincia", // clave foránea de ciudades
            entityColumn = "idprovincia"  // clave primaria de provincias
    )
    public List<Ciudad> ciudades;

}
