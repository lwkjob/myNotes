package com.lwk.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwenke on 16/7/27.
 */
public class HashTest {

    public static void main(String[] args) {
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        for (int i=1 ;i<100;i++){

            TestEntry testEntry=new TestEntry();
            testEntry.setAge(13432424);
            testEntry.setName("242442424"+i);
            objectObjectHashMap.put(testEntry,testEntry);
        }


        System.out.println("sff");

    }

}
