package com.zeeroapps.sunflower.workers;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeeroapps.sunflower.data.AppDatabase;
import com.zeeroapps.sunflower.data.Plant;

import java.io.InputStream;
import java.util.List;

import androidx.work.Worker;

public class SeedDatabaseWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {

        try {
            InputStream inputStream = getApplicationContext().getAssets().open("plants.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            List<Plant> plantList = new Gson().fromJson(json, new TypeToken<List<Plant>>() {
            }.getType());
            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
            appDatabase.plantDao().insertAll(plantList);
            return Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAILURE;
        }
    }
}
