package com.example.ciudadesrooms.roomDatabase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ciudadesrooms.clases.Ciudad;
import com.example.ciudadesrooms.dao.DaoCiudad;

@Database(entities = {Ciudad.class}, version = 1, exportSchema = false)
public abstract class CiudadRoomDatabase extends RoomDatabase {
    public abstract DaoCiudad ciudadDao();
    private static CiudadRoomDatabase INSTANCE;
    public static CiudadRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CiudadRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CiudadRoomDatabase.class, "ciudad_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
