package com.example.shop.adapter.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.CartActivity;
import com.example.shop.MainActivity;
import com.example.shop.R;
import com.example.shop.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    ArrayList<Cart> arrayCart;

    public CartAdapter(ArrayList<Cart> arrayCart) {
        this.arrayCart = arrayCart;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        Cart cart = arrayCart.get(position);
        holder.tvproductName.setText(cart.getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvproductPrice.setText(decimalFormat.format(cart.getProductPrice()) + "$");
        holder.tvNumber.setText(cart.getProductNumber() + "");
        Picasso.get().load(cart.getProductImage()).into(holder.imvCartItem);
        int numb = Integer.parseInt(holder.tvNumber.getText().toString());
        if (numb >= 10) {
            holder.btnAdd.setVisibility(View.INVISIBLE);
            holder.btnRemove.setVisibility(View.VISIBLE);
        } else if (numb <= 1) {
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.btnRemove.setVisibility(View.INVISIBLE);
        }
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNumb = Integer.parseInt(holder.tvNumber.getText().toString()) + 1;
                int recentNumb = MainActivity.arrayCart.get(position).getProductNumber();
                long recentPrice = MainActivity.arrayCart.get(position).getProductPrice();
                MainActivity.arrayCart.get(position).setProductNumber(newNumb);
                long newPrice = (recentPrice * newNumb) / recentNumb;
                MainActivity.arrayCart.get(position).setProductPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvproductPrice.setText(decimalFormat.format(newPrice) + "$");
                CartActivity.eventUtil();
                if (newNumb > 9) {
                    holder.btnAdd.setVisibility(View.INVISIBLE);
                    holder.btnRemove.setVisibility(View.VISIBLE);
                    holder.tvNumber.setText(String.valueOf(newNumb));
                } else {
                    holder.btnAdd.setVisibility(View.VISIBLE);
                    holder.btnRemove.setVisibility(View.VISIBLE);
                    holder.tvNumber.setText(String.valueOf(newNumb));
                }
            }
        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNumb = Integer.parseInt(holder.tvNumber.getText().toString()) - 1;
                int recentNumb = MainActivity.arrayCart.get(position).getProductNumber();
                long recentPrice = MainActivity.arrayCart.get(position).getProductPrice();
                MainActivity.arrayCart.get(position).setProductNumber(newNumb);
                long newPrice = (recentPrice * newNumb) / recentNumb;
                MainActivity.arrayCart.get(position).setProductPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvproductPrice.setText(decimalFormat.format(newPrice) + "$");
                CartActivity.eventUtil();
                if (newNumb < 2) {
                    holder.btnRemove.setVisibility(View.INVISIBLE);
                    holder.btnAdd.setVisibility(View.VISIBLE);
                    holder.tvNumber.setText(String.valueOf(newNumb));
                } else {
                    holder.btnAdd.setVisibility(View.VISIBLE);
                    holder.btnRemove.setVisibility(View.VISIBLE);
                    holder.tvNumber.setText(String.valueOf(newNumb));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayCart.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        ImageButton btnRemove, btnAdd;
        ImageView imvCartItem;
        TextView tvproductName, tvproductPrice, tvNumber;

        public CartViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvCartItem = itemView.findViewById(R.id.imvCart);
            tvproductName = itemView.findViewById(R.id.tvCartName);
            tvproductPrice = itemView.findViewById(R.id.tvCartPrice);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
