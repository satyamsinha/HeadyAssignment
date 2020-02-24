package com.assignment.eshopapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductFilterDataAdapter  extends RecyclerView.Adapter<ProductFilterDataAdapter.MyViewHolder> {

    private final List<ProductsDetails> productsDetails;
    private final Context context;
    private final List<Products> productsList;
    private TextView txt_product_names;
    private TextView txt_product_variants;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_product_name;
        public MyViewHolder(View view) {
            super(view);
            txt_product_names=(TextView)view.findViewById(R.id.txt_product_names);
            txt_product_variants=(TextView)view.findViewById(R.id.txt_product_variants);
        }
    }


    ProductFilterDataAdapter(List<ProductsDetails> productsDetails, Context context, List<Products> productsList){
        this.productsDetails=productsDetails;
        this.context=context;
        this.productsList=productsList;
    }

    @Override
    public ProductFilterDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridchild_product_filter, parent, false);

        return new ProductFilterDataAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductFilterDataAdapter.MyViewHolder holder, final int position) {
        txt_product_names.setText(productsDetails.get(position).getName());
        if(productsList.get(position).getShares()!=null)
            txt_product_variants.setText("Share Count: "+productsList.get(position).getShares());
        else if(productsList.get(position).getView_count()!=null)
            txt_product_variants.setText("View Count: "+productsList.get(position).getView_count());
        else if(productsList.get(position).getOrder_count()!=null)
            txt_product_variants.setText("Order Count: "+productsList.get(position).getOrder_count());
        else
            txt_product_variants.setText("");
    }

    @Override
    public int getItemCount() {
        return productsDetails.size();
    }
}


