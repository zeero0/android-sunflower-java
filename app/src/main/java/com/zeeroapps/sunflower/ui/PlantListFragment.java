package com.zeeroapps.sunflower.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zeeroapps.sunflower.R;
import com.zeeroapps.sunflower.adapters.PlantAdapter;
import com.zeeroapps.sunflower.data.Plant;
import com.zeeroapps.sunflower.utils.InjectorUtils;
import com.zeeroapps.sunflower.view_models.PlantListViewModel;
import com.zeeroapps.sunflower.view_models.PlantListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class PlantListFragment extends Fragment {

    private List<Plant> plantsList = new ArrayList<>();
    private PlantListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_list, container, false);

        PlantListViewModelFactory factory = InjectorUtils.providePlantListViewModelFactory(getActivity());
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);

        PlantAdapter adapter = new PlantAdapter(plantsList);
        RecyclerView recyclerView = view.findViewById(R.id.plant_list);
        recyclerView.setAdapter(adapter);
        subscribeUI(adapter);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void subscribeUI(PlantAdapter adapter) {
        viewModel.getPlants().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(@Nullable List<Plant> plants) {
                adapter.updateItems(plants);
            }
        });
    }

}
