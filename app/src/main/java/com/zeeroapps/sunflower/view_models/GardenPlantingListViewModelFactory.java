package com.zeeroapps.sunflower.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.zeeroapps.sunflower.data.GardenPlantingRepository;

public class GardenPlantingListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    GardenPlantingRepository gardenPlantingRepository;

    public GardenPlantingListViewModelFactory(GardenPlantingRepository repository) {
        this.gardenPlantingRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GardenPlantingListViewModel(gardenPlantingRepository);
    }
}
