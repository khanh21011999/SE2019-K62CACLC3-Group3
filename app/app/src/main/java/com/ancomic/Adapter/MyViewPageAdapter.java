package com.ancomic.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ancomic.Model.Link;
import com.ancomic.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyViewPageAdapter extends PagerAdapter {

    Context context;
    List<Link> linkList;
    LayoutInflater inflater;

    public MyViewPageAdapter(Context context, List<Link> linkList) {
        this.context = context;
        this.linkList = linkList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return linkList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View image_layout=inflater.inflate(R.layout.view_page_item,container,false);
        PhotoView page_image=(PhotoView)image_layout.findViewById(R.id.page_image);
        Picasso.get().load(linkList.get(position).getLink()).into(page_image);

        container.addView(image_layout);
        return image_layout;
    }
}
