
package com.lwk.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/7/29.
 */
public class FileMd5Util {

    public static void main(String[] args) throws Exception {


        fileMd5();


    }

    public  static void fileMd5() throws Exception{

        String filePath="d://fundbook201603.sql";
        String filePath2="d://lwktest.txt";
//      String filePath="d://thrift-0.9.3.exe";
        long startTime = System.currentTimeMillis();
        File filein22 = new File(filePath);
        try {
//            System.out.println(getFileMD5String(filein22));
            System.out.println(fileMD5(filePath));
            System.out.println(fileMD5(filePath2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println((double) (endTime - startTime) / 1000);
    }




    public static String getFileMD5String(File file) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        //最大值不能超过 Integer.MAX_VALUE
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,file.length() );
        messageDigest.update(byteBuffer);

        return byteArrayToHex(messageDigest.digest());
    }

    public static String byteArrayToHex(byte[] byteArray) {

        // 首先初始化一个字符数组，用来存放每个16进制字符

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））

        char[] resultCharArray = new char[byteArray.length * 2];


        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去

        int index = 0;

        for (byte b : byteArray) {

            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];

            resultCharArray[index++] = hexDigits[b & 0xf];

        }
        return new String(resultCharArray);
    }
    public static String fileMD5(String inputFile) throws IOException {



        // 缓冲区大小（这个可以抽出一个参数）

        int bufferSize = 256 * 1024;

        FileInputStream fileInputStream = null;

        DigestInputStream digestInputStream = null;



        try {

            // 拿到一个MD5转换器（同样，这里可以换成SHA1）

            MessageDigest messageDigest =MessageDigest.getInstance("MD5");



            // 使用DigestInputStream

            fileInputStream = new FileInputStream(inputFile);

            digestInputStream = new DigestInputStream(fileInputStream,messageDigest);



            // read的过程中进行MD5处理，直到读完文件

            byte[] buffer =new byte[bufferSize];

            while (digestInputStream.read(buffer) > 0){
                // 获取最终的MessageDigest

                messageDigest= digestInputStream.getMessageDigest();

            }


            // 拿到结果，也是字节数组，包含16个元素

            byte[] resultByteArray = messageDigest.digest();



            // 同样，把字节数组转换成字符串

            return byteArrayToHex(resultByteArray);



        } catch (NoSuchAlgorithmException e) {

            return null;

        } finally {

            try {

                digestInputStream.close();

            } catch (Exception e) {

            }

            try {

                fileInputStream.close();

            } catch (Exception e) {

            }

        }

    }
}