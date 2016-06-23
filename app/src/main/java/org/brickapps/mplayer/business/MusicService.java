package org.brickapps.mplayer.business;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import org.brickapps.mplayer.IMusicService;

import java.lang.ref.WeakReference;

public class MusicService extends Service {
    private final IBinder mBinder = new ServiceStub(this);
    MusicServiceImpl musicServiceImpl;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicServiceImpl = new MusicServiceImpl(this);
        musicServiceImpl.init();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void play(long songId) {
      musicServiceImpl.play(songId);
    }

    private static final class ServiceStub extends IMusicService.Stub {

        private final WeakReference<MusicService> mService;

        private ServiceStub(final MusicService service) {
            mService = new WeakReference<MusicService>(service);
        }

        @Override
        public void play(long id) throws RemoteException {
            mService.get().play(id);
        }
    }
}
