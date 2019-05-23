package com.zeeroapps.sunflower.view_models;

import androidx.lifecycle.ViewModel;
import android.content.Context;
import androidx.databinding.ObservableField;

import com.zeeroapps.sunflower.R;
import com.zeeroapps.sunflower.data.GardenPlanting;
import com.zeeroapps.sunflower.data.Plant;
import com.zeeroapps.sunflower.data.PlantAndGardenPlantings;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PlantAndGardenPlantingsViewModel extends ViewModel {

    private final ObservableField<String> imageUrl;
    private final ObservableField<String> plantDate;
    private final ObservableField<String> waterDate;
    private Plant plant;
    private GardenPlanting gardenPlanting;

    public PlantAndGardenPlantingsViewModel(Context context, PlantAndGardenPlantings plantings) {
        this.plant = plantings.getPlant();
        this.gardenPlanting = plantings.getGardenPlantings().get(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        String plantDateStr = dateFormat.format(gardenPlanting.plantDate.getTime());
        String waterDateStr = dateFormat.format(gardenPlanting.lastWateringDate.getTime());
        String wateringPrefix = context.getString(R.string.watering_next_prefix, waterDateStr);
        String wateringSuffix = context.getResources().getQuantityString(R.plurals.watering_next_suffix,
                plant.getWateringInterval(), plant.getWateringInterval());

        imageUrl = new ObservableField<String>(plant.getImageUrl());
        plantDate = new ObservableField<String>(
                context.getString(R.string.planted_date, plant.getName(), plantDateStr));
        waterDate = new ObservableField<String>("$wateringPrefix - $wateringSuffix");
        //Todo: fix waterDate field
    }


    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public ObservableField<String> getPlantDate() {
        return plantDate;
    }

    public ObservableField<String> getWaterDate() {
        return waterDate;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public GardenPlanting getGardenPlanting() {
        return gardenPlanting;
    }

    public void setGardenPlanting(GardenPlanting gardenPlanting) {
        this.gardenPlanting = gardenPlanting;
    }
}
