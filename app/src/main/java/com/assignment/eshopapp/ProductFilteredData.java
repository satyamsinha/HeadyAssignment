package com.assignment.eshopapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductFilteredData extends Fragment {

    private final Context context;
    private final Product product;
    private final int position;
    private View mView;
    List<ProductsDetails> productsDetails=new ArrayList<>();
    private List<Products> productsList;
    private RecyclerView recyclerProductItem;

    ProductFilteredData(Context context, Product product, int position){
        this.context=context;
        this.product=product;
        this.position=position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = inflater.inflate(R.layout.filter_frag,null);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerProductItem=(RecyclerView)mView.findViewById(R.id.recycler_product_item);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerProductItem.setLayoutManager(mLayoutManager);
        recyclerProductItem.setItemAnimator(new DefaultItemAnimator());
        TextView txt_title = (TextView) mView.findViewById(R.id.txt_title);
        txt_title.setText(product.getRankings().get(position).getRanking());
        findProducts(position);
        ProductFilterDataAdapter productFilterDataAdapter= new ProductFilterDataAdapter(productsDetails,getContext(),productsList);
        recyclerProductItem.setAdapter(productFilterDataAdapter);

    }

    void findProducts(int position){

        productsList= product.getRankings().get(position).getProducts();

        for(int cnt =0 ; cnt<productsList.size();cnt++ ) {
            for (int prodcnt = 0; prodcnt < product.getCategories().size(); prodcnt++) {
                for (int prodCatcnt = 0; prodCatcnt < product.getCategories().get(prodcnt).getProducts().size(); prodCatcnt++) {
                    if (productsList.get(cnt).getId().equals( product.getCategories().get(prodcnt).getProducts().get(prodCatcnt).getId()))
                        productsDetails.add(product.getCategories().get(prodcnt).getProducts().get(prodCatcnt));
                }
            }
        }


    }

}
