package com.eclubprague.cardashboard.core.utils;

import java.util.List;

/**
 * Created by Michael on 02.09.2015.
 */
public class ListUtils {

    public static <T> int getNthIndexOf(List<T> list, T elem, int n) {
        int skip = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(elem)) {
                if (skip++ == n) {
                    return i;
                }
            }
        }
        return -1;
    }
}
