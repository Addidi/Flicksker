package com.example.addidi.flickster;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.addidi.flickster.Models.Movie;
import com.example.addidi.flickster.adapters.LandMovieArrayAdapter;
import com.example.addidi.flickster.adapters.MovieArrayAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    LandMovieArrayAdapter movieAdapterLand;
    ListView lvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        movieAdapterLand = new LandMovieArrayAdapter(this, movies);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            lvItems.setAdapter(movieAdapter);
            // ...
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lvItems.setAdapter(movieAdapterLand);
            // ...
        }

        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults=  response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    movieAdapterLand.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });

    }
}
