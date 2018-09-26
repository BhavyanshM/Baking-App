package com.computecrib.bakingapp.util;

import com.computecrib.bakingapp.Ingredient;
import com.computecrib.bakingapp.Recipe;
import com.computecrib.bakingapp.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class JsonUtilities {

    public static ArrayList<Recipe> getRecipesFromJSON(String jsonString) throws JSONException {
        ArrayList<Recipe> recipes = new ArrayList<>();

        JSONArray jsonRecipes = new JSONArray(jsonString);

        for(int i = 0; i<jsonRecipes.length(); i++){
            JSONObject recipeJSON = jsonRecipes.getJSONObject(i);

            JSONArray jsonIngredients = recipeJSON.getJSONArray("ingredients");
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for(int j = 0; j<jsonIngredients.length(); j++){
                JSONObject ingredientJSON = jsonIngredients.getJSONObject(j);
                ingredients.add(new Ingredient(
                        ingredientJSON.getString("quantity"),
                        ingredientJSON.getString("measure"),
                        ingredientJSON.getString("ingredient")
                ));
            }

            JSONArray jsonSteps = recipeJSON.getJSONArray("steps");
            ArrayList<Step> steps = new ArrayList<>();
            for(int j = 0; j<jsonSteps.length(); j++){
                JSONObject stepJSON = jsonSteps.getJSONObject(j);
                steps.add(new Step(
                        stepJSON.getString("id"),
                        stepJSON.getString("shortDescription"),
                        stepJSON.getString("description"),
                        stepJSON.getString("videoURL"),
                        stepJSON.getString("thumbnailURL")
                ));
            }

            recipes.add(new Recipe(
                    recipeJSON.getString("id"),
                    recipeJSON.getString("name"),
                    ingredients,
                    steps,
                    recipeJSON.getString("servings"),
                    recipeJSON.getString("image")
            ));
        }

        return recipes;
    }
}
