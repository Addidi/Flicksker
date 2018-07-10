package com.example.addidi.flickster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.addidi.flickster.Models.Movie;
import com.example.addidi.flickster.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private static class ViewHolder{
        ImageView ivMovieImage;
        TextView tvTitle;
        TextView tvOverview;

    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position

        Movie movie = getItem(position);
        ViewHolder viewHolder;

        //check the existing view being reused
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.ivMovieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        // find the image view
       /* ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        // clear out image from convertView
        ivImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
*/
        //populate data
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
       // tvTitle.setText(movie.getOriginalTitle());
        //tvOverview.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath()).placeholder(R.drawable.pimg).into(viewHolder.ivMovieImage);
        //run convertView
        return convertView;
    }
}
