package com.zeeroapps.sunflower.data;

import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class GardenPlantingRepository {

    static GardenPlantingRepository instance;
    GardenPlantingDao gardenPlantingDao;

    public GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    public static GardenPlantingRepository getInstance(GardenPlantingDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (GardenPlanting.class) {
                if (instance == null) {
                    instance = new GardenPlantingRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }

    public void createGardenPlanting(String plantId) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                GardenPlanting gardenPlanting = new GardenPlanting(plantId);
                gardenPlantingDao.insertGardenPlanting(gardenPlanting);
            }
        });

    }


    public LiveData<GardenPlanting> getGardenPlantingForPlant(String plantId) {
        return gardenPlantingDao.getGardenPlantingForPlant(plantId);
    }


    public LiveData<List<GardenPlanting>> getGardenPlants() {
        return gardenPlantingDao.getGardenPlantings();
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings(){
        return gardenPlantingDao.getPlantAndGardenPlantings();
    }


//    private ExecutorService IO_EXECUTOR = Executors.newSingleThreadExecutor();
//
//    void runOnIoThread() {
//        IO_EXECUTOR.execute();
//    }



}
