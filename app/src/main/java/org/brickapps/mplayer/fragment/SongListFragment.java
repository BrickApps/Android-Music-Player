package org.brickapps.mplayer.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.brickapps.mplayer.R;
import org.brickapps.mplayer.adapter.SongAdapter;
import org.brickapps.mplayer.base.BaseFragment;
import org.brickapps.mplayer.bean.Song;
import org.brickapps.mplayer.business.MusicPlayer;
import org.brickapps.mplayer.callback.RecyclerItemClickListener;
import org.brickapps.mplayer.rx.BaseSubscriber;
import org.brickapps.mplayer.util.LayoutUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MiJack on 2016/6/23.
 */
public class SongListFragment extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private SongAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = LayoutUtils.inflater(R.layout.fragment_song_list, container);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        adapter = new SongAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView,
                new RecyclerItemClickListener.SimpleItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Song song = adapter.getItem(position);
                        Toast.makeText(view.getContext(),"song",Toast.LENGTH_SHORT).show();
                    MusicPlayer.play(song);
                    }
                }));
        loadMusicRes();
        return view;
    }

    public void loadMusicRes() {
        Observable.create(new Observable.OnSubscribe<List<Song>>() {
            @Override
            public void call(Subscriber<? super List<Song>> subscriber) {
                String selectionStatement = "is_music=1 AND title != ''";
                final String songSortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
                Cursor cursor = getActivity().getContentResolver()
                        .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                null, selectionStatement, null, songSortOrder);

                List<Song> arrayList = new ArrayList();
                if ((cursor != null) && (cursor.moveToFirst()))
                    do {
                        long id = cursor.getLong(cursor.getColumnIndex("_id"));
                        String title = cursor.getString(cursor.getColumnIndex("title"));
                        String artist = cursor.getString(cursor.getColumnIndex("artist"));
                        String album = cursor.getString(cursor.getColumnIndex("album"));
                        int duration = cursor.getInt(cursor.getColumnIndex("duration"));
                        int trackNumber = cursor.getInt(cursor.getColumnIndex("track"));
                        long artistId = cursor.getInt(cursor.getColumnIndex("artist_id"));
                        long albumId = cursor.getLong(cursor.getColumnIndex("album_id"));

                        arrayList.add(new Song(id, albumId, artistId, title, artist, album, duration, trackNumber));
                    }
                    while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                subscriber.onNext(arrayList);
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<List<Song>>() {
                    @Override
                    public void onNext(List<Song> songs) {
                        adapter.setDatas(songs);

                    }
                });
    }
}
