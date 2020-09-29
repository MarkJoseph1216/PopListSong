package com.example.popsongslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.popsongslist.Adapters.PopListAdapter;
import com.example.popsongslist.Interface.PopSongApi;
import com.example.popsongslist.Model.PopList;
import com.example.popsongslist.Model.PopListModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView popSongRecycleView;
    PopListAdapter popListAdapter;
    private List<PopList> songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songsList = new ArrayList<PopList>();
        popSongRecycleView = findViewById(R.id.popSongRecycleView);

        setupRecyclerView();
        loadSongList();
    }

    private void loadSongList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PopSongApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PopSongApi api = retrofit.create(PopSongApi.class);
        Call<PopListModel> call = api.getSongResults("Pop");

        call.enqueue(new Callback<PopListModel>() {
            @Override
            public void onResponse(Call<PopListModel> call, Response<PopListModel> response) {
                songsList = response.body().getSongsList();
                popListAdapter = new PopListAdapter(MainActivity.this, songsList);
                popSongRecycleView.setAdapter(popListAdapter);
            }

            @Override
            public void onFailure(Call<PopListModel> call, Throwable t) {

            }
        });
    }

    private void setupRecyclerView() {
        if (popListAdapter == null) {
            popSongRecycleView.setHasFixedSize(true);
            popSongRecycleView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            popListAdapter.notifyDataSetChanged();
        }
    }
}
