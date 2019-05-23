package com.zeeroapps.sunflower.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(
        tableName = "garden_plantings",
        foreignKeys = {@ForeignKey(entity = Plant.class, parentColumns = {"id"}, childColumns = {"plant_id"})},
        indices = {@Index("plant_id")}
)
public class GardenPlanting {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long gardenPlantingId = 0;

    @ColumnInfo(name = "plant_id")
    public String plantId;

    @ColumnInfo(name = "plant_date")
    public Calendar plantDate = Calendar.getInstance();

    @ColumnInfo(name = "last_watering_date")
    public Calendar lastWateringDate = Calendar.getInstance();

    public GardenPlanting(String plantId) {
        this.plantId = plantId;
    }
}
