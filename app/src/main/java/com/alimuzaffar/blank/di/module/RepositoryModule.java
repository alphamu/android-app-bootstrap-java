package com.alimuzaffar.blank.di.module;

import android.app.Application;
import androidx.room.Room;
import com.alimuzaffar.blank.database.TheDatabase;
import com.alimuzaffar.blank.database.dao.SampleDao;
import com.alimuzaffar.blank.net.ApiInterface;
import com.alimuzaffar.blank.repository.SampleRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Module
public class RepositoryModule {

    // Executor providers a background thread for read/write operations.
    @Provides
    ExecutorService provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    ScheduledExecutorService provideScheduledExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    // provides Database
    @Provides
    @Singleton
    TheDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                TheDatabase.class, "TheDatabase.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    // Create an injector for each DAO
    @Provides
    @Singleton
    SampleDao provideUserDao(TheDatabase database) {
        return database.userDao();
    }


    // provides Repository
    // Create an injector for each Repository
    @Provides
    @Singleton
    SampleRepository provideUserRepository(ApiInterface apiInterface, SampleDao sampleDao, ExecutorService executor) {
        return new SampleRepository(apiInterface, sampleDao, executor);
    }

}
