package org.brickapps.mplayer.util;

import org.brickapps.mplayer.bean.Song;

import java.util.Collection;
import java.util.List;

/**
 * Created by MiJack on 2016/6/23.
 */
public class Utils {
    public static int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    public static <T> T get(List<T> list, int i) {
        int size = size(list);
        if (size == 0) {
            return null;
        }
        if (i >= 0 && i < size) {
            return list.get(i);
        } else {
            return null;
        }
    }
}
