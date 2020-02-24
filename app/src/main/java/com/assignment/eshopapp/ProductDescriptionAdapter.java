package com.assignment.eshopapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductDescriptionAdapter extends BaseAdapter {

    private final List<ProductsDetails> productsDetails;
    private final Context context;
    private TextView txt_product_names;
    private TextView txt_product_variants;

    ProductDescriptionAdapter (List<ProductsDetails> productsDetails,Context context){
        this.productsDetails=productsDetails;
        this.context=context;
    }
    @Override
    public int getCount() {
        return productsDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return productsDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gridchild_product_desc, parent, false);
            txt_product_names=(TextView)convertView.findViewById(R.id.txt_product_names);
            txt_product_variants=(TextView)convertView.findViewById(R.id.txt_product_variants);
        }
        Log.i("Prodduct",productsDetails.get(position).getName());
        txt_product_names.setText(productsDetails.get(position).getName());
        if(productsDetails.get(position).getVariantsList()!=null)
        txt_product_variants.setText("Options: "+productsDetails.get(position).getVariantsList().size());
        return convertView;
    }
}
