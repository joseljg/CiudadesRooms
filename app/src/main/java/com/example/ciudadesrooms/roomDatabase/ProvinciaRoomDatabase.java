package com.example.ciudadesrooms.roomDatabase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ciudadesrooms.clases.Provincia;
import com.example.ciudadesrooms.dao.DaoProvincia;

@Database(entities = {Provincia.class}, version = 1, exportSchema = false)
public abstract class ProvinciaRoomDatabase extends RoomDatabase {
    public abstract DaoProvincia provinciaDao();
    private static ProvinciaRoomDatabase INSTANCE;

    public static ProvinciaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProvinciaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProvinciaRoomDatabase.class, "provincia_database")
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
