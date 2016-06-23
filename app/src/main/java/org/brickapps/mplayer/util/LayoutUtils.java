package org.brickapps.mplayer.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MiJack on 2016/6/23.
 */
public class LayoutUtils {
    public static View inflater(int resource, ViewGroup root) {
        return inflater(root.getContext(), resource, root);
    }

    private static View inflater(Context context, int resource, ViewGroup root) {
        return inflater(context, resource, root, root == null);
    }

    public static View inflater(Context context, int resource, ViewGroup root, boolean attachToRoot) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(resource,root,attachToRoot);
    }

}
