package com.example.goshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goshop.entity.Product;
import com.example.goshop.utility.ImageManager;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    ArrayList<Product> productArrayList;
    Context context;
    OnProductListener onProductListener;
    public ProductAdapter(ArrayList<Product> productArrayList, Context context,OnProductListener onProductListener) {
        this.productArrayList = productArrayList;
        this.context = context;
        this.onProductListener =onProductListener ;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lists_item2,parent,false);
        return new ViewHolder(viewItem,onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(product.name);
        viewHolder.quantity.setText(String.format("%,.2f",product.quantityInStock)+"");
        viewHolder.price.setText(String.format("%,.2f",product.price)+"  Fcfa");
        viewHolder.imageView.setImageResource(R.drawable.fleur);
        Bitmap bitmap = new ImageManager(context).setFileName(product.uri).load();
        viewHolder.imageView.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {

        return productArrayList== null? 0: productArrayList.size();
    }

    public void updateProductList(List<Product> products){
        productArrayList.clear();
        productArrayList.addAll(products);
        this.notifyDataSetChanged();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener  {
        TextView  price ;
        TextView quantity;
        TextView name;
        ImageView imageView;
        OnProductListener onProductListener;

        public ViewHolder(@NonNull View itemView,OnProductListener productListener) {

            super(itemView);
            this.onProductListener =productListener;
            price = itemView.findViewById(R.id.price);
            quantity =itemView.findViewById(R.id.quantity_in_stock);
            name = itemView.findViewById(R.id.name);
            imageView =itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            onProductListener.onProductClick(getAdapterPosition(),v);
        }
    }

    public interface OnProductListener{
        void onProductClick(int position ,View view);

    }
}
