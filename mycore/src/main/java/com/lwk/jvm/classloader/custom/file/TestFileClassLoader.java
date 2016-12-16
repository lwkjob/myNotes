package com.lwk.jvm.classloader.custom.file;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * 不同类加载器  加载的同一份字节码 是不同的实例
 * Created by lwk on 2016/12/14.
 */
public class TestFileClassLoader {



    public void testClassIdentity(String classDataRootPath) throws Exception {
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath  );
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath );
        String className = "com.lwk.jvm.classloader.custom.file.Sample";
        Class<?> class1 = fscl1.loadClass(className);
        Object obj1 = class1.newInstance();
        System.out.println("obj1.getClass().getClassLoader() "+obj1.getClass().getClassLoader());
        Class<?> class2 = fscl2.loadClass(className);
        Object obj2 = class2.newInstance();
        System.out.println("obj2.getClass().getClassLoader() "+obj2.getClass().getClassLoader());
        System.out.println(obj1.equals(obj2));
        System.out.println("Thread.currentThread().getContextClassLoader() "+Thread.currentThread().getContextClassLoader());
        Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
        setSampleMethod.invoke(obj1, obj2);

    }

    public static void main(String[] args) throws Exception {
//        System.out.println(System.getProperty("user.dir"));
//        File directory = new File("");//设定为当前文件夹
//        System.out.println(directory.getCanonicalPath());//获取标准的路径
//        System.out.println(directory.getAbsolutePath());//获取绝对路径

        String currentPath = getCurrentPath();
        System.out.println(currentPath);

        new TestFileClassLoader().testClassIdentity("D:\\work\\code\\my\\MyGrowth\\mycore\\src\\main\\java\\com\\lwk\\jvm\\classloader\\custom\\file\\data\\");

    }

    public static String getCurrentPath() {

        String osName = System.getProperty("os.name").toLowerCase();

        String currentPath = TestFileClassLoader.class.getResource("/").getPath();

        if (osName.indexOf("windows") >= 0) {
            currentPath = StringUtils.substring(currentPath, 1);
        }

        return currentPath;
    }
}
