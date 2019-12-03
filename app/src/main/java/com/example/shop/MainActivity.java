package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.example.shop.adapter.MainViewPagerAdapter;
import com.example.shop.adapter.ProductViewPagerAdapter;
import com.example.shop.adapter.producttype.ProductTypeAdapter;
import com.example.shop.fragment.AllProductFragment;
import com.example.shop.fragment.banner.BannerFragment;
import com.example.shop.fragment.laptop.LaptopFragment;
import com.example.shop.fragment.phone.PhoneFragment;
import com.example.shop.model.Cart;
import com.example.shop.model.ProductType;
import com.example.shop.viewmodel.MainViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ViewPager viewPagerBanner, viewPagerProduct;
    Runnable runnable;
    Handler handler;
    int currentItem;
    ArrayList<ProductType> arrayProductTypes;
    ProductTypeAdapter adapter;
    ListView listView;
    MainViewModel viewModel;
    TabLayout tabLayoutProduct;
    public static ArrayList<Cart> arrayCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupActionBar();
        setupBannerViewPager();
        setupViewModel();
        setupProductViewPager();
        setupProductTabLayout();
        setupMenuItemClick();
    }

    private void init() {
        viewPagerBanner = findViewById(R.id.viewPagerBanner);
        viewPagerProduct = findViewById(R.id.viewPagerProduct);
        tabLayoutProduct = findViewById(R.id.tabLayout);
        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        listView = findViewById(R.id.listView);
        if (arrayCart != null) {

        } else {
            arrayCart = new ArrayList<>();
        }
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void setupBannerViewPager() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(BannerFragment.newInstance("https://i2.wp.com/brightspotincentivesevents.com/wp-content/uploads/2018/09/iphone-x-banner.jpg?ssl=1"));
        mainViewPagerAdapter.addFragment(BannerFragment.newInstance("https://didongviet.vn/blog/wp-content/uploads/2019/09/giai-ma-suc-hut-cua-the-he-iphone-11-tai-apple-event-nam-nay-banner.jpg"));
        mainViewPagerAdapter.addFragment(BannerFragment.newInstance("https://www.fortress.com.hk/en/newsfeed/wp-content/uploads/asusbanner_v1_en.jpg"));
        mainViewPagerAdapter.addFragment(BannerFragment.newInstance("https://i.gadgets360cdn.com/large/ZenBookS13_Main_1546848488737.jpg?output-quality=80"));
        viewPagerBanner.setAdapter(mainViewPagerAdapter);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentItem = viewPagerBanner.getCurrentItem();
                currentItem++;
                if (currentItem >= viewPagerBanner.getAdapter().getCount()) {
                    currentItem = 0;
                }
                viewPagerBanner.setCurrentItem(currentItem, true);
                handler.postDelayed(runnable, 4000);
            }
        };
        handler.postDelayed(runnable, 4000);
    }

    private void setupProductViewPager() {
        ProductViewPagerAdapter productViewPagerAdapter = new ProductViewPagerAdapter(getSupportFragmentManager());
        productViewPagerAdapter.addFragment(AllProductFragment.newInstance(), "All");
        productViewPagerAdapter.addFragment(PhoneFragment.newInstance(), "Phone");
        productViewPagerAdapter.addFragment(LaptopFragment.newInstance(), "Laptop");
        viewPagerProduct.setAdapter(productViewPagerAdapter);
    }

    private void setupViewModel() {
        viewModel = new MainViewModel();
        viewModel.onValidProductType().observe(this, new Observer<List<ProductType>>() {
            @Override
            public void onChanged(List<ProductType> productTypes) {
                arrayProductTypes = new ArrayList<>();
                arrayProductTypes.addAll(productTypes);
                adapter = new ProductTypeAdapter(arrayProductTypes, getApplicationContext());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupProductTabLayout() {
        tabLayoutProduct.setupWithViewPager(viewPagerProduct);
    }

    private void setupMenuItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent phoneIntent = new Intent(MainActivity.this, PhoneActivity.class);
                        phoneIntent.putExtra("idtype", arrayProductTypes.get(position).getIdtype());
                        startActivity(phoneIntent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 1:
                        Intent laptopIntent = new Intent(MainActivity.this, LaptopActivity.class);
                        laptopIntent.putExtra("idtype", arrayProductTypes.get(position).getIdtype());
                        startActivity(laptopIntent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 2:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 3:
                        Intent contactIntent = new Intent(MainActivity.this, ContactActivity.class);
                        startActivity(contactIntent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 4:
                        Intent infoIntent = new Intent(MainActivity.this, InfoActivity.class);
                        startActivity(infoIntent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuCart:
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

