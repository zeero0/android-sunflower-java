package com.zeeroapps.sunflower.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class PlantAndGardenPlantings {
    @Embedded
    Plant plant;

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    List<GardenPlanting> gardenPlantings = new ArrayList<>();
}
