package com.example.popsongslist.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopList {

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;

    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    public PopList(String artistName, String previewUrl, String artworkUrl100, String primaryGenreName, String releaseDate, String trackName) {
        this.artistName = artistName;
        this.previewUrl = previewUrl;
        this.artworkUrl100 = artworkUrl100;
        this.primaryGenreName = primaryGenreName;
        this.releaseDate = releaseDate;
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
}
