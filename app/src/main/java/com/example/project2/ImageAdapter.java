package com.example.project2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.List;

public class ImageAdapter extends BaseAdapter{

    private static final int PADDING = 5;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private Context mContext;
    private List<Integer> cIDs;

    //Default constructor to get grid view variables
    public ImageAdapter(Context c, List<Integer> ids) {
        mContext = c;
        this.cIDs = ids;
    }

    //Get size of grid
    @Override
    public int getCount() {
        return cIDs.size();
    }

    //get cell object
    @Override
    public Object getItem(int position) {
        return cIDs.get(position);
    }

    //get cell position
    @Override
    public long getItemId(int position) {
        return cIDs.get(position);
    }

    //Loops for every image and places them in a grid as cells
    //For this particular instance it will create a 2x3 grid
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if (imageView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
            imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        imageView.setImageResource(cIDs.get(position));
        return imageView;
    }
}
