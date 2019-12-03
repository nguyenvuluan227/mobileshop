package com.example.shop.adapter.product;

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

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder> {

    ArrayList<Product> arrayLaptop;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public LaptopAdapter(ArrayList<Product> arrayLaptop) {
        this.arrayLaptop = arrayLaptop;
    }

    @NonNull
    @Override
    public LaptopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_laptop, parent, false);
        return new LaptopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopViewHolder holder, int position) {
        Product laptop = arrayLaptop.get(position);
        holder.tvLaptopName.setText(laptop.getProductname());
        holder.tvLaptopPrice.setText(laptop.getProductprice() + "$");
        Picasso.get().load(laptop.getProductimage()).into(holder.imvLaptop);
    }

    @Override
    public int getItemCount() {
        return arrayLaptop.size();
    }

    public class LaptopViewHolder extends RecyclerView.ViewHolder {

        ImageView imvLaptop;
        TextView tvLaptopName, tvLaptopPrice;

        public LaptopViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvLaptop = itemView.findViewById(R.id.imvLaptop);
            tvLaptopName = itemView.findViewById(R.id.tvLaptopName);
            tvLaptopPrice = itemView.findViewById(R.id.tvLaptopPrice);
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
