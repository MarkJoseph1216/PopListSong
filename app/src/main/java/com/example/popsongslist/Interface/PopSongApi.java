package com.example.popsongslist.Interface;

import com.example.popsongslist.Model.PopListModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PopSongApi {

    String URL = "https://itunes.apple.com/";

    @GET("search?term=Pop&primaryGenreName=Pop&limit=20")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<PopListModel> getSongResults(@Query("term") CharSequence searchTerm);
}
