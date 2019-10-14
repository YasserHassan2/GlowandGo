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
import com.example.glowgo.Model.Category;
import com.example.glowgo.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolders> {

    private List<Category> categoriesList;
    private Context context;
    CustomItemClickListener listener;

    public RecyclerViewAdapter(Context context, List<Category> categoriesList) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row, null);
        final RecyclerViewHolders holder = new RecyclerViewHolders(layoutView);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getPosition());

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder);

        Glide.with(context)
                .load(categoriesList.get(position).getCat_imageURL())
                .apply(requestOptions)
                .into(holder.countryPhoto);

        holder.countryName.setText(categoriesList.get(position).getCat_title());

    }



    //Set method of OnItemClickListener object
    public void setOnItemClickListener(CustomItemClickListener recyclerViewItemClickListner){
        this.listener=recyclerViewItemClickListner;
    }



    @Override
    public int getItemCount() {
        return this.categoriesList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder  {

        /**
         * this class contains onclick listener for the recylcer view home
         */

        public TextView countryName;
        public ImageView countryPhoto;
        public int position=0;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.cat_name);
            countryPhoto = (ImageView) itemView.findViewById(R.id.cat_photo);


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