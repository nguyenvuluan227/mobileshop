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

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductAdapterViewHolder> {

    ArrayList<Product> arrayProduct;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AllProductAdapter(ArrayList<Product> arrayProduct) {
        this.arrayProduct = arrayProduct;
    }

    @NonNull
    @Override
    public AllProductAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_all_product, parent, false);
        return new AllProductAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapterViewHolder holder, int position) {
        Product product = arrayProduct.get(position);
        holder.tvAllProductName.setText(product.getProductname());
        holder.tvAllProductPrice.setText(product.getProductprice() + "$");
        Picasso.get().load(product.getProductimage()).into(holder.imvAllProduct);
    }

    @Override
    public int getItemCount() {
        return arrayProduct.size();
    }

    public class AllProductAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imvAllProduct;
        TextView tvAllProductName, tvAllProductPrice;

        public AllProductAdapterViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvAllProduct = itemView.findViewById(R.id.imvAllProduct);
            tvAllProductName = itemView.findViewById(R.id.tvAllProductName);
            tvAllProductPrice = itemView.findViewById(R.id.tvAllProductPrice);
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
