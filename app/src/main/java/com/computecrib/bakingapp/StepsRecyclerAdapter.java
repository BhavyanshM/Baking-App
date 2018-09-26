package com.computecrib.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class StepsRecyclerAdapter extends RecyclerView.Adapter<StepsRecyclerAdapter.Holder> {

    private List<Step> steps;
    private Context context;

    public StepsRecyclerAdapter(List<Step> steps, Context context) {
        this.steps = steps;
        this.context = context;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Step item = steps.get(position);
        holder.itemView.setTag(position);
        holder.textViewStepShortDescription.setText(item.getShortDescription());
        holder.textViewStepDescription.setText(item.getDescription());
//        Glide.with(context)
//                .load(item.getThumbPath())
//                .thumbnail(0.5f)
//                .into(holder.imageViewThumbnail);
    }


    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewStepShortDescription;
        private TextView textViewStepDescription;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewStepShortDescription = itemView.findViewById(R.id.tv_item_step_short_description);
            textViewStepDescription = itemView.findViewById(R.id.tv_item_step_description);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, StepDetailActivity.class);
//            Step intentStep = steps.get(getAdapterPosition());
//            intent.putExtra("IngredientsListString",intentStep.getIngredientListString() );
            context.startActivity(intent);
        }
    }
}