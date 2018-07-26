package com.zeeroapps.sunflower.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.zeeroapps.sunflower.data.GardenPlanting;
import com.zeeroapps.sunflower.data.GardenPlantingRepository;
import com.zeeroapps.sunflower.data.PlantAndGardenPlantings;

import java.util.List;

public class GardenPlantingListViewModel extends ViewModel {

    private LiveData<List<GardenPlanting>> gardenPlantings;
    GardenPlantingRepository gardenPlantingRepository;

    public GardenPlantingListViewModel(GardenPlantingRepository gardenPlantingRepository) {
        this.gardenPlantingRepository = gardenPlantingRepository;
        gardenPlantings = gardenPlantingRepository.getGardenPlants();
    }

    LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings =
            Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings(), plantings -> {
                return plantings;
    });

}
