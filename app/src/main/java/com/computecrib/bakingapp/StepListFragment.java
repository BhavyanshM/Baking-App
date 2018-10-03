package com.computecrib.bakingapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends Fragment {

    @BindView(R.id.tv_detail_ingredients)TextView mDetailIngredientsTextView;
    @BindView(R.id.rv_recipe_detail_steps) RecyclerView mStepsRecyclerView;

    public StepListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View stepListFragment = inflater.inflate(R.layout.fragment_step_list, container, false);
        ButterKnife.bind(this, stepListFragment);

        ArrayList<Step> steps = ((RecipeDetailActivity)getActivity()).getSteps();
        String ingredientsListString = ((RecipeDetailActivity)getActivity()).getIngredientsListString();
        mDetailIngredientsTextView.setText(ingredientsListString);

        StepsRecyclerAdapter adapter = new StepsRecyclerAdapter(steps, getActivity());
        mStepsRecyclerView.setAdapter(adapter);
        mStepsRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mStepsRecyclerView.setLayoutManager(linearLayoutManager);
        return stepListFragment;
    }

}
