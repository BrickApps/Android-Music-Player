package org.brickapps.mplayer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.brickapps.mplayer.R;
import org.brickapps.mplayer.base.BaseActivity;
import org.brickapps.mplayer.fragment.SongListFragment;

/**
 * Created by MiJack on 2016/6/23.
 */

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout,new SongListFragment())
                .commit();//.commitNow();
    }
}
