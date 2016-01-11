package com.eclubprague.cardashboard.core.utils;

import android.util.Log;

import java.util.List;

/**
 * Created by Michael on 02.09.2015.
 */
public class ListUtils {

    /**
     * Returns nth index of element elem
     * @param list input list
     * @param elem element to be searched for
     * @param n starting from 1
     * @param <T>
     * @return
     */
    public static <T> int getNthIndexOf(List<T> list, T elem, int n) {
        int skip = 0;
//        Log.d("ListUtils", "Looking for: " + elem);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(elem)) {
//                Log.d("ListUtils", "EQUAL: " + list.get(i));
//                Log.d("ListUtils", "EQUAL: " + skip + " vs " + n);
                if (++skip == n) {
//                    Log.d("ListUtils", "EQUAL: returning " + i);
                    return i;
                }
            } else {
//                Log.d("ListUtils", "NOT EQUAL: " + list.get(i));
            }
        }
        return -1;
    }
}
