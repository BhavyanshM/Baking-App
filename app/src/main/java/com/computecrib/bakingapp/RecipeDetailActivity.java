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



    ArrayList<Step> steps;
    String ingredientsListString;

    public ArrayList<Step> getSteps(){
        return steps;
    }

    public String getIngredientsListString(){
        return ingredientsListString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);
        ArrayList<Ingredient> ingredients = getIntent().getParcelableArrayListExtra("Ingredients");
        steps = getIntent().getParcelableArrayListExtra("Steps");
        ingredientsListString = getIntent().getStringExtra("IngredientsListString");

        StepListFragment stepListFragment = new StepListFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.step_list_container, stepListFragment)
                .commit();

        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if(isTablet){
            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, stepDetailFragment)
                    .commit();
        }

    }
}
