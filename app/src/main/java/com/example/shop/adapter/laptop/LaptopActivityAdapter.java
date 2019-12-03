package com.example.shop.adapter.laptop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;
import com.example.shop.adapter.OnItemClickListener;
import com.example.shop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LaptopActivityAdapter extends RecyclerView.Adapter<LaptopActivityAdapter.LaptopActivityViewHolder> {

    ArrayList<Product> arrayLaptop;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public LaptopActivityAdapter(ArrayList<Product> arrayLaptop) {
        this.arrayLaptop = arrayLaptop;
    }

    @NonNull
    @Override
    public LaptopActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_activity_laptop, parent, false);
        return new LaptopActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopActivityViewHolder holder, int position) {
        Product laptop = arrayLaptop.get(position);
        holder.tvLaptopActivityName.setText(arrayLaptop.get(position).getProductname());
        holder.tvLaptopActivityPrice.setText(arrayLaptop.get(position).getProductprice() + "$");
        holder.tvLaptopActivityDescription.setText(arrayLaptop.get(position).getProductdescription());
        Picasso.get().load(arrayLaptop.get(position).getProductimage()).into(holder.imvLaptopActivity);
    }

    @Override
    public int getItemCount() {
        return arrayLaptop.size();
    }

    public class LaptopActivityViewHolder extends RecyclerView.ViewHolder {

        ImageView imvLaptopActivity;
        TextView tvLaptopActivityName, tvLaptopActivityPrice, tvLaptopActivityDescription;

        public LaptopActivityViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvLaptopActivity = itemView.findViewById(R.id.imvActivityLaptop);
            tvLaptopActivityName = itemView.findViewById(R.id.tvActivityLaptopName);
            tvLaptopActivityPrice = itemView.findViewById(R.id.tvActivityLaptopPrice);
            tvLaptopActivityDescription = itemView.findViewById(R.id.tvActivityLaptopDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }
}
