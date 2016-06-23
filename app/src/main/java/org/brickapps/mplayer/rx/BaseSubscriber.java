package org.brickapps.mplayer.rx;

import rx.Subscriber;

/**
 * Created by MiJack on 2016/6/23.
 */
public class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
