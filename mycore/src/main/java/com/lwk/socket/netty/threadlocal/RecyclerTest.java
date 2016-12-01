package com.lwk.socket.netty.threadlocal;

import io.netty.util.internal.RecyclableArrayList;

/**
 * Created by lwk on 2016/11/3.
 */
public class RecyclerTest {


    public void nettyRecyclerList(){
        byte[] data=new byte[1024];

        for (int i = 0,count=10000*100; i <count ; i++) {
            RecyclableArrayList list=RecyclableArrayList.newInstance();
            for (int j = 0; j <100 ; j++) {
                list.add(data);
            }
            list.recycle();
        }
    }
}
