package com.zeeroapps.sunflower.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.zeeroapps.sunflower.workers.SeedDatabaseWorker;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

@Database(entities = {Plant.class, GardenPlanting.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlantDao plantDao();
    public abstract GardenPlantingDao gardenPlantingDao();
    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null)
                    instance = buildDatabase(context);
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "sunflower-db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SeedDatabaseWorker.class).build();
                        WorkManager.getInstance().enqueue(workRequest);
                    }
                }).build();
    }

    public static void destroyDatabase() {
        instance = null;
    }
}
