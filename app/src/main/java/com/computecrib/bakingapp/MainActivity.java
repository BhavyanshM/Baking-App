package com.computecrib.bakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.computecrib.bakingapp.util.JsonUtilities;
import com.computecrib.bakingapp.util.NetworkUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.tv_rest_data) TextView mRestDataTextView;
    @BindView(R.id.rv_recipes) RecyclerView mRecipesRecyclerView;

    private ArrayList<Recipe> recipes;
    private RecipesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipes = new ArrayList<>();
        ButterKnife.bind(this);
        if(NetworkUtils.isConnected((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))){
            new GetRecipeJSONTask().execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        }else{
            Toast.makeText(this, R.string.network_unavailable_error, Toast.LENGTH_LONG).show();
        }
        adapter = new RecipesRecyclerAdapter(recipes, this);
        mRecipesRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecipesRecyclerView.setLayoutManager(linearLayoutManager);

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
