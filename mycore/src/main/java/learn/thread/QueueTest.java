package learn.thread;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import static org.junit.Assert.*;
import com.google.common.collect.Lists;
//import com.octo.captcha.engine.sound.utils.SoundToFile;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by dell-pc on 2015/12/23.
 */
public class QueueTest {
    public static void main   (String[] args) throws  Exception{
        BlockingQueue blockingQueue=new ArrayBlockingQueue(3);
//        blockingQueue.add(2222);
//        blockingQueue.add(2222) ; // 不阻塞直接报错

//        blockingQueue.put(1); //会阻塞
//        blockingQueue.put(1);

        System.out.println("剩余容量"+blockingQueue.remainingCapacity());

        System.out.println("offer222:"+blockingQueue.offer(1));
        boolean c =    blockingQueue.offer(2);
        boolean c2 =    blockingQueue.offer(3);
        boolean c1 =    blockingQueue.offer(4);

        System.out.println("剩余容量"+blockingQueue.remainingCapacity());

//        System.out.println(blockingQueue.remove(222));   //取走指定数据且不阻塞 返回ture和false
        System.out.println("remove555:"+blockingQueue.remove(5555));

        System.out.println("take:"+blockingQueue.take());  //tack会阻塞
        System.out.println("take:"+blockingQueue.take());

        System.out.println("poll:"+blockingQueue.poll(2, TimeUnit.SECONDS)); //poll等待2秒如果取不到返回null
        System.out.println("poll:" + blockingQueue.poll());



        System.out.println("peek():" + blockingQueue.peek());//取第一个元素 取不到返回null

        List<Integer> arraylist=new ArrayList<Integer>();

        blockingQueue.drainTo(arraylist);  //drainTo取出所有数据到list中
        System.out.println("队列还剩下什么：" + Joiner.on(",").join(arraylist));

        blockingQueue.offer(1);
        blockingQueue.offer(2);
        System.out.println("element:" + blockingQueue.element());//取第一个元素 取不到会报错

        Queue linkQueue=new LinkedBlockingQueue<>();
        linkQueue=new LinkedBlockingDeque<>();
        linkQueue=new LinkedTransferQueue<>();



    }



}
