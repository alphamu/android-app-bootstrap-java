package com.alimuzaffar.blank.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.alimuzaffar.blank.database.converter.DateConverter;
import com.alimuzaffar.blank.database.converter.ListConverter;
import com.alimuzaffar.blank.database.dao.SampleDao;
import com.alimuzaffar.blank.database.entity.Sample;

@Database(entities = {
        Sample.class
}, version = 1)
@TypeConverters({DateConverter.class, ListConverter.class})
public abstract class TheDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile TheDatabase INSTANCE;

    // --- DAO ---
    public abstract SampleDao userDao();

}
