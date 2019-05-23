package com.zeeroapps.sunflower.adapters;

import androidx.recyclerview.widget.DiffUtil;

import com.zeeroapps.sunflower.data.PlantAndGardenPlantings;

import java.util.List;

public class GardenPlantDiffCallback extends DiffUtil.Callback {

    private List<PlantAndGardenPlantings> oldList;
    private List<PlantAndGardenPlantings> newList;

    public GardenPlantDiffCallback(List<PlantAndGardenPlantings> oldList, List<PlantAndGardenPlantings> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return this.oldList.size();
    }

    @Override
    public int getNewListSize() {
        return this.newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        PlantAndGardenPlantings oldPlant = oldList.get(oldItemPosition);
        PlantAndGardenPlantings newPlant = newList.get(newItemPosition);
        return oldPlant.getPlant().getPlantId() == newPlant.getPlant().getPlantId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PlantAndGardenPlantings oldPlant = oldList.get(oldItemPosition);
        PlantAndGardenPlantings newPlant = newList.get(newItemPosition);
        return oldPlant.equals(newPlant);
    }

}
