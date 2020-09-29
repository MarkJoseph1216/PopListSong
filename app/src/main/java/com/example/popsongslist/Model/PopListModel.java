package com.example.popsongslist.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopListModel {

    @SerializedName("resultCount")
    @Expose
    private int resultCount;
    @SerializedName("results")
    @Expose
    private List<PopList> songsList = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<PopList> getSongsList() {
        return songsList;
    }

    public void setSongsList(List<PopList> songsList) {
        this.songsList = songsList;
    }
}
