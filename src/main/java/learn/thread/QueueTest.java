package learn.thread;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import static org.junit.Assert.*;
import com.google.common.collect.Lists;
import com.octo.captcha.engine.sound.utils.SoundToFile;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by dell-pc on 2015/12/23.
 */
public class QueueTest {
    public static void main   (String[] args) throws  Exception{
        BlockingQueue blockingQueue=new ArrayBlockingQueue(2);
//        blockingQueue.add(2222);
//        blockingQueue.add(2222) ; // 不阻塞直接报错

//        blockingQueue.put(1); //会阻塞
//        blockingQueue.put(1);

        System.out.println("剩余容量"+blockingQueue.remainingCapacity());

        boolean b=     blockingQueue.offer(222);   //不阻塞 不报错 返回ture和false
        boolean c =    blockingQueue.offer(221);
        boolean c2 =    blockingQueue.offer(2241);
        boolean c1 =    blockingQueue.offer(224441);

        System.out.println("剩余容量"+blockingQueue.remainingCapacity());

//        System.out.println(blockingQueue.remove(222));   //取走指定数据且不阻塞
//        System.out.println(blockingQueue.remove(222));

        System.out.println(blockingQueue.take());  //tack会阻塞
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS)); //poll等待2秒如果取不到返回null
        System.out.println(blockingQueue.poll());

        List<Integer> arraylist=new ArrayList<Integer>();

        blockingQueue.drainTo(arraylist);  //drainTo取出所有数据到list中

        System.out.println(arraylist);
        System.out.println(Joiner.on(",").join(arraylist));

    }



}
