package com.developer.alfin.embededvideoapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movies implements Parcelable {

    private String title;
    private String releaseDate;
    private String overview;
    private String imgMovie;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImgMovie() {
        return "https://image.tmdb.org/t/p/w185" + imgMovie;
    }

    public void setImgMovie(String imgMovie) {
        this.imgMovie = imgMovie;
    }

    public static Creator<Movies> getCREATOR() {
        return CREATOR;
    }

    protected Movies(Parcel in) {
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Movies(JSONObject object) {
        try {
            String title = object.getString("title");
            String overview = object.getString("overview");
            String releaseDate = object.getString("release_date");
            String posterPath = object.getString("poster_path");

            this.title = title;
            this.releaseDate = releaseDate;
            this.overview = overview;
            imgMovie = posterPath;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
