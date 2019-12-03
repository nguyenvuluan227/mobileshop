package com.example.shop.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop.model.Customer;
import com.example.shop.model.Order;
import com.example.shop.model.Product;
import com.example.shop.model.ProductType;
import com.example.shop.repository.AllProductRepository;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ProductTypeRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private ProductTypeRepository productTypeRepository = ProductTypeRepository.getInstance();
    private AllProductRepository allProductRepository = AllProductRepository.getInstance();
    private ProductRepository productRepository = ProductRepository.getInstance();
    private CustomerRepository customerRepository = CustomerRepository.getInstance();
    private OrderRepository orderRepository = OrderRepository.getInstance();

    public MutableLiveData<List<ProductType>> onValidProductType() {
        return productTypeRepository.getProductType();
    }

    public MutableLiveData<List<Product>> onValidAllProduct() {
        return allProductRepository.getAllProduct();
    }

    public MutableLiveData<List<Product>> onValidProduct(String idtype) {
        return productRepository.getProduct(idtype);
    }

    public MutableLiveData<Customer> onValidCustomer(Integer id, String name, String phone, String email) {
        return customerRepository.getCustomer(id, name, phone, email);
    }

    public MutableLiveData<String> onValidOrder(Integer id, Integer idorder, Integer idproduct, String name, long price, Integer number) {
        return orderRepository.getOrder(id, idorder, idproduct, name, price, number);
    }


}
