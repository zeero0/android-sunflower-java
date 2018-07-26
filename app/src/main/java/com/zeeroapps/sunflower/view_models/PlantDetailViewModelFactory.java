package com.zeeroapps.sunflower.view_models;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.zeeroapps.sunflower.data.GardenPlantingRepository;
import com.zeeroapps.sunflower.data.PlantRepository;

public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;

    public PlantDetailViewModelFactory(PlantRepository plantRepository,
                                       GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId);
    }
}
