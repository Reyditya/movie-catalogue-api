package com.atsdev.moviecatalogueapi.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.atsdev.moviecatalogueapi.R;
import com.atsdev.moviecatalogueapi.adapters.TvAirAdapter;
import com.atsdev.moviecatalogueapi.models.TvAiringData;
import com.atsdev.moviecatalogueapi.viewmodel.TvAiringViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvAirAdapter tvAirAdapter;
    private RecyclerView rvTvAir;
    private ProgressBar progressBar;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TvAiringViewModel tvAiringViewModel;

        rvTvAir = view.findViewById(R.id.rv_tv_airing);
        progressBar = view.findViewById(R.id.progressbar_tvair);
        progressBar.setVisibility(View.VISIBLE);

        showRecyclerTvAiring(view);

        tvAiringViewModel = ViewModelProviders.of(this).get(TvAiringViewModel.class);
        tvAiringViewModel.setTvAir();
        tvAiringViewModel.getTvAiring().observe(this, getTvAring);
    }

    private final Observer<ArrayList<TvAiringData>> getTvAring = new Observer<ArrayList<TvAiringData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvAiringData> tvAiringData) {
            if (tvAiringData != null) {
                tvAirAdapter.setTvAirData(tvAiringData);
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    private void showRecyclerTvAiring(View view) {
        tvAirAdapter = new TvAirAdapter();
        tvAirAdapter.notifyDataSetChanged();
        rvTvAir.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, view.isInLayout()));
        rvTvAir.setAdapter(tvAirAdapter);
    }
}
