package com.example.glowgo.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.glowgo.Model.TourPackage;
import com.example.glowgo.R;

import java.util.List;

public class TourPackagesAdapter extends RecyclerView.Adapter<TourPackagesAdapter.RecyclerViewHolders> {

    private List<TourPackage> tourPackagesList;
    private Context context;
    CustomItemClickListener listener;


    public TourPackagesAdapter(Context context, List<TourPackage> tourPackagesList) {
        this.tourPackagesList = tourPackagesList;
        this.context = context;
    }

    @Override
    public TourPackagesAdapter.RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_row, parent, false);
        final TourPackagesAdapter.RecyclerViewHolders holder = new TourPackagesAdapter.RecyclerViewHolders(layoutView);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getPosition());

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(TourPackagesAdapter.RecyclerViewHolders holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder);

        Glide.with(context)
                .load(tourPackagesList.get(position).getImgeUrl())
                .apply(requestOptions)
                .into(holder.countryPhoto);

        holder.countryName.setText(tourPackagesList.get(position).getPackageName());
        holder.packageCountry.setText(tourPackagesList.get(position).getPackageCountry());
        holder.price.setText(tourPackagesList.get(position).getPackagePrice() + " EGP");

    }


    //Set method of OnItemClickListener object
    public void setOnItemClickListener(CustomItemClickListener recyclerViewItemClickListner) {
        this.listener = recyclerViewItemClickListner;
    }


    @Override
    public int getItemCount() {
        return this.tourPackagesList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        /**
         * this class contains onclick listener for the recylcer view home
         */

        public TextView countryName, price, packageCountry;
        public ImageView countryPhoto;
        public int position = 0;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.tv_packageName);
            countryPhoto = itemView.findViewById(R.id.iv_imageView);
            packageCountry = itemView.findViewById(R.id.tv_country);
            price = itemView.findViewById(R.id.tv_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //When item view is clicked, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    listener.onItemClick(v, position);
                }
            });
        }


    }


}
