package com.example.glowgo;

import android.app.ProgressDialog;

import android.content.Intent;

import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import com.example.glowgo.Activities.ProductActivity;
import com.example.glowgo.Adapters.CustomItemClickListener;
import com.example.glowgo.Adapters.RecyclerViewAdapter;
import com.example.glowgo.AuthActivity.LoginActivity;
import com.example.glowgo.Model.AdBanner;
import com.example.glowgo.Model.Category;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ArrayList<SlideModel> imageList = new ArrayList<SlideModel>();
    ImageSlider imageSlider;

    DatabaseHelper databaseHelper;

    String imgUrl;

    ArrayList<AdBanner> adBanners = new ArrayList<AdBanner>();
    ArrayList<Category> categoriesList = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSlider = new ImageSlider(MainActivity.this);
        recyclerView = findViewById(R.id.recycler_view);
        imageSlider = findViewById(R.id.image_slider);
        final ProgressDialog pd = ProgressDialog.show(MainActivity.this, "", "Loading, Please wait..", true);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        loadAds();
        loadCategories();

        pd.dismiss();


    }


    public void loadAds() {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ads_Banners");


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {

                        String adUrl = o.getString("ad_url");
                        ParseFile adImage = o.getParseFile("ad_img");

                        String imgUrl = adImage.getUrl();

                        String ad_snppiet = o.getString("ad_snippet");


                        adBanners.add(new AdBanner(adUrl, imgUrl, ad_snppiet));
                        imageList.add(new SlideModel(imgUrl, ad_snppiet, true));


                    }
                    imageSlider.setImageList(imageList);

                    imageSlider.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemSelected(int i) {
                            String adURL = adBanners.get(i).getAd_url();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(adURL));
                            startActivity(intent);
                        }
                    });


                } else {
                    Toast.makeText(MainActivity.this, "error: " + e, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void loadCategories() {
        final ProgressDialog pd = ProgressDialog.show(MainActivity.this, "", "Loading Please wait..", true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Categories");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {

                        String cat_id = o.getObjectId();
                        String cat_title = o.getString("Category_title");
                        ParseFile imageFile = o.getParseFile("Category_image");
                        String imageURL = imageFile.getUrl();
                        String categoryType = o.getString("categoryType");
                        imgUrl = imageURL;

                        Category category1 = new Category(cat_id, cat_title, imageURL, categoryType);
                        categoriesList.add(category1);

                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    RecyclerViewAdapter RecyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, categoriesList);

                    recyclerView.setAdapter(RecyclerViewAdapter);

                    RecyclerViewAdapter.setOnItemClickListener(new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            if (categoriesList.get(position).getCategoriType().equals("product")) {

                                databaseHelper.sendToProductWithCategoryName(categoriesList.get(position).getCat_title());

                            } else if (categoriesList.get(position).getCategoriType().equals("package")) {
                                databaseHelper.sendToTourPackages();
                            } else {
                                Toast.makeText(MainActivity.this, "Not Avaliable Now", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, "error= " + e, Toast.LENGTH_LONG).show();
                }
                pd.dismiss();
            }
        });


    }


}
