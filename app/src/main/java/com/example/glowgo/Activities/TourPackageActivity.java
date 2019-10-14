package com.example.glowgo.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.glowgo.Adapters.CustomItemClickListener;
import com.example.glowgo.Adapters.RecyclerViewAdapter;
import com.example.glowgo.Adapters.TourPackagesAdapter;
import com.example.glowgo.AuthActivity.LoginActivity;
import com.example.glowgo.MainActivity;
import com.example.glowgo.Model.Category;
import com.example.glowgo.Model.TourPackage;
import com.example.glowgo.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class TourPackageActivity extends AppCompatActivity {
    ArrayList<TourPackage> tourPackageList = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_package);
        recyclerView = findViewById(R.id.recycler_view);
        loadTourPackages();
    }

    public void loadTourPackages() {
        final ProgressDialog pd = ProgressDialog.show(TourPackageActivity.this, "", "Loading Please wait..", true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TourPackages");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {

                        String tpid = o.getObjectId();
                        String tp_name = o.getString("package_name");
                        ParseFile tpImage = o.getParseFile("package_img");
                        String imageURL = tpImage.getUrl();
                        String tp_country = o.getString("package_country");
                        String tp_price = o.getString("package_price");
                        TourPackage tourPackage = new TourPackage(tpid,imageURL,tp_name,tp_country,tp_price);
                        tourPackageList.add(tourPackage);

                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(TourPackageActivity.this));

                    TourPackagesAdapter RecyclerViewAdapter = new TourPackagesAdapter(TourPackageActivity.this, tourPackageList);

                    recyclerView.setAdapter(RecyclerViewAdapter);

                    RecyclerViewAdapter.setOnItemClickListener(new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent sendToDetailsInetent = new Intent(TourPackageActivity.this,TourPackageDetailsActivity.class);
                            sendToDetailsInetent.putExtra("package_id",tourPackageList.get(position).getId());
                            startActivity(sendToDetailsInetent);

                        }
                    });

                } else {
                    Toast.makeText(TourPackageActivity.this, "error= " + e, Toast.LENGTH_LONG).show();
                }
                pd.dismiss();
            }
        });


    }
}
