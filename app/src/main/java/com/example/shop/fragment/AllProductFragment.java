package com.example.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.shop.ProductDetailActivity;
import com.example.shop.R;
import com.example.shop.adapter.OnItemClickListener;
import com.example.shop.adapter.product.AllProductAdapter;
import com.example.shop.model.Product;
import com.example.shop.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllProductFragment extends Fragment {

    MainViewModel viewModel;
    ArrayList<Product> arrayProduct;
    AllProductAdapter adapter;
    RecyclerView recyclerViewProduct;
    StaggeredGridLayoutManager layoutManager;

    public static AllProductFragment newInstance() {

        Bundle args = new Bundle();

        AllProductFragment fragment = new AllProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_product, container, false);
        recyclerViewProduct = view.findViewById(R.id.recyclerViewAllProduct);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        viewModel = new MainViewModel();
        viewModel.onValidAllProduct().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                arrayProduct = new ArrayList<>();
                arrayProduct.addAll(products);
                adapter = new AllProductAdapter(arrayProduct);
                recyclerViewProduct.setLayoutManager(layoutManager);
                recyclerViewProduct.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                setupItemClick();
            }
        });
        return view;
    }

    private void setupItemClick() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productDetail",arrayProduct.get(position));
                startActivity(intent);
            }
        });
    }

}
