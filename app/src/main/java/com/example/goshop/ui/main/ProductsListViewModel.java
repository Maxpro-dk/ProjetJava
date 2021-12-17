package com.example.goshop.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.goshop.entity.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<ArrayList<Product>> dataLive;
    ArrayList<Product> productArrayList;
    FirebaseFirestore db;

    public  ProductsListViewModel()
    {
        dataLive =new MutableLiveData<>();
        init();



    }
    public  void generator()
    {
        Product product= new Product();
        product.name="Galaxy SamSung";
        product.price=1000000;
        product.quantityInStock=2552052;
        product.uri="image.jpg";

        productArrayList =new ArrayList<>();
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);
        productArrayList.add(product);

        productArrayList.add(product);

    }
    public  void init(){
        generator();
        dataLive.setValue(productArrayList);
    }

    public MutableLiveData<ArrayList<Product>> getDataLive() {

        return dataLive;
    }
    public void  addProduct(Product product){
        productArrayList.add(product);
    }

}