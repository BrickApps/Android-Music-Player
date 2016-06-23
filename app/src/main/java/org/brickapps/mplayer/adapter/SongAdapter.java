package org.brickapps.mplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.brickapps.mplayer.R;
import org.brickapps.mplayer.bean.Song;
import org.brickapps.mplayer.holder.SongHolder;
import org.brickapps.mplayer.util.LayoutUtils;
import org.brickapps.mplayer.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MiJack on 2016/6/23.
 */
public class SongAdapter extends RecyclerView.Adapter<SongHolder> {
    private List<Song> songs;

    public SongAdapter() {
        songs = new ArrayList<>();
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutUtils.inflater(R.layout.holder_song, parent);
        SongHolder holder = new SongHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {
        Song song = getItem(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return Utils.size(songs);
    }

    public void setDatas(List<Song> datas) {
        this.songs = datas;
        this.notifyDataSetChanged();
    }

    public Song getItem(int position) {
        return Utils.get(songs, position);
    }
}
