package com.example.popsongslist.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popsongslist.Model.PopList;
import com.example.popsongslist.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import android.os.Handler;

import de.hdodenhof.circleimageview.CircleImageView;

public class PopListAdapter extends RecyclerView.Adapter<PopListAdapter.PopListViewHolder> {

    Context context;
    List<PopList> getPopSongs;
    String imageReplaceChar;
    MediaPlayer mediaPlayer = new MediaPlayer();
    Runnable runnable;
    Handler durationHandler = new Handler();

    public PopListAdapter(Context context, List<PopList> getPopSongs) {
        this.context = context;
        this.getPopSongs = getPopSongs;
    }

    @NonNull
    @Override
    public PopListAdapter.PopListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poplist_item, parent, false);
        return new  PopListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopListAdapter.PopListViewHolder holder, int position) {

        holder.txtArtistName.setText("Artist Name: " + getPopSongs.get(position).getArtistName());
        holder.txtSongTitle.setText("Song Title: " + getPopSongs.get(position).getTrackName());
        holder.txtGenre.setText("Genre: " + getPopSongs.get(position).getPrimaryGenreName());

        imageReplaceChar = getPopSongs.get(position).getArtworkUrl100().replace("100x100bb.jpg", "512x512-75.png");

        Picasso.get().load(imageReplaceChar).into(holder.imgAlbum);

        if (position == getPopSongs.size() -1) {
            holder.viewSeparator.setVisibility(View.INVISIBLE);
            holder.txtFooter.setVisibility(View.VISIBLE);
        }

        holder.btnPlayPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        mp.reset();
                        return false;
                    }
                });

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        holder.songDuration.setMax(mp.getDuration());
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            mediaPlayer.stop();
                        }
                        mp.start();
                        changeSeekBarDuration(holder.songDuration);
                    }
                });

                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(getPopSongs.get(position).getPreviewUrl());
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepareAsync();
                } catch (IllegalArgumentException e) {
                } catch (IllegalStateException e) {
                } catch (IOException e) {
                }
            }
        });

        holder.btnPausePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });

        holder.songDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeSeekBarDuration(SeekBar seekBar){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekBarDuration(seekBar);
                }
            };

            durationHandler.postDelayed(runnable, 1000);
        }
    }

    @Override
    public int getItemCount() {
        return getPopSongs.size();
    }

    public class PopListViewHolder extends RecyclerView.ViewHolder{

        TextView txtSongTitle, txtArtistName, txtGenre, txtFooter;
        CircleImageView imgAlbum;
        View viewSeparator;
        Button btnPlayPreview, btnPausePreview;
        SeekBar songDuration;

        public PopListViewHolder(@NonNull View itemView) {
            super(itemView);

            songDuration = itemView.findViewById(R.id.songDuration);
            btnPausePreview = itemView.findViewById(R.id.btnPausePreview);
            btnPlayPreview = itemView.findViewById(R.id.btnPlayPreview);
            viewSeparator = itemView.findViewById(R.id.viewSeparator);
            txtArtistName = itemView.findViewById(R.id.txtArtistName);
            txtSongTitle = itemView.findViewById(R.id.txtSongTitle);
            txtGenre = itemView.findViewById(R.id.txtGenre);
            txtFooter = itemView.findViewById(R.id.txtFooter);
            imgAlbum = itemView.findViewById(R.id.imgAlbum);

        }
    }
}
