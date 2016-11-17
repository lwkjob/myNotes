package com.lwk.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by liwenke on 2016/11/13.
 */
public class MultimapTest {

private static Logger logger= LoggerFactory.getLogger(MultimapTest.class);

    public static void main(String[] args) {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        myMultimap.put("女装", "内衣");
        myMultimap.put("女装", "羽绒服");
        myMultimap.put("女装", "风衣");
        myMultimap.put("男装", "皮夹克");
        // 获取key "女装"对应的list
        Collection<String> womenDressList = myMultimap.get("女装");

        for(String women:womenDressList){
            logger.info(women);

        }
        logger.info("删除key 女装 对应List中的 羽绒服\r\n");

        // 删除key "女装"对应List中的"羽绒服"
        myMultimap.remove("女装", "羽绒服");

        for(String women:womenDressList){
            logger.info(women);

        }
        logger.info("删除全部 女装");
        myMultimap.removeAll("女装");
        for(String women:womenDressList){
            logger.info(women);

        }

        logger.info("其他数据\r\n");
        Collection<String> mans = myMultimap.get("男装");
        for(String man:mans){
            logger.info(man);

        }
    }
}
