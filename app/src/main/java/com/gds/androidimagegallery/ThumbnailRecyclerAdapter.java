package com.gds.androidimagegallery;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by giuseppedistasi on 12/04/17.
 */

public class ThumbnailRecyclerAdapter extends RecyclerView.Adapter<ThumbnailRecyclerAdapter.ViewHolder> {

    private ArrayList <String> images;
    private Context context;
    private String folderName;
    private boolean isWeb;

    public ThumbnailRecyclerAdapter(Context context, ArrayList<String> images, boolean isWeb) {
        this.images = images;
        this.context = context;
        this.isWeb = isWeb;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.thumb_image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.thumb, parent, false);

        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {

        Glide.with(context)
                .load(images.get(listPosition))
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }}

