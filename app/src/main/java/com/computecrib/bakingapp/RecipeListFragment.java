package com.computecrib.bakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.computecrib.bakingapp.util.JsonUtilities;
import com.computecrib.bakingapp.util.NetworkUtils;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeListFragment extends Fragment {

    @BindView(R.id.rv_recipes) RecyclerView mRecipesRecyclerView;


    private ArrayList<Recipe> recipes;
    private RecipesRecyclerAdapter adapter;

    public RecipeListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View recipeListFragment = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        recipes = new ArrayList<>();
        ButterKnife.bind(this, recipeListFragment);
        if(NetworkUtils.isConnected((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE))){
            new GetRecipeJSONTask().execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        }else{
            Toast.makeText(getActivity(), R.string.network_unavailable_error, Toast.LENGTH_LONG).show();
        }
        adapter = new RecipesRecyclerAdapter(recipes, getActivity());
        mRecipesRecyclerView.setAdapter(adapter);

        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if(isTablet){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            mRecipesRecyclerView.setLayoutManager(gridLayoutManager);
        }else{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecipesRecyclerView.setLayoutManager(linearLayoutManager);
        }

        return recipeListFragment;
    }

    @SuppressLint("StaticFieldLeak")
    public class GetRecipeJSONTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String fullURL = urls[0];
            String recipeJSONResults = null;
            try {
                recipeJSONResults = NetworkUtils.getBakingRecipesJSONString(fullURL);
            }catch (Exception e){
                e.printStackTrace();
            }
            return recipeJSONResults;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null && !s.equals("")){
                try {
                    recipes = JsonUtilities.getRecipesFromJSON(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String recs = "";
                for(Recipe r : recipes)
                    recs += r.getSteps().get(2).getShortDescription() + "\n";

//                mRestDataTextView.setText(s);

                adapter.setRecipes(recipes);
                adapter.notifyDataSetChanged();
//                if(recyclerSavedState != null){
//                    grid.onRestoreInstanceState(recyclerSavedState);
//                }
            }
        }


    }
}