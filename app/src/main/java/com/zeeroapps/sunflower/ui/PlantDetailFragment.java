package com.zeeroapps.sunflower.ui;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
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
    PlantDetailViewModel mViewModel;

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

        String plantId = PlantDetailFragmentArgs.fromBundle(getArguments()).getPlantId();
        setupViewModel(plantId);

        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.addPlantToGarden();
                Snackbar.make(view, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show();

                //Todo: Hide button after adding plant to garden.
            }
        });

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    private void setupViewModel(String plantId) {
        PlantDetailViewModelFactory factory = InjectorUtils
                .providePlantDetailViewModelFactory(getActivity(), plantId);
        mViewModel = ViewModelProviders.of(this, factory).get(PlantDetailViewModel.class);
        mViewModel.plant.observe(this, new Observer<Plant>() {
            @Override
            public void onChanged(@Nullable Plant plant) {
                Log.e(TAG, "onChanged: "+plant.getName() );
                shareText = plant == null ? "" :
                        String.format(getString(R.string.share_text_plant), plant.getName());
            }
        });
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
