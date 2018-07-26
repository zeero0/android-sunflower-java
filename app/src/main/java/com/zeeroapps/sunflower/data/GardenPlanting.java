package com.zeeroapps.sunflower.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Calendar;

@Entity(
        tableName = "garden_plantings",
        foreignKeys = {@ForeignKey(entity = Plant.class, parentColumns = {"id"}, childColumns = {"plant_id"})},
        indices = {@Index("plant_id")}
)
public class GardenPlanting {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long gardenPlantingId = 0;

    @ColumnInfo(name = "plant_id")
    String plantId;

    @ColumnInfo(name = "plant_date")
    Calendar plantDate = Calendar.getInstance();

    @ColumnInfo(name = "last_watering_date")
    Calendar lastWateringDate = Calendar.getInstance();

    public GardenPlanting(String plantId) {
        this.plantId = plantId;
    }
}
