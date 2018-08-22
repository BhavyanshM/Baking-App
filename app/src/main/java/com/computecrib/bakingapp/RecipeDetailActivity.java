package com.computecrib.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_detail_ingredients) TextView mDetailIngredientsTextView;
    @BindView(R.id.rv_recipe_detail_steps) RecyclerView mStepsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);
        ArrayList<Ingredient> ingredients = getIntent().getParcelableArrayListExtra("Ingredients");
        ArrayList<Step> steps = getIntent().getParcelableArrayListExtra("Steps");
        String ingredientsListString = getIntent().getStringExtra("IngredientsListString");
        mDetailIngredientsTextView.setText(ingredientsListString);

        StepsRecyclerAdapter adapter = new StepsRecyclerAdapter(steps, this);
        mStepsRecyclerView.setAdapter(adapter);
        mStepsRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mStepsRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
