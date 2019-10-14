package com.example.glowgo.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.glowgo.Adapters.CustomItemClickListener;
import com.example.glowgo.Adapters.StaggeredRecyclerViewAdapter;
import com.example.glowgo.AuthActivity.LoginActivity;
import com.example.glowgo.DatabaseHelper;
import com.example.glowgo.Model.Product;
import com.example.glowgo.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    Intent intent;
    RecyclerView recyclerView;
    ArrayList<Product> productsList = new ArrayList<>();
    String idofproduct;
    ProgressDialog pd;
    DatabaseHelper databaseHelper;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        databaseHelper = new DatabaseHelper(ProductActivity.this);
        databaseHelper.connectToDB();
        isNetworkConnected(ProductActivity.this);
        pd = ProgressDialog.show(ProductActivity.this, "Loading", "getting data please wait..", true);
        intent = getIntent();
        category = intent.getStringExtra("categoryName");


        loadProduct();
        pd.dismiss();

    }


    public void loadProduct() {
        final ProgressDialog pd = ProgressDialog.show(ProductActivity.this, "", "Loading Please wait..", true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.whereEqualTo("category_name",category);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {

                        idofproduct = o.getObjectId();
                        String name = o.getString("product_name");
                        String title = o.getString("snippet");
                        ParseFile product_image = o.getParseFile("product_img");
                        String imgURL = product_image.getUrl();
                        String price = o.getString("product_price");


                        Product product = new Product(idofproduct ,imgURL,name,price,title);
                        productsList.add(product);

                    }

                    recyclerView = findViewById(R.id.recycler_view_vendors);
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));


                    StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter = new StaggeredRecyclerViewAdapter(ProductActivity.this, productsList);

                    recyclerView.setAdapter(staggeredRecyclerViewAdapter);

                    staggeredRecyclerViewAdapter.setOnItemClickListener(new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                           Intent sendToDetailsInetent = new Intent(ProductActivity.this,ProductDetailsActivity.class);
                           sendToDetailsInetent.putExtra("product_id",productsList.get(position).getId());
                           startActivity(sendToDetailsInetent);
                             }
                    });

                } else {
                    Toast.makeText(ProductActivity.this, "error= " + e, Toast.LENGTH_LONG).show();
                }
                pd.dismiss();
            }
        });


    }

    public void isNetworkConnected(Context c) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(c.CONNECTIVITY_SERVICE);

        if (!(cm.getActiveNetworkInfo() != null)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(c);
            builder1.setMessage("No Network Connection, Please Connect to internet and try again.");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }



}
