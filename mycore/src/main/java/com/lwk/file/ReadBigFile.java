package com.lwk.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ReadBigFile {


    public static String infilePath = "d://fundbook201603.sql";
    public static String outfilePath = "d://lwktest.txt";
//    public static String infilePath = "d://centos6.7-64.ova";

    public static void mappedRead() throws Exception {

//      final int BUFFER_SIZE = 0x300000;// 缓冲区大小为3M
        final int BUFFER_SIZE = 30 * 1024 * 1024;//

        File f = new File(infilePath);

        MappedByteBuffer inputBuffer = new RandomAccessFile(f, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, f.length());
        FileChannel rw = new FileOutputStream(outfilePath).getChannel();
        byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容
        int size = inputBuffer.capacity();
        int offset = 0;
        int count = 0;
        while (offset < size) {
            count = (size - offset) > BUFFER_SIZE ? BUFFER_SIZE : (size - offset);
            for (int i = 0; i < count; i++) {
                dst[i] = inputBuffer.get(offset + i);
            }
            ByteBuffer b = ByteBuffer.wrap(dst, 0, count);
            rw.write(b);
            offset += count;
            System.out.println(offset + "---" + size + "---" + count);
        }
    }

    public static void randomAccessRead() throws Exception {
        int bufSize = 1024;
        byte[] bs = new byte[bufSize];
        ByteBuffer byteBuf = ByteBuffer.allocate(1024);
        FileChannel channel = new RandomAccessFile(infilePath, "r").getChannel();
        while (channel.read(byteBuf) != -1) {
            int size = byteBuf.position();
            byteBuf.rewind();
            byteBuf.get(bs); // 把文件当字符串处理，直接打印做为一个例子。
            System.out.print(new String(bs, 0, size));
            byteBuf.clear();
        }

    }


    public static void main(String[] args) throws Exception {

//     FileUtils.copyFile(new File(infilePath),new File("d://lwktest.aa"));

//        mappedRead();
        randomAccessRead();


    }


}
