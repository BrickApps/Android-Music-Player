package org.brickapps.mplayer.business;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by MiJack on 2016/6/23.
 */

public class MusicServiceImpl {
    private WeakReference<Service> service;
    private MediaPlayer mediaPlayer;

    public MusicServiceImpl(Service service) {
        this.service = new WeakReference<>(service);
    }

    public void init() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void play(long songId) {
        if (isPlay()) {
            stop();
        }
        try {
            ContentResolver contentResolver = service.get()
                    .getContentResolver();
            Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = contentResolver.query(uri, null, MediaStore.Audio.Media._ID + " = ?", new String[]{
                    String.valueOf(songId)
            }, null);
            if (cursor == null) {
                // query failed, handle error.
            } else if (cursor.moveToFirst() && cursor.getCount() == 1) {
                mediaPlayer.reset();
                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId);
                mediaPlayer.setDataSource(service.get().getApplicationContext(), contentUri);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        mediaPlayer.stop();
    }

    public boolean isPlay() {
        return mediaPlayer.isPlaying();
    }
}
