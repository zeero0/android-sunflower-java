package com.zeeroapps.sunflower.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.zeeroapps.sunflower.data.Plant;
import com.zeeroapps.sunflower.data.PlantRepository;

import java.util.List;

public class PlantListViewModel extends ViewModel {
    private PlantRepository plantRepository;
    private int NO_GROW_ZONE = -1;
    private MutableLiveData<Integer> growZoneNumber = new MutableLiveData<>();
    private MediatorLiveData<List<Plant>> plantList = new MediatorLiveData<>();

    PlantListViewModel(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
        growZoneNumber.setValue(NO_GROW_ZONE);

        LiveData<List<Plant>> livePlantList = Transformations.switchMap(growZoneNumber, (gz_no) -> {
            if (gz_no == NO_GROW_ZONE) {
                return plantRepository.getPlants();
            } else {
                return plantRepository.getPlantsByGrowZoneNumber(gz_no);
            }
        });

        plantList.addSource(livePlantList, plants -> plantList.setValue(plants));
    }


    public MediatorLiveData<List<Plant>> getPlants() {
        return plantList;
    }

    public void setGrowZoneNumber(int no) {
        growZoneNumber.setValue(no);
    }

    public void clearGrowZoneNumber() {
        growZoneNumber.setValue(NO_GROW_ZONE);
    }

    public boolean isFiltered() {
        return growZoneNumber.getValue() != NO_GROW_ZONE;
    }

}
