package com.zeeroapps.sunflower.view_models;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.zeeroapps.sunflower.data.PlantRepository;

public class PlantListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private PlantRepository plantRepository;

    public PlantListViewModelFactory(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        PlantListViewModel plantListViewModel = new PlantListViewModel(plantRepository);
        return (T) plantListViewModel;
    }
}
