package com.zeeroapps.sunflower.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

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
