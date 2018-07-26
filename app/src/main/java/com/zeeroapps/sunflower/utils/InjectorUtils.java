package com.zeeroapps.sunflower.utils;

import android.content.Context;

import com.zeeroapps.sunflower.data.AppDatabase;
import com.zeeroapps.sunflower.data.GardenPlantingRepository;
import com.zeeroapps.sunflower.data.PlantRepository;
import com.zeeroapps.sunflower.view_models.GardenPlantingListViewModelFactory;
import com.zeeroapps.sunflower.view_models.PlantDetailViewModelFactory;
import com.zeeroapps.sunflower.view_models.PlantListViewModel;
import com.zeeroapps.sunflower.view_models.PlantListViewModelFactory;

public class InjectorUtils {


    private static PlantRepository getPlantRepository(Context context) {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao());
    }

    private static GardenPlantingRepository getGardenPlantingRepository(Context context) {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context).gardenPlantingDao());
    }

    public static PlantListViewModelFactory providePlantListViewModelFactory(Context context) {
        PlantRepository repository = getPlantRepository(context);
        PlantListViewModelFactory vmFactory = new PlantListViewModelFactory(repository);
        return vmFactory;
    }

    public static GardenPlantingListViewModelFactory provideGardenPlantListViewModelFactory(Context context) {
        GardenPlantingRepository gardenPlantingRepository = getGardenPlantingRepository(context);
        return new GardenPlantingListViewModelFactory(gardenPlantingRepository);
    }

    public static PlantDetailViewModelFactory providePlantDetailViewModelFactory(Context context, String plantId) {
        return new PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context), plantId);
    }
}
