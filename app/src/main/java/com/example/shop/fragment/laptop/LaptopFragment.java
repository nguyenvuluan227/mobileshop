package com.example.shop.fragment.laptop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.shop.ProductDetailActivity;
import com.example.shop.R;
import com.example.shop.adapter.OnItemClickListener;
import com.example.shop.adapter.product.LaptopAdapter;
import com.example.shop.model.Product;
import com.example.shop.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class LaptopFragment extends Fragment {

    MainViewModel viewModel;
    ArrayList<Product> arrayLaptop;
    String idtype = "2";
    RecyclerView recyclerViewLaptop;
    StaggeredGridLayoutManager layoutManager;
    LaptopAdapter adapter;

    public static LaptopFragment newInstance() {

        Bundle args = new Bundle();

        LaptopFragment fragment = new LaptopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laptop, container, false);
        recyclerViewLaptop = view.findViewById(R.id.recyclerViewLaptop);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        viewModel = new MainViewModel();
        viewModel.onValidProduct(idtype).observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                arrayLaptop = new ArrayList<>();
                arrayLaptop.addAll(products);
                adapter = new LaptopAdapter(arrayLaptop);
                recyclerViewLaptop.setLayoutManager(layoutManager);
                recyclerViewLaptop.setAdapter(adapter);
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
                intent.putExtra("productDetail", arrayLaptop.get(position));
                startActivity(intent);
            }
        });
    }
}
