package com.zeeroapps.sunflower.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlantRepository {
    private PlantDao plantDao;
    private static volatile PlantRepository instance;

    PlantRepository(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public LiveData<List<Plant>> getPlants() {
        return plantDao.getPlants();
    }

    public LiveData<Plant> getPlant(String id) {
        return plantDao.getPlant(id);
    }

    public LiveData<List<Plant>> getPlantsByGrowZoneNumber(int growZoneNo) {
        return plantDao.getPlantsByGrowZoneNumber(growZoneNo);
    }

    public static PlantRepository getInstance(PlantDao plantDao) {
        if (instance == null) {
            synchronized(PlantRepository.class) {
                if (instance == null)
                    instance = new PlantRepository(plantDao);
            }
        }
        return instance;
    }
}
