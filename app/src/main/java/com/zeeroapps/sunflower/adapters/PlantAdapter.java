package com.zeeroapps.sunflower.adapters;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zeeroapps.sunflower.R;
import com.zeeroapps.sunflower.data.Plant;
import com.zeeroapps.sunflower.databinding.ListItemPlantBinding;
import com.zeeroapps.sunflower.ui.PlantListFragmentDirections;

import java.util.List;

import androidx.navigation.Navigation;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder>{
    private List<Plant> plantList;
    private LayoutInflater layoutInflater;

    public PlantAdapter(List<Plant> plants) {
        plantList = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemPlantBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_plant, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plant plant = plantList.get(position);
        holder.bind(plant, createClickListener(plant.getPlantId()));
    }

    private View.OnClickListener createClickListener(String plantId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlantListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment direction =
                        PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plantId);
                Navigation.findNavController(view).navigate(direction);

            }
        };
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public void updateItems(List<Plant> plants) {
        final PlantDiffCallback diffCallback = new PlantDiffCallback(this.plantList, plants);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        plantList.clear();
        plantList.addAll(plants);
        diffResult.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ListItemPlantBinding binding;

        ViewHolder(@NonNull ListItemPlantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Plant plant, View.OnClickListener clickListener) {
            binding.setClickListener(clickListener);
            binding.setPlant(plant);
            binding.executePendingBindings();
        }
    }
}
