package com.lwk.jvm.btrace;

import java.io.BufferedReader;
import java.io.InputStreamReader;

 

public class TargetClass

{

    public static void main(String[] args) throws Exception

    {  

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++)

        {

            reader.readLine();

            int a = (int) Math.round(Math.random() * 1000);

            int b = (int) Math.round(Math.random() * 1000);

            System.out.println(new Test2().test2add2(a, b));

        }

    }

}