package com.example.shop.adapter.producttype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.model.ProductType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductTypeAdapter extends BaseAdapter {

    ArrayList<ProductType> arrayProductTypes;
    Context context;

    public ProductTypeAdapter(ArrayList<ProductType> arrayProductTypes, Context context) {
        this.arrayProductTypes = arrayProductTypes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayProductTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayProductTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ProductTypeViewHolder {
        TextView tvProduct;
        ImageView imvProduct;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductTypeViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ProductTypeViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_menu_producttype, null);
            viewHolder.tvProduct = convertView.findViewById(R.id.tvProduct);
            viewHolder.imvProduct = convertView.findViewById(R.id.imvProduct);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ProductTypeViewHolder) convertView.getTag();
        }
        ProductType productType = (ProductType) getItem(position);
        viewHolder.tvProduct.setText(productType.getProducttypename());
        Picasso.get().load(productType.getProducttypeimage()).into(viewHolder.imvProduct);
        return convertView;

    }
}
