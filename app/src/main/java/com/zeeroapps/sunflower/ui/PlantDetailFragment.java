package com.zeeroapps.sunflower.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zeeroapps.sunflower.R;
import com.zeeroapps.sunflower.data.Plant;
import com.zeeroapps.sunflower.databinding.FragmentPlantDetailsBinding;
import com.zeeroapps.sunflower.utils.InjectorUtils;
import com.zeeroapps.sunflower.view_models.PlantDetailViewModel;
import com.zeeroapps.sunflower.view_models.PlantDetailViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlantDetailFragment extends Fragment {

    private static final String TAG = "PlantDetailFragment";
    private static final String ARG_ITEM_ITEM = "item_id";

    String shareText;

    public static PlantDetailFragment newInstance(String plantId) {
        Bundle args = new Bundle();
        args.putString(ARG_ITEM_ITEM, plantId);
        PlantDetailFragment fragment = new PlantDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlantDetailsBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_plant_details, container, false);
        View view = binding.getRoot();

        String plantId = PlantDetailFragmentArgs.fromBundle(getArguments()).getPlantId();

        PlantDetailViewModelFactory factory = InjectorUtils.providePlantDetailViewModelFactory(
                getActivity(), plantId);
        PlantDetailViewModel viewModel = ViewModelProviders.of(this, factory).get(PlantDetailViewModel.class);
        viewModel.plant.observe(this, new Observer<Plant>() {
            @Override
            public void onChanged(@Nullable Plant plant) {
                Log.e(TAG, "onChanged: "+plant.getName() );
                shareText = plant == null ? "" :
                        String.format(getString(R.string.share_text_plant), plant.getName());

//                binding.setPlant(plant);
            }
        });

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        setHasOptionsMenu(true);
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
