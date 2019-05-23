package com.zeeroapps.sunflower.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class PlantAndGardenPlantings {
    @Embedded
    Plant plant;

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    List<GardenPlanting> gardenPlantings = new ArrayList<>();

    public Plant getPlant() {
        return plant;
    }

    public List<GardenPlanting> getGardenPlantings(){
        return gardenPlantings;
    }
}
