package com.example.glowgo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.glowgo.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    String pr_id,pr_name,pr_image,pr_desc,pr_snippet,pr_price;
    ImageView pr_imagee;
    TextView tv_name,tv_desc,tv_snippet,tv_price;

    Intent getPr_id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getPr_id = getIntent();
        pr_imagee = findViewById(R.id.iv_productImg);
        tv_name = findViewById(R.id.tv_packageName);
        tv_desc = findViewById(R.id.tv_packageDesc);
        tv_snippet = findViewById(R.id.tv_packageCounry);
        tv_price = findViewById(R.id.tv_packge_price);
        loadProductById(getPr_id.getStringExtra("product_id"));

    }

    private void loadProductById(String id){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.whereEqualTo("objectId",id);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {


                        pr_name = o.getString("product_name");
                        pr_snippet = o.getString("snippet");
                        ParseFile product_image = o.getParseFile("product_img");
                        pr_image = product_image.getUrl();
                        pr_desc = o.getString("product_description");
                        pr_price = o.getString("product_price");






                    }
                    RequestOptions requestOptions = new RequestOptions()
                            .placeholder(R.drawable.placeholder).centerCrop();

                    Glide.with(ProductDetailsActivity.this)
                            .load(pr_image)
                            .apply(requestOptions)
                            .into(pr_imagee);
                    tv_name.setText(pr_name);
                    tv_desc.setText(pr_desc);
                    tv_price.setText(pr_price + " EGP");
                    tv_snippet.setText(pr_snippet);




                } else {
                    Toast.makeText(ProductDetailsActivity.this, "error= " + e, Toast.LENGTH_LONG).show();
                }
            }
        });


    }




}
