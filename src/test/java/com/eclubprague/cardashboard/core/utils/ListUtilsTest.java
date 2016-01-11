package com.eclubprague.cardashboard.core.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Michael on 11.01.2016.
 */
public class ListUtilsTest {

    @Test
    public void testGetNthIndexOf() throws Exception {
        List<String> list = new ArrayList<>();
        final int size = 10;
        for ( int i = 0; i < size; i++ ) {
            list.add( "#" + i );
        }
        List<String> testList = new ArrayList<>();
        for ( int i = 0; i < size; i++ ) {
            testList.addAll( list );
        }
        final int pos = 5; // pos < size
        final int n = 3; // n < size
        assertEquals( ( n - 1 ) * size + pos, ListUtils.getNthIndexOf( testList, list.get( pos ), n ) );
        assertEquals( -1, ListUtils.getNthIndexOf( testList, "#" + size, 1 ) );
    }
}