package com.assignment.eshopapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.net.ConnectivityManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragment extends Fragment implements  AdapterView.OnItemSelectedListener {

    private View view;
   // private ListView gridProduct;
    private Context context;
    Product product= new Product();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Spinner spinnerFilter;
    private ArrayList<String> filterData;
    private RecyclerView recyclerview;
    private ProductDashRecyclerAdapter productDashRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view= inflater.inflate(R.layout.product_dashboard,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //gridProduct =(ListView)view.findViewById(R.id.grid_product);
        recyclerview=(RecyclerView)view.findViewById(R.id.recycler_view);
        spinnerFilter=(Spinner)view.findViewById(R.id.spinner_filter);
        context=getActivity();
        pref = context.getSharedPreferences("ProdcutList", 0); // 0 - for private mode
        editor = pref.edit();

        String productString= pref.getString("product","");
        ApiCallInterface apiRequest=RetrofitRequest.getClient(context).create(ApiCallInterface.class);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());


        if(!TextUtils.isEmpty(productString)) {
            product = new Gson().fromJson(productString, Product.class);
            productDashRecyclerAdapter= new ProductDashRecyclerAdapter(product,context);
            recyclerview.setAdapter(productDashRecyclerAdapter);
            inflateSpinnerData();
        }
        else {

            final ProgressDialog progressBar = new ProgressDialog(context);
            progressBar.setCancelable(true);//you can cancel it by pressing back button
            progressBar.setMessage("Downloading ...");
            progressBar.show();
            if(isNetworkConnected()) {
                apiRequest.getProduct().enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        editor.putString("product", "" + response.body().toString());
                        product = response.body();
                        productDashRecyclerAdapter = new ProductDashRecyclerAdapter(product, context);
                        recyclerview.setAdapter(productDashRecyclerAdapter);
                        inflateSpinnerData();
                        progressBar.cancel();
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        progressBar.cancel();
                        Toast.makeText(context, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                    }

                });
            }else{
                progressBar.cancel();
                Toast.makeText(context, "Internet connection not available", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void inflateSpinnerData() {
        filterData = new ArrayList<String>();
        filterData.add("Select Filter");
        for(int cnt=0 ; cnt <product.getRankings().size();cnt++) {
            filterData.add(product.getRankings().get(cnt).getRanking());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, filterData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(dataAdapter);

        spinnerFilter.setOnItemSelectedListener(this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        String productString= pref.getString("product","");
        if(!TextUtils.isEmpty(productString)) {
            ProductDashRecyclerAdapter productDashRecyclerAdapter= new ProductDashRecyclerAdapter(product,context);
            recyclerview.setAdapter(productDashRecyclerAdapter);
            inflateSpinnerData();
        }
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position!=0) {
            String filterValue=parent.getItemAtPosition(position).toString();
            spinnerFilter.setSelection(0);
            ProductFilteredData productFilteredData = new ProductFilteredData(context, product,position-1);
            ((ScrollingActivity) context).addFragment(R.id.content_frame, productFilteredData, "ProductListFragment");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
