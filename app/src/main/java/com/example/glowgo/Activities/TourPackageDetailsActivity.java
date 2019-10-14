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

public class TourPackageDetailsActivity extends AppCompatActivity {

    String pack_id,pack_name,pack_image,pack_desc,pack_country,pack_price;
    ImageView pack_imagee;
    TextView tv_name,tv_desc,tv_country,tv_price;

    Intent getPr_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_package_details);

        getPr_id = getIntent();
        pack_imagee = findViewById(R.id.iv_productImg);
        tv_name = findViewById(R.id.tv_packageName);
        tv_desc = findViewById(R.id.tv_packageDesc);
        tv_country = findViewById(R.id.tv_packageCounry);
        tv_price = findViewById(R.id.tv_packge_price);
        loadProductById(getPr_id.getStringExtra("package_id"));


    }


    private void loadProductById(String id){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TourPackages");
        query.whereEqualTo("objectId",id);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {


                        pack_name = o.getString("package_name");
                        pack_country = o.getString("package_country");
                        ParseFile package_image = o.getParseFile("package_img");
                        pack_image = package_image.getUrl();
                        pack_desc = o.getString("package_desc");
                        pack_price = o.getString("package_price");






                    }
                    RequestOptions requestOptions = new RequestOptions()
                            .placeholder(R.drawable.placeholder).centerCrop();

                    Glide.with(TourPackageDetailsActivity.this)
                            .load(pack_image)
                            .apply(requestOptions)
                            .into(pack_imagee);
                    tv_name.setText(pack_name);
                    tv_desc.setText(pack_desc);
                    tv_price.setText(pack_price + " EGP");
                    tv_country.setText(pack_country);




                } else {
                    Toast.makeText(TourPackageDetailsActivity.this, "error= " + e, Toast.LENGTH_LONG).show();
                }
            }
        });


    }





}
