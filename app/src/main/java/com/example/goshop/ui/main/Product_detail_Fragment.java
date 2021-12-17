package com.example.goshop.ui.main;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.goshop.R;
import com.example.goshop.entity.Product;
import com.example.goshop.utility.ImageManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Product_detail_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Product_detail_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // WEIGET

    AppCompatTextView nameTView;
    AppCompatTextView descriptionTView;
    AppCompatTextView priceTView;
    AppCompatTextView quantityAvailableTView;
    AppCompatTextView quantityAlertTView;
    ImageView imageView ;
    Product product;


    public Product_detail_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Product_detail_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Product_detail_Fragment newInstance(String param1, String param2) {
        Product_detail_Fragment fragment = new Product_detail_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_product_detail_, container, false);
        nameTView = view.findViewById(R.id.name);
        descriptionTView = view.findViewById(R.id.description);
        priceTView = view.findViewById(R.id.price);
        quantityAvailableTView = view.findViewById(R.id.quantity_in_stock);
        quantityAlertTView = view.findViewById(R.id.alert_quantity);
        imageView = view.findViewById(R.id.img_detail);

         addProduct();
        // Inflate the layout for this fragment
        return  view;
    }
//String.format("%,.2f",product1.getPrice())+" Fcfa")
    public void addProduct(){
        nameTView.setText("maxime");
        descriptionTView.setText(product.description);
        priceTView.setText(String.format("%,.2f",product.price)+"  Fcfa");
        quantityAvailableTView.setText(String.format("%,.2f",product.quantityInStock)+"");
        quantityAlertTView.setText(String.format("%,.2f",product.alertQuantity)+"");
        Bitmap bitmap = new ImageManager(getContext()).setFileName(product.uri).load();
        imageView.setImageBitmap(bitmap);



    }
}