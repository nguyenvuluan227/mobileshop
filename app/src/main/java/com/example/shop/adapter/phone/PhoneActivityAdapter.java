package com.example.shop.adapter.phone;

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

public class PhoneActivityAdapter extends RecyclerView.Adapter<PhoneActivityAdapter.PhoneActivityViewHolder> {

    ArrayList<Product> arrayPhone;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PhoneActivityAdapter(ArrayList<Product> arrayPhone) {
        this.arrayPhone = arrayPhone;
    }

    @NonNull
    @Override
    public PhoneActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_activity_phone, parent, false);
        return new PhoneActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneActivityViewHolder holder, int position) {
        Product phone = arrayPhone.get(position);
        holder.tvPhoneActivityName.setText(arrayPhone.get(position).getProductname());
        holder.tvPhoneActivityPrice.setText(arrayPhone.get(position).getProductprice() + "$");
        holder.tvPhoneActivityDescription.setText(arrayPhone.get(position).getProductdescription());
        Picasso.get().load(arrayPhone.get(position).getProductimage()).into(holder.imvPhoneActivity);
    }

    @Override
    public int getItemCount() {
        return arrayPhone.size();
    }

    public class PhoneActivityViewHolder extends RecyclerView.ViewHolder {

        ImageView imvPhoneActivity;
        TextView tvPhoneActivityName, tvPhoneActivityPrice, tvPhoneActivityDescription;

        public PhoneActivityViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvPhoneActivity = itemView.findViewById(R.id.imvActivityPhone);
            tvPhoneActivityName = itemView.findViewById(R.id.tvActivityPhoneName);
            tvPhoneActivityPrice = itemView.findViewById(R.id.tvActivityPhonePrice);
            tvPhoneActivityDescription = itemView.findViewById(R.id.tvActivityPhoneDescription);
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
