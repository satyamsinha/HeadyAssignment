package com.assignment.eshopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductDashRecyclerAdapter extends RecyclerView.Adapter<ProductDashRecyclerAdapter.MyViewHolder> {

    private final Product product;
    private final Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_product_name;
        public MyViewHolder(View view) {
            super(view);
            txt_product_name=(TextView)view.findViewById(R.id.txt_product_name);
        }
    }


    public ProductDashRecyclerAdapter(Product product, Context context){
        this.product=product;
        this.context= context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridchild_product, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ArrayList<Categories> productsDetailsArrayList = product.getCategories();
        holder.txt_product_name.setText(productsDetailsArrayList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailProductFragment detailProductFragment= new DetailProductFragment(product.getCategories().get(position).getProducts(),context,productsDetailsArrayList.get(position));
                ((ScrollingActivity)context).addFragment(R.id.content_frame,detailProductFragment,"ProductListFragment");

            }
        });
    }

    @Override
    public int getItemCount() {
        return product.getCategories().size();
    }
}