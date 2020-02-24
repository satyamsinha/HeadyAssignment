package com.assignment.eshopapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductSubCategoryAdapter extends BaseAdapter {

    private final List<Variants> variantsList;
    private final Context context;
    private final Tax tax;
    private TextView txt_product_name;
    private TextView txt_color;
    private TextView txt_price;
    private TextView txt_size;

    ProductSubCategoryAdapter(List<Variants> variantsList, Context context, Tax tax){
        this.variantsList=variantsList;
        this.context= context;
        this.tax= tax;
    }

    @Override
    public int getCount() {
        return variantsList.size();
    }

    @Override
    public Object getItem(int position) {
        return variantsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_detail_item, parent, false);
            txt_size=(TextView)convertView.findViewById(R.id.txt_size);
            txt_color=(TextView)convertView.findViewById(R.id.txt_color);
            txt_price=(TextView)convertView.findViewById(R.id.txt_price);
        }
        txt_color.setText("Color :"+variantsList.get(position).getColor());
        double priceTotal= Double.parseDouble(variantsList.get(position).getPrice())*(100+Double.parseDouble(tax.getValue()))/100;
        txt_price.setText("Price : Rs."+variantsList.get(position).getPrice()  + " + Tax:"+tax.getValue()+"% \nTotal Price :Rs."+ priceTotal );
        txt_size.setText("Size :"+variantsList.get(position).getSize());

        return convertView;
    }
}
