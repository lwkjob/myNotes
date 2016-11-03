package com.lwk.jvm.btrace;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

 

@BTrace
public class TraceScript

{

       @OnMethod(clazz="com.lwk.jvm.btrace.TraceScript", method="test2add2", location=@Location(Kind.RETURN))
       public static void func(int a, int b, @Return int result)

       {

              jstack();

              println(strcat("para A: ", str(a)));

              println(strcat("para B: ", str(b)));

              println(strcat("result: ", str(result)));

       }

}