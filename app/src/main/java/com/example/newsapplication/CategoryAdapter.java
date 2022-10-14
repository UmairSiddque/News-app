package com.example.newsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CategoryModel> categoryModelArrayList;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList, CategoryOnClick categoryOnClick) {
        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
        this.categoryOnClick = categoryOnClick;
    }

    private CategoryOnClick categoryOnClick;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList) {
        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorylayout, parent, false);

        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
   CategoryModel categoryModel=categoryModelArrayList.get(position);
   holder.categoryTV.setText(categoryModel.getCategory());
        Picasso.get().load(categoryModel.getCategoryUrlImage()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryOnClick.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public interface CategoryOnClick{
        void onCategoryClick(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTV;
        private ImageView categoryIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV=itemView.findViewById(R.id.categoryTV);
            categoryIV=itemView.findViewById(R.id.categoryIV);
        }
    }
}
