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

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {

    ArrayList<Product> arrayPhone;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PhoneAdapter(ArrayList<Product> arrayPhone) {
        this.arrayPhone = arrayPhone;
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_phone, parent, false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Product phone = arrayPhone.get(position);
        holder.tvPhoneName.setText(phone.getProductname());
        holder.tvPhonePrice.setText(phone.getProductprice() + "$");
        Picasso.get().load(phone.getProductimage()).into(holder.imvPhone);
    }

    @Override
    public int getItemCount() {
        return arrayPhone.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        ImageView imvPhone;
        TextView tvPhoneName, tvPhonePrice;

        public PhoneViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvPhone = itemView.findViewById(R.id.imvPhone);
            tvPhoneName = itemView.findViewById(R.id.tvPhoneName);
            tvPhonePrice = itemView.findViewById(R.id.tvPhonePrice);
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

