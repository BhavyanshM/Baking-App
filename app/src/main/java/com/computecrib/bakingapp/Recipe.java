package com.computecrib.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable{

    private String id;
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private String servings;
    private String image;

    public Recipe(String id, String name, ArrayList<Ingredient> ingredientList, ArrayList<Step> stepList, String servings, String image) {
        this.id = id;
        this.name = name;
        ingredients = ingredientList;
        steps = stepList;
        this.servings = servings;
        this.image = image;
    }

    protected Recipe(Parcel in) {
        id = in.readString();
        name = in.readString();
        servings = in.readString();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredientListString(){
        StringBuilder fullIngredientList = new StringBuilder();
        for(Ingredient i : ingredients){
            fullIngredientList.append(i.getIngredient()).append(", ");
        }
        return fullIngredientList.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(servings);
        parcel.writeString(image);
    }
}
