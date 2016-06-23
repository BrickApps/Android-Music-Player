package org.brickapps.mplayer.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.brickapps.mplayer.R;
import org.brickapps.mplayer.bean.Song;

/**
 * Created by MiJack on 2016/6/23.
 */
public class SongHolder extends RecyclerView.ViewHolder {

    TextView titleView;
    TextView infoView;

    public SongHolder(View itemView) {
        super(itemView);
        titleView=(TextView)itemView.findViewById(R.id.titleView);
        infoView=(TextView)itemView.findViewById(R.id.infoView);
    }

    public void bind(Song song) {
        titleView.setText(song.title);
        infoView.setText(song.albumName+" - "+song.artistName);
    }
}
