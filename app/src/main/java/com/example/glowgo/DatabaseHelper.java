package com.example.glowgo;

import android.content.Context;
import android.content.Intent;

import com.example.glowgo.Activities.ProductActivity;
import com.example.glowgo.Activities.TourPackageActivity;
import com.parse.Parse;

public class DatabaseHelper {

    Context mContext;

    public DatabaseHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void connectToDB(){

        //# database connection
        Parse.initialize(new Parse.Configuration.Builder(mContext)
                .applicationId("8GqrlHFvmRaMY4dp6pXPBi1NMfHUr71FEpkGJVYi")
                .clientKey("EiLABkWxejpF2XKwH5NlnpAnYBDTlxXyYiOzlQEe")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }

    public void sendToProductWithCategoryName(String categoryName){
        Intent intent = new Intent(mContext, ProductActivity.class);
        intent.putExtra("categoryName",categoryName);
        mContext.startActivity(intent);
    }

    public void sendToTourPackages(){
        Intent intent = new Intent(mContext, TourPackageActivity.class);
        mContext.startActivity(intent);
    }
}
