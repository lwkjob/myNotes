package com.lwk.guava.collections;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lwk on 2016/12/1.
 */
public class GuavaCollection {

    private Logger logger = LoggerFactory.getLogger(GuavaCollection.class);

    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(

            "red",

            "orange",

            "yellow",

            "green",

            "blue",

            "purple");

    public void immutableTest() {
        ImmutableSet<String> strings = ImmutableSet.copyOf(COLOR_NAMES);
        for (String s : strings) {
            logger.info(s);
        }
    }


    public static void main(String[] args) {
//        new GuavaCollection().immutableTest();

        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
    }


}
