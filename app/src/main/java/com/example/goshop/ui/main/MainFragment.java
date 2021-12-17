package com.example.goshop.ui.main;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.goshop.R;
import com.example.goshop.entity.Product;
import com.example.goshop.utility.EditError;
import com.example.goshop.utility.ImageManager;
import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.security.Provider;

public class MainFragment extends Fragment  {

    private TextInputEditText designationEditText;
    private TextInputEditText descriptionEditText;
    private TextInputEditText priceEditText;
    private TextInputEditText quantityInStockEditText;
    private TextInputEditText alertQuantityEditText;
    private Button myBtn;
    private  Button myBtnImg;
    private ImageView imageView;
    private  Button btnsend;
    ProductsListViewModel viewModel;

    public  final static int PICK_IMAGE =125;


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result == null) {
                        //Display an error
                        return;
                    }

                  Uri uri= result.getData().getData();
                    imageView.setImageURI(uri);
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.main_fragment,container,false);
       viewModel= new ViewModelProvider(requireActivity()).get(ProductsListViewModel.class);

       designationEditText = rootView.findViewById(R.id.name);
       descriptionEditText = rootView.findViewById(R.id.description);
       priceEditText = rootView.findViewById(R.id.price);
       quantityInStockEditText =rootView.findViewById(R.id.quantity_in_stock);
       alertQuantityEditText = rootView.findViewById(R.id.alert_quantity);
       myBtnImg = rootView.findViewById(R.id.img_btn);
       imageView = rootView.findViewById(R.id.img_view);
       myBtn = rootView.findViewById(R.id.my_btn);


        myBtn.setOnClickListener( btnAddClick);
        myBtnImg.setOnClickListener(btnimg);


       return rootView;
    }

    private  View.OnClickListener btnimg = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(Intent.createChooser(intent, "Select Picture"));

        }
    };

    // ACTION TO DO IF MY_BTN WAS CLICKED
    private  View.OnClickListener btnAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            if (inputVerify()) {
                Product product = new Product();
                product.name = designationEditText.getText().toString();
                product.description = descriptionEditText.getText().toString();
                product.price = Float.parseFloat(priceEditText.getText().toString());
                product.quantityInStock = Integer.parseInt(quantityInStockEditText.getText().toString());
                product.alertQuantity = Integer.parseInt(alertQuantityEditText.getText().toString());
                String imgWay=product.name.trim().toLowerCase()+".jpg";
                product.uri=imgWay;
                new ImageManager(getContext())
                        .setFileName(imgWay)
                        .save((Bitmap) ((BitmapDrawable) imageView.getDrawable()).getBitmap());


                Bundle bundle =  new Bundle();
//                bundle.putSerializable("product",product);
                viewModel.addProduct(product);

                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_productsListFragment);

            }


        }
    };



    // verify that input is not empty by using EditError class
 private boolean inputVerify(){
     EditError error = new EditError();

     error.isEmpty(designationEditText,"Renseigner la désignation du produit");
     error.isEmpty(descriptionEditText,"Renseigner la description  du produit");
     error.isEmpty(priceEditText,"Renseigner le prix du produit");
     error.isEmpty(quantityInStockEditText,"Renseigner la quantité en stock du produit");
     error.isEmpty(alertQuantityEditText,"Renseigner la quantité alerte du produit");

     return error.isInputValide();
 }




}