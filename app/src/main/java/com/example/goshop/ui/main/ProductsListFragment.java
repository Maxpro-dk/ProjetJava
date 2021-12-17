package com.example.goshop.ui.main;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goshop.ProductAdapter;
import com.example.goshop.R;
import com.example.goshop.databinding.ProductsListFragmentBinding;
import com.example.goshop.entity.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductsListFragment extends Fragment implements LifecycleOwner , ProductAdapter.OnProductListener {
    private ProductsListFragment context;
    public ProductsListViewModel mViewModel;
    ProductsListFragmentBinding binding;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    FloatingActionButton addBtn;
    ProductAdapter.OnProductListener productListener= this;

    public static ProductsListFragment newInstance() {
        return new ProductsListFragment();
    }



    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.products_list_fragment, container, false);
       context = this;
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        addBtn =view.findViewById(R.id.go_to_add_btn);
        mViewModel = new ViewModelProvider(requireActivity()).get(ProductsListViewModel.class);
        mViewModel.getDataLive().observe(getViewLifecycleOwner(),productListUpdateObserver);

        addBtn.setOnClickListener(addProductPage);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    Observer<ArrayList<Product>> productListUpdateObserver = new Observer<ArrayList<Product>>() {
        @Override
        public void onChanged(ArrayList<Product> products) {
            productAdapter = new ProductAdapter(products,requireActivity(),productListener);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(productAdapter);
        }
    };

    @Override
    public void onProductClick(int position,View view) {
        mViewModel.getDataLive().getValue().get(position).toString();
        Bundle bundle =  new Bundle();
        bundle.putSerializable("product",mViewModel.getDataLive().getValue().get(position));

        Navigation.findNavController(view).navigate(R.id.action_productsListFragment_to_product_detail_Fragment,bundle);

    }

    private  View.OnClickListener addProductPage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Navigation.findNavController(v).navigate(R.id.action_productsListFragment_to_mainFragment);
        }
    };
}