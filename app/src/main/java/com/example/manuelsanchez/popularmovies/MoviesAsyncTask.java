package com.example.manuelsanchez.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Manuel Sanchez on 9/16/15
 */
public class MoviesAsyncTask extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = MoviesAsyncTask.class.getSimpleName();

    private final Context mContext;

    public MoviesAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... params) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonString = null;


        try {
            final String FORECAST_BASE_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=[API_KEY]";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .build();

            URL url = new URL(builtUri.toString());


            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            movieJsonString = buffer.toString();


        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            processJsonMovieData(movieJsonString);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    private void processJsonMovieData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray movies = jsonObject.getJSONArray("results");
        for (int i = 0; i < movies.length(); i++) {
            Log.e(LOG_TAG, movies.getJSONObject(i).getString("title"));
        }
    }
}
