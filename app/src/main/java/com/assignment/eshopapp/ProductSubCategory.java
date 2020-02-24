package com.assignment.eshopapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ProductSubCategory extends Fragment {
    private final List<Variants> variantsList;
    private final String title;
    private final Tax tax;
    private View mView;
    private GridView gridProductItem;

    ProductSubCategory(List<Variants> variantsList, String title, Tax tax){
        this.variantsList=variantsList;
        this.title=title;
        this.tax= tax;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView=inflater.inflate(R.layout.product_detail,null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridProductItem=(GridView)mView.findViewById(R.id.grid_product_item);
        TextView txt_title = (TextView) mView.findViewById(R.id.txt_title);
        txt_title.setText(title+"\n Variants :"+variantsList.size());
        ProductSubCategoryAdapter productSubCategoryAdapter= new ProductSubCategoryAdapter(variantsList,getContext(),tax);
        gridProductItem.setAdapter(productSubCategoryAdapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
