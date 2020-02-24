package com.assignment.eshopapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DetailProductFragment  extends Fragment {

    private final ArrayList<ProductsDetails> productsDetails;
    private final Context context;
    //private final String title;
    private final Categories categories;
    private View mView;
    private GridView grid_product_item;

    public DetailProductFragment(ArrayList<ProductsDetails> productsDetails, Context context, Categories categories) {
        this.productsDetails= productsDetails;
        this.context= context;
        this.categories=categories;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView txt_title = (TextView) mView.findViewById(R.id.txt_title);
        txt_title.setText(categories.getName());
        //String tax= productsDetails.get()
        grid_product_item=(GridView)mView.findViewById(R.id.grid_product_item);

        final ProductDescriptionAdapter productDescriptionAdapter= new ProductDescriptionAdapter(productsDetails,context);
        grid_product_item.setAdapter(productDescriptionAdapter);

        grid_product_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProductSubCategory productSubCategory= new ProductSubCategory(productsDetails.get(position).getVariantsList(),productsDetails.get(position).getName(),productsDetails.get(position).getTax());
                ((ScrollingActivity)context).addFragment(R.id.content_frame,productSubCategory,"ProductSubCategory");

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView=inflater.inflate(R.layout.product_detail,null);
        return mView;
    }
}
