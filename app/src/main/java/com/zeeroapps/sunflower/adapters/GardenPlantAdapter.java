package com.zeeroapps.sunflower.adapters;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zeeroapps.sunflower.R;
import com.zeeroapps.sunflower.data.PlantAndGardenPlantings;
import com.zeeroapps.sunflower.databinding.ListItemGardenPlantingBinding;
import com.zeeroapps.sunflower.view_models.PlantAndGardenPlantingsViewModel;

import java.util.List;

public class GardenPlantAdapter extends RecyclerView.Adapter<GardenPlantAdapter.ViewHolder> {

    List<PlantAndGardenPlantings> plantingsList;

    public GardenPlantAdapter(List<PlantAndGardenPlantings> plantingsList) {
        this.plantingsList = plantingsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        ListItemGardenPlantingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_item_garden_planting, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(plantingsList.get(position));
    }

    @Override
    public int getItemCount() {
        return plantingsList.size();
    }

    public void updateList(List<PlantAndGardenPlantings> plantings) {
        GardenPlantDiffCallback diffCallback = new GardenPlantDiffCallback(this.plantingsList, plantings);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.plantingsList.clear();
        this.plantingsList.addAll(plantings);
        diffResult.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemGardenPlantingBinding binding;

        ViewHolder(@NonNull ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PlantAndGardenPlantings plantAndGardenPlantings) {
            binding.setViewModel(new PlantAndGardenPlantingsViewModel(
                    binding.getRoot().getContext(), plantAndGardenPlantings));
            binding.executePendingBindings();
        }
    }
}

