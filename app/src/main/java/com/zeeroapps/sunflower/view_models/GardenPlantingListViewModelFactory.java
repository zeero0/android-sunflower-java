package com.zeeroapps.sunflower.view_models;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

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
