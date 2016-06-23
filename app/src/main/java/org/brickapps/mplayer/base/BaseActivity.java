package org.brickapps.mplayer.base;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.brickapps.mplayer.business.MusicPlayer;

/**
 * Created by MiJack on 2016/6/23.
 */

public class BaseActivity extends AppCompatActivity implements ServiceConnection {
    private MusicPlayer.ServiceToken token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = MusicPlayer.bind(this, this);
    }

    @Override
    protected void onDestroy() {
        MusicPlayer.unbindFromService(token);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
