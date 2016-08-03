package com.lwk.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwenke on 16/7/27.
 */
public class HashTest {

    public static void main(String[] args) {
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        for (int i=1 ;i<100;i++){

            TestEntry testEntry=new TestEntry();
            testEntry.setAge(13432424+i);
            testEntry.setName("242442424");
            objectObjectHashMap.put("sfsf"+i,testEntry);
        }


        System.out.println("sff");

    }

}
