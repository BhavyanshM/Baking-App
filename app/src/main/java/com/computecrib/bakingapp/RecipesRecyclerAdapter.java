package com.computecrib.bakingapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecipesRecyclerAdapter extends RecyclerView.Adapter<RecipesRecyclerAdapter.Holder> {

    private List<Recipe> recipes;
    private Context context;

    public RecipesRecyclerAdapter(List<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Recipe item = recipes.get(position);
        holder.itemView.setTag(position);
        holder.textViewRecipeName.setText(item.getName());
        holder.textViewRecipeIngredients.setText("Ingredients");
        holder.textViewRecipeServings.setText(item.getServings());
//        Glide.with(context)
//                .load(item.getThumbPath())
//                .thumbnail(0.5f)
//                .into(holder.imageViewThumbnail);
    }


    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewRecipeName;
        private TextView textViewRecipeIngredients;
        private TextView textViewRecipeServings;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewRecipeName= (TextView) itemView.findViewById(R.id.tv_item_recipe_name);
            textViewRecipeIngredients = (TextView) itemView.findViewById(R.id.tv_item_recipe_ingredients);
            textViewRecipeServings = (TextView) itemView.findViewById(R.id.tv_item_recipe_servings);
        }

        @Override
        public void onClick(View view) {
            view.setBackgroundColor(Color.parseColor("#0000ff"));
        }
    }
}