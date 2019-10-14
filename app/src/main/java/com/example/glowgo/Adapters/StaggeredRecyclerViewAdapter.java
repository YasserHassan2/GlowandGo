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
import com.example.glowgo.Model.Product;
import com.example.glowgo.R;

import java.util.ArrayList;

/**
 * Created by User on 1/17/2018.
 */

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder> {


    ArrayList<Product> prodcutsList;
    private Context mContext;
    CustomItemClickListener listener;

    public StaggeredRecyclerViewAdapter(Context mContext, ArrayList<Product> prodcutsList) {
        this.mContext = mContext;
        this.prodcutsList = prodcutsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getPosition());

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(prodcutsList.get(position).getProduct_imagURL())
                .apply(requestOptions)
                .into(holder.profile_image);

        holder.vendor_name.setText(prodcutsList.get(position).getProduct_name());
        holder.vendor_title.setText(prodcutsList.get(position).getSnippet());
        holder.vendor_price.setText(prodcutsList.get(position).getProduct_price() + " EGP");



    }
    //Set method of OnItemClickListener object
    public void setOnItemClickListener(CustomItemClickListener recyclerViewItemClickListner){
        this.listener=recyclerViewItemClickListner;
    }



    @Override
    public int getItemCount() {
        return prodcutsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image;
        TextView vendor_name,vendor_title,vendor_price;
        public int position=0;

        public ViewHolder(View itemView) {
            super(itemView);
            this.profile_image = itemView.findViewById(R.id.profile_image);
            this.vendor_name = itemView.findViewById(R.id.product_name);
            this.vendor_title = itemView.findViewById(R.id.product_snippet);
            this.vendor_price = itemView.findViewById(R.id.tv_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //When item view is clicked, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    listener.onItemClick(v, position);
                }
            });

        }}
}
