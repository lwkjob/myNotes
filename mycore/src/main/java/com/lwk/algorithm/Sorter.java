package com.lwk.algorithm;

import java.util.Comparator;


/**
*
*/
public interface Sorter {



  public <T extends Comparable<T>> void sort(T[] list);



  public <T> void sort(T[] list, Comparator<T> comp);
}