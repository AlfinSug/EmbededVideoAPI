package com.developer.alfin.embededvideoapi.viewmodel;

import android.util.Log;
import android.view.View;

import com.developer.alfin.embededvideoapi.HomeActivity;
import com.developer.alfin.embededvideoapi.model.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY = "5fa175c782f2e05fa1e88d364797976e";
    private MutableLiveData<ArrayList<Movies>> listMutableLiveData = new MutableLiveData<>();

    public void setListMutableLiveData(final String moviesData) {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        final ArrayList<Movies> listMovie = new ArrayList<>();

        String base_url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";

        httpClient.get(base_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Movies movies = new Movies(object);
                        listMovie.add(movies);
                    }
                    listMutableLiveData.postValue(listMovie);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                HomeActivity.noInternet.setVisibility(View.VISIBLE);

                Log.d("Exception", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movies>> getMoviesData() {
        return listMutableLiveData;
    }
}
