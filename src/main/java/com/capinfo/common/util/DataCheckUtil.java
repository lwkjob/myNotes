 /**
 * Title:      首信支付平台
 * Description: 数据校验操作基础类
 * Copyright:    Copyright (c) 2006
 * Company:      Capinfo
 * @author Chen Jun E-commerce Dept
 * @version 1.0
 */
 package com.capinfo.common.util;

import java.io.*;

import java.net.*;

import java.util.*;

import java.text.*;

import javax.servlet.http.*;
import javax.servlet.*;

//import aspect.stinger.*;

import org.apache.commons.lang.StringUtils;

/**
 * 通用工具类，实现数据检查操作功能
 */
public class DataCheckUtil {
    /**
     * 判断一个对象是否为非空字符串
     * @param obj 要检查的对象
     * @return 经过预处理的参数，特殊字符转为中文
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
	
	
    public static String isString(Object obj) throws AppException {
        //判断是否为NULL
        if (obj == null) {
            throw (new AppException(40002001, null, "AppException", "参数为无效null"));
        }
        //判断是否字符串对象类型
        if (!(obj instanceof java.lang.String)) {
            throw (new AppException(40002002, null, "AppException", "参数对象类型错"));
        }

        String strobj = (String)obj;
        strobj.trim(); //清除前导和尾随空格
        //判断是否为空
        if (strobj.equalsIgnoreCase("")) {
            throw (new AppException(40002003, null, "AppException", "参数字符串为空"));
        }
        //进行HTML编码处理
        return HTMLEncode(strobj);
    }

    /**
     * 判断一个对象是否为非空字符串
     * @param obj 要检查的对象
     * @param objKey 要检查的对象键值
     * @return 经过预处理的参数，特殊字符转为中文
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static String isString(Object obj, String objKey) throws AppException {
        //判断是否为NULL                                
        if (obj == null) {
            throw (new AppException(40002001, objKey, "AppException", "参数为无效null"));
        }
        //判断是否为字符串对象类型
        if (!(obj instanceof java.lang.String)) {
            throw (new AppException(40002002, objKey, "AppException", "参数对象类型错"));
        }

        String strobj = (String)obj;
        strobj.trim(); //清除前导和尾随空格
        //判断是否为空
        if (strobj.equalsIgnoreCase("")) {
            throw (new AppException(40002003, objKey, "AppException", "参数字符串为空"));
        }
        //进行HTML编码处理
        return HTMLEncode(strobj);
    }

    /**
     * 判断一个对象是否为非空字符串
     * @param obj 要检查的对象
     * @param objKey 要检查的对象键值
     * @param maxLen 字符串长度
     * @return 经过预处理的参数，特殊字符转为中文
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static String isString(Object obj, String objKey, int maxLen) throws AppException {
        //判断是否为NULL
        if (obj == null) {
            throw (new AppException(40002001, objKey, "AppException", "参数为无效null"));
        }
        //判断是否为字符串对象类型
        if (!(obj instanceof java.lang.String)) {
            throw (new AppException(40002002, objKey, "AppException", "参数对象类型错"));
        }

        String strobj = (String)obj;
        strobj.trim(); //清除前导和尾随空格
        //判断是否为空
        if (strobj.equalsIgnoreCase("")) {
            throw (new AppException(40002003, objKey, "AppException", "参数字符串为空"));
        }
        //判断是否超过规定长度
        if (strobj.length() > maxLen) {
            throw (new AppException(40002004, objKey, "AppException", "参数字符串超过规定长度"));
        }
        //进行HTML编码处理
        return HTMLEncode(strobj);
    }

    /**
     * 判断一个对象是否为整数字符串
     * @param obj 要检查的对象
     * @return 经过预处理的参数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static int isIntString(Object obj) throws AppException {
        try {
            //进行数据类型转换处理
            Integer intobj = new Integer(isString(obj));
            return intobj.intValue();
        } catch (NumberFormatException expt) {
            throw (new AppException(40002002, null, "NumberFormatException", "参数对象类型错"));
        }
    }

    /**
     * 判断一个对象是否为整数字符串
     * @param obj 要检查的对象
     * @param objKey 要检查的对象键值
     * @return 经过预处理的参数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static int isIntString(Object obj, String objKey) throws AppException {
        try {
            //进行数据类型转换处理
            Integer intobj = new Integer(isString(obj));
            return intobj.intValue();
        } catch (NumberFormatException expt) {
            throw (new AppException(40002002, objKey, "NumberFormatException", "参数对象类型错"));
        }
    }

    /**
     * 判断一个对象是否为长整数字符串
     * @param obj 要检查的对象
     * @return 经过预处理的参数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static long isLongString(Object obj) throws AppException {
        try {
            //进行数据类型转换处理
            Long longobj = new Long(isString(obj));
            return longobj.longValue();
        } catch (NumberFormatException expt) {
            throw (new AppException(40002002, null, "NumberFormatException", "参数对象类型错"));
        }
    }

    /**
     * 判断一个对象是否为长整数字符串
     * @param obj 要检查的对象
     * @param objKey 要检查的对象键值
     * @return 经过预处理的参数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static long isLongString(Object obj, String objKey) throws AppException {
        try {
            //进行数据类型转换处理
            Long longobj = new Long(isString(obj, objKey));
            return longobj.longValue();
        } catch (NumberFormatException expt) {
            throw (new AppException(40002002, objKey, "NumberFormatException", "参数对象类型错"));
        }
    }

    /**
     * 检查一个整数是否表示时间（不包含日期）
     * @param num 要检查的整数
     * @return 检查后的整数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static int isTimeInt(int num) throws AppException {
        int hour = num / 10000;
        int minute = (num % 10000) / 100;
        int second = num % 100;
        if (num < 0 || hour > 23 || minute > 59 || second > 59) {
            throw (new AppException(40002005, null, "AppException", "该整数不表示时间"));
        } else {
            return num;
        }
    }

    /**
     * 检查一个整数是否表示时间（不包含日期）
     * @param num 要检查的整数
     * @param objKey 要检查的参数键值
     * @return 检查后的整数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static int isTimeInt(int num, String objKey) throws AppException {
        int hour = num / 10000;
        int minute = (num % 10000) / 100;
        int second = num % 100;
        if (num < 0 || hour > 23 || minute > 59 || second > 59) {
            throw (new AppException(40002005, objKey, "AppException", "该整数不表示时间"));
        } else {
            return num;
        }
    }

    /**
     * 检查一个字符串是否表示时间（不包含日期）
     * @param strSrc 要检查的字符串
     * @return 检查后的整数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static int isTimeString(String strSrc) throws AppException {
        //判断字符串长度
        if (strSrc.length() != 8) {
            throw (new AppException(40002006, null, "AppException", "时间字符串长度有误"));
        }
        //判断分隔字符
        if (strSrc.charAt(2) != ':' || strSrc.charAt(5) != ':') {
            throw (new AppException(40002007, null, "AppException", "该字符串不表示时间"));
        }

        String strTime = strSrc.substring(0, 2) + strSrc.substring(3, 5) + strSrc.substring(6);
        int iTime = 0;
        try {
            //转换处理为整数格式
            iTime = Integer.parseInt(strTime);
            return isTimeInt(iTime);
        } catch (NumberFormatException expt) {
            throw (new AppException(40002002, null, "NumberFormatException", "参数对象类型错"));
        }
    }

    /**
     * 检查一个字符串是否表示时间（不包含日期）
     * @param strSrc 要检查的字符串
     * @param objKey 要检查的参数键值
     * @return 检查后的整数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static int isTimeString(String strSrc, String objKey) throws AppException {
        //判断字符串长度
        if (strSrc.length() != 8) {
            throw (new AppException(40002006, objKey, "AppException", "时间字符串长度有误"));
        }
        //判断分隔字符
        if (strSrc.charAt(2) != ':' || strSrc.charAt(5) != ':') {
            throw (new AppException(40002007, objKey, "AppException", "该字符串不表示时间"));
        }

        String strTime = strSrc.substring(0, 2) + strSrc.substring(3, 5) + strSrc.substring(6);
        int iTime = 0;
        try {
            //转换处理为整数格式
            iTime = Integer.parseInt(strTime);
            return isTimeInt(iTime);
        } catch (NumberFormatException expt) {
            throw (new AppException(40002007, objKey, "NumberFormatException", "该字符串不表示时间"));
        }
    }

    /**
     * 检查一个字符串是否是yyyymmdd格式的日期字符串
     * @param strSrc 要检查的字符串
     * @return 检查后的日期字符串
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static String isDateIntString(String strSrc) throws AppException {
        //进行日期字符串拆分处理
        int dateint = isIntString(strSrc);
        int yearint = dateint / 10000;
        int monthint = (dateint % 10000) / 100;
        int dayint = dateint % 100;
        //检查年份是否合法
        if (yearint < 2000) {
            throw (new AppException(40002008, null, "AppException", "该字符串表示的年份早于2000年"));
        }
        //检查月份是否合法
        if (monthint < 1 || monthint > 12) {
            throw (new AppException(40002009, null, "AppException", "该字符串不表示日期，月份值有误"));
        }
        //检查每月30天情况
        if (monthint == 4 || monthint == 6 || monthint == 9 || monthint == 11) {
            if (dayint < 1 || dayint > 30) {
                throw (new AppException(40002010, null, "AppException", "该字符串不表示日期，小月只有1-30日"));
            }
        }
        //检查2月份情况
        else if (monthint == 2) {
            if ((yearint % 100) == 0) { //年份是整百
                if ((yearint % 400) == 0) {
                    if (dayint < 1 || dayint > 29) {
                        throw (new AppException(40002011, null, "AppException", "该字符串不表示日期，当年2月只有29天"));
                    }
                } else {
                    if (dayint < 1 || dayint > 28) {
                        throw (new AppException(40002012, null, "AppException", "该字符串不表示日期，当年2月只有28天"));
                    }
                }
            } else { //年份不是整百
                if ((yearint % 4) == 0) {
                    if (dayint < 1 || dayint > 29) {
                        throw (new AppException(40002011, null, "AppException", "该字符串不表示日期，当年2月只有29天"));
                    }
                } else {
                    if (dayint < 1 || dayint > 28) {
                        throw (new AppException(40002012, null, "AppException", "该字符串不表示日期，当年2月只有28天"));
                    }
                }
            }
        }
        //检查每月31天情况
        else {
            if (dayint < 1 || dayint > 31) {
                throw (new AppException(40002013, null, "AppException", "该字符串不表示日期，大月只有1-31日"));
            }
        }
        return dateint + "";
    }

    /**
     * 检查一个字符串是否是yyyymmdd格式的日期字符串
     * @param  strSrc 要检查的字符串
     * @param  objKey 要检查的参数键值
     * @return 检查后的日期字符串
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static String isDateIntString(String strSrc, String objKey) throws AppException {
        //进行日期字符串拆分处理
        int dateint = isIntString(strSrc, objKey);
        int yearint = dateint / 10000;
        int monthint = (dateint % 10000) / 100;
        int dayint = dateint % 100;
        //检查年份是否合法
        if (yearint < 2000) {
            throw (new AppException(40002008, objKey, "AppException", "该字符串表示的年份早于2000年"));
        }
        //检查月份是否合法
        if (monthint < 1 || monthint > 12) {
            throw (new AppException(40002009, objKey, "AppException", "该字符串不表示日期，月份值有误"));
        }
        //检查每月30天情况
        if (monthint == 4 || monthint == 6 || monthint == 9 || monthint == 11) {
            if (dayint < 1 || dayint > 30) {
                throw (new AppException(40002010, objKey, "AppException", "该字符串不表示日期，小月只有1-30日"));
            }
        }
        //检查2月份情况
        else if (monthint == 2) {
            if ((yearint % 100) == 0) { //年份是整百
                if ((yearint % 400) == 0) {
                    if (dayint < 1 || dayint > 29) {
                        throw (new AppException(40002011, objKey, "AppException", "该字符串不表示日期，当年2月只有29天"));
                    }
                } else {
                    if (dayint < 1 || dayint > 28) {
                        throw (new AppException(40002012, objKey, "AppException", "该字符串不表示日期，当年2月只有28天"));
                    }
                }
            } else { //年份不是整百
                if ((yearint % 4) == 0) {
                    if (dayint < 1 || dayint > 29) {
                        throw (new AppException(40002011, objKey, "AppException", "该字符串不表示日期，当年2月只有29天"));
                    }
                } else {
                    if (dayint < 1 || dayint > 28) {
                        throw (new AppException(40002012, objKey, "AppException", "该字符串不表示日期，当年2月只有28天"));
                    }
                }
            }
        }
        //检查每月31天情况
        else {
            if (dayint < 1 || dayint > 31) {
                throw (new AppException(40002013, objKey, "AppException", "该字符串不表示日期，大月只有1-31日"));
            }
        }
        return dateint + "";
    }

    /**
     * 检查字符串是否是表示日期的字符串
     * @param  strSrc 待检查字符串
     * @return 转换结果
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static java.util.Date StringToDate(String strSrc) throws AppException {
        //判断是否日期字符串格式
        isDateIntString(strSrc);
        //进行日期转换处理
        SimpleDateFormat spdate = new SimpleDateFormat("yyyyMMdd");
        ParsePosition pos = new ParsePosition(0);
        return spdate.parse(strSrc, pos);
    }

    /**
     * 检查字符串是否是表示金额的字符串
     * @param strSrc 待检查字符串
     * @return 转换结果
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static double StringToDouble(String strSrc) throws AppException {
        try {
            //进行数值类型转换处理
            return Double.parseDouble(strSrc);
        } catch (NumberFormatException expt) {
            throw (new AppException(40002014, null, "NumberFormatException", "该字符串不表示双精度数"));
        }
    }

    /**
     * 检查字符串是否是表示金额的字符串
     * @param  strSrc 待检查字符串
     * @param  objKey 参数键值
     * @return 转换结果
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static double StringToDouble(String strSrc, String objKey) throws AppException {
        try {
            //进行数值类型转换处理
            return Double.parseDouble(strSrc);
        } catch (NumberFormatException expt) {
            throw (new AppException(40002014, objKey, "NumberFormatException", "该字符串不表示双精度数"));
        }
    }

    /**
     * 检查一个字符串是否表示一个URL
     * @param  strSrc 待检查字符串
     * @return 检查结果
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static String isURLString(String strSrc) throws AppException {
        try {
            //进行对象类型转换处理
            URL url = new URL(strSrc);
            return strSrc;
        } catch (MalformedURLException expt) {
            throw (new AppException(40002015, null, "MalformedURLException", "该字符串不表示URL"));
        }
    }

    /**
     * 检查一个字符串是否表示一个URL
     * @param strSrc 待检查字符串
     * @param objKey 参数键值
     * @return 检查结果
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static String isURLString(String strSrc, String objKey) throws AppException {
        try {
            //进行对象类型转换处理      
            URL url = new URL(strSrc);
            return strSrc;
        } catch (MalformedURLException expt) {
            throw (new AppException(40002015, objKey, "MalformedURLException", "该字符串不表示URL"));
        }
    }

    /**
     * 将Y/N字符串转换为布尔值
     * @param strSrc 待转换字符串
     * @return 转换结果
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static boolean YNStringToBool(String strSrc) throws AppException {
        //进行字符串判断
        String strYN = isString(strSrc);
        //如果为真
        if (strYN.equalsIgnoreCase("Y")) {
            return true;
        }
        //如果为假
        else if (strYN.equalsIgnoreCase("N")) {
            return false;
        }
        //其他情况
        else { //既不是Y也不是N
            throw (new AppException(40002016, null, "AppException", "该字符串不表示布尔值Y/N"));
        }
    }

    /**
     * 将Y/N字符串转换为布尔值
     * @param strSrc 待转换字符串
     * @param objKey 参数键值
     * @exception AppException 数据不合理时抛出的应用程序异常
     * @return 转换结果
     */
    public static boolean YNStringToBool(String strSrc, String objKey) throws AppException {
        //进行字符串判断
        String strYN = isString(strSrc);
        //如果为真
        if (strYN.equalsIgnoreCase("Y")) {
            return true;
        }
        //如果为假    
        else if (strYN.equalsIgnoreCase("N")) {
            return false;
        }
        //其他情况    
        else { //既不是Y也不是N
            throw (new AppException(40002016, objKey, "AppException", "该字符串不表示布尔值Y/N"));
        }
    }

    /**
     * 将表示时间（不包含日期）的整数转换为字符串
     * @param num 要检查的整数
     * @return 转换后的时间字符串
     */
    public static String TimeIntToString(int num) {
        //进行数值拆分获取时间元素
        int hour = num / 10000;
        int minute = (num % 10000) / 100;
        int second = num % 100;
        //进行时间转换处理
        return FillZero((long)hour, 2) + ":" + FillZero((long)minute, 2) + ":" + FillZero((long)second, 2);
    }

    /**
     * 将一个整数头部补零，然后截断到指定长度
     * @param num 要补零的整数
     * @param len 补位后的长度
     * @return 填充结果
     */
    public static String FillZero(long num, int len) {
        String buf = "0000000000000000000"; //long型数最长19个0
        int ilen = len;
        buf = buf + num; //拼接字符串
        if (len < 1 || len > 19)
            ilen = 19; //限制最大长度
        return buf.substring(buf.length() - ilen);
    }

    /**
     * 实现ISO8859-1到GBK的字符集转换，特殊字符转为中文
     * @param value 使用ISO8859-1字符集的参数
     * @return 使用GBK字符集的结果
     */
    public static String ISOtoGBK(String value) {
        String strArg = value.trim(); //先删去首尾空格
        try {
            //判断是否为NULL
            if (value == null) {
                return "";
            }
            //进行编码转换处理
            else {
                strArg = new String(strArg.getBytes("ISO8859-1"), "GBK");
                return HTMLEncode(strArg);
            }
        } catch (UnsupportedEncodingException expt) {
            expt.printStackTrace();
            return "";
        }
    }

    /**
     * 对字符串作重新编码，特殊字符转为中文，被编码的字符包括 < > " ' & 空格
     * @param source 待编码的字符串
     * @return 编码结果
     */
    public static String HTMLEncode(String source) {
        StringBuffer buf = new StringBuffer(); //操作缓冲区
        int Idx = 0; //字符索引
        if (source != null) {
            //遍历检查是否有特征字符并做转换处理
            for (Idx = 0; Idx < source.length(); Idx++) {
                switch (source.charAt(Idx)) {
                case '<':
                    buf.append("＜");
                    break;
                case '>':
                    buf.append("＞");
                    break;
                case '"':
                    buf.append("＂");
                    break;
                case '\'':
                    buf.append("＇");
                    break;
                case '&':
                    buf.append("＆");
                    break;
                case ' ':
                    buf.append("");
                    break;
                default:
                    buf.append(source.charAt(Idx));
                }
            }
            return buf.toString();
        } else {
            return source;
        }
    }

    /* 对编码过的字符串进行解码
   * @param source 待解码的字符串
   * @return 编码结果
   */

    public static String HTMLDecode(String source) {
        StringBuffer buf = new StringBuffer(); //操作缓冲区
        int Idx = 0; //字符索引
        if (source != null) {
            //遍历检查是否有特征字符并做转换处理
            for (Idx = 0; Idx < source.length(); Idx++) {
                switch (source.charAt(Idx)) {
                case '＜':
                    buf.append("<");
                    break;
                case '＞':
                    buf.append(">");
                    break;
                case '＂':
                    buf.append("\"");
                    break;
                case '＇':
                    buf.append("\'");
                    break;
                case '＆':
                    buf.append("&");
                    break;
                case '':
                    buf.append(" ");
                    break;
                default:
                    buf.append(source.charAt(Idx));
                }
            }
            return buf.toString();
        } else {
            return "";
        }
    }

    /**
     * 字符串替换函数
     * @param str 原字符串
     * @param chgstr 被替换字符串
     * @param tostr 替换字符串
     * @return 编码结果
     * 
     * 如果“替换字符串”包含“被替换字符串”则直接返回
     */
    public static String replace(String str, String chgstr, String tostr) {
        int i = 0;
        if (str == null || chgstr == null || tostr == null || chgstr.equals("") || tostr.indexOf(chgstr) != -1) {
            return str;
        }

        String new_str = str;
        i = new_str.indexOf(chgstr);
        while (i >= 0) {
            new_str = new_str.substring(0, new_str.indexOf(chgstr)) + tostr + new_str.substring(new_str.indexOf(chgstr) + chgstr.length());
            i = new_str.indexOf(chgstr);
        }
        return new_str;
    }

    /**
     * 参数缺省替换函数
     * @param value 原字符串
     * @param defaultValue 缺省值
     * @return 转换结果
     */
    public static String valueOf(Object value, String defaultValue) {
        //判断是否为NULL
        if (value == null) {
            return defaultValue;
        }
        //进行对象类型转换处理
        else {
            return value.toString();
        }
    }

    public static String valueOf(Object value) {
        //判断是否为NULL
        if (value == null) {
            return "";
        }
        //进行对象类型转换处理
        else {
            return value.toString();
        }
    }

    /**
     * 采用STINGER进行输入参数正则表达式检查
     * @param config 页面配置变量
     * @param request 页面请求变量
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    /*public static void checkInput(ServletConfig config, HttpServletRequest request) throws AppException {
        //获取规则校验引擎实例
        Stinger stinger = Stinger.getInstance(config);
        ProblemList errors = null;
        try {
            //对页面请求变量进行规则校验
            errors = stinger.validate(request);
        } catch (FatalValidationException e) {
            //非法请求报文则会话失效
            request.getSession().invalidate();
            throw (new AppException(40002100, null, "AppException", "非法请求"));
        }
        if (errors.hasErrors()) {
            //存在参数格式错误则组织错误信息
            throw (new AppException(40002100, null, "AppException", stinger.format(errors)));
        }
    }
*/
    /**
     * 进行SQL注入规则检查
     * @param request 请求参数
     * @exception AppException 数据不合理时抛出的应用程序异常
     */
    public static void checkInject(HttpServletRequest request) throws AppException {
        //获取页面请求参数列表
        Enumeration params = request.getParameterNames();
        String paramName = "";
        String paramValue = "";
        //遍历页面请求参数项
        while (params.hasMoreElements()) {
            paramName = params.nextElement().toString();
            paramValue = valueOf(request.getParameter(paramName), "").toLowerCase();
            //判断是否存在特征非法字符串
            if (paramValue.indexOf("select ") > -1 || paramValue.indexOf("insert ") > -1 || paramValue.indexOf("delete from") > -1 || paramValue.indexOf("count(") > -1 || paramValue.indexOf("drop table") > -1 || paramValue.indexOf("update ") > -1 || paramValue.indexOf("truncate ") > -1 || paramValue.indexOf("asc(") > -1 || paramValue.indexOf("mid(") > -1 || paramValue.indexOf("char(") > -1 || paramValue.indexOf(" and ") > -1 || paramValue.indexOf(" or ") > -1)
                throw (new AppException(40002100, null, "AppException", "非法请求参数"));
        }
    }

    /**
     * 格式化字符串
     * @param src 被格式化的字符串
     * @param retLength 返回字符串长度
     * @param leftOrRight 左填充,还是右填充,1:源串左对齐, 其它为源串右对齐
     * @param fillStr 填充字符串,如果fillStr为空串,则不需要填充
     * @return 返回格式化后的字符串
     */
    public static String getFormatStr(String src, int retLength, int leftOrRight, String fillStr) {
        String retValue = "";
        String tmpStr = "";
        int srcLength = src.length();
        //原字符串超过返回字符串长度则进行截取操作
        if (srcLength > retLength) {
            retValue = src.substring(srcLength - retLength);
        }
        //返回字符串超过原字符串长度
        else {
            //判断填充方式
            if (fillStr.equals("")) {
                retValue = src;
            }
            //非空字符串填充
            else {
                for (int i = 0; i < retLength; i++)
                    tmpStr += fillStr;
                if (leftOrRight == 1) {
                    retValue = (src + tmpStr).substring(0, retLength);
                } else {
                    retValue = (tmpStr + src).substring((tmpStr + src).length() - retLength);
                }
            }
        }
        return retValue;
    }

    /**
     * 左填充字符
     * */
    public static String strFillLeft(String src, int retLength, String fillStr) {
        return getFormatStr(src, retLength, 0, fillStr);
    }

    /**
     * 右填充字符
     * 
     * */
    public static String strFillRight(String src, int retLength, String fillStr) {
        return getFormatStr(src, retLength, 1, fillStr);
    }

    /**
     * 数组列表处理
     * @param src 需处理的数组
     * @param seperator 列表分隔符
     * @return 返回列表格式的字符串
     */
    public static String getList(int[] src, String seperator) {
        String retValue = "";
        //判断原数组是否为空
        if (src != null) {
            //遍历原数组列表
            for (int i = 0; i < src.length; i++) {
                //根据数组项位置进行分别处理
                if (i == (src.length - 1))
                    retValue += src[i];
                else
                    retValue += src[i] + seperator;
            }
        }
        return retValue;
    }

    /**
     *重复字符串
     *@param src 原字符串
     *@param count 重复次数
     *@return 处理后的字符串
     * */
    public static String repeat(String src, int count) {
        //判断原字符串是否为null
        if (src == null)
            return null;
        String ret = "";
        //根据重复次数进行循环处理
        for (int i = 0; i < count; i++) {
            ret += src;
        }
        return ret;
    }

    /**
     *掩饰卡号的首尾几位字符
     *@param src 原字符串
     *@param headCount 需要掩饰src开始字符位数
     *@param tailCount 需要掩饰src结尾字符位数 
     *@return 处理后的字符串
     * */
    public static String remark(String src, int headCount, int tailCount) {
        //判断原字符串是否为NULL
        if (src == null) {
            return null;
        }
        //设置卡号屏蔽符号
        String s = "*";
        //检查是否符合掩饰卡号长度条件
        if (src.length() < (headCount + tailCount)) {
            return repeat(s, src.length());
        }

        StringBuffer strBuff = new StringBuffer(src);
        strBuff.replace(0, headCount, repeat(s, headCount));
        strBuff.replace(src.length() - tailCount, src.length(), repeat(s, tailCount));

        return new String(strBuff);
    }

    /**
     *掩饰卡号的中间字符
     *@param src 原字符串
     *@param headCount 需要显示src开始字符位数
     *@param tailCount 需要显示src结尾字符位数 
     *@return 处理后的字符串
     * */
    public static String remarkMiddle(String src, int headCount, int tailCount) {
        //判断原字符串是否为NULL
        if (src == null)
            return null;
        //设置卡号屏蔽符号
        String s = "*";
        //检查是否符合掩饰卡号长度条件
        if ((headCount + tailCount) > src.length())
            return src;

        StringBuffer strBuff = new StringBuffer(src);
        strBuff.replace(headCount, src.length() - tailCount, repeat(s, src.length() - (headCount + tailCount)));
        return new String(strBuff);
    }

    /**
     * 从xml格式的字符串中取elemnet对应的值
     * @param xmlStr xml格式的字符串
     * @param elementStr element字符串
     * @return elemnet对应的值
     * 
     */
    public static String getXMLElement(String xmlStr, String elementStr) {
        int p1 = xmlStr.toLowerCase().indexOf("<" + elementStr.toLowerCase() + ">");
        int p2 = xmlStr.toLowerCase().indexOf("</" + elementStr.toLowerCase() + ">");
        if (p1 == -1 || p2 == -1) {
            return "";
        } else {
            return xmlStr.substring(p1 + elementStr.length() + 2, p2);
        }
    }

    /**
     * 处理xml字符串中的敏感信息,可以处理相同标签多次出现
     * @param xmlStr xml字符串
     * @param remarkFields 敏感信息标签，多个用“,”分隔
     * @return 返回处理后的日志信息
     * 
     * */
    public static String getLogStr(String xmlStr, String remarkFields) {
        StringBuffer xmlBuf = new StringBuffer(xmlStr);
        String[] arr = PageUtil.splitStrToArray(remarkFields, ",");
        String s = "";
        int p1, p2; //标签的起止位置
        int d1, d2; //实际数据的起止位置

        for (int i = 0; i < arr.length; i++) {
            s = arr[i];
            int startP = 0; //查询时的起始位置
            while (true) {
                p1 = xmlStr.toLowerCase().indexOf("<" + s.toLowerCase() + ">", startP);
                p2 = xmlStr.toLowerCase().indexOf("</" + s.toLowerCase() + ">", p1);

                if (p1 != -1 && p2 != -1) {
                    d1 = p1 + s.length() + 2;
                    d2 = p2;
                    if (d2 - d1 >= 15) {
                        xmlBuf.replace(d1 + 6, d2 - 4, repeat("*", d2 - d1 - 6 - 4));
                    } else {
                        xmlBuf.replace(d1, d2, repeat("*", d2 - d1));
                    }
                } else {
                    //如果处理完毕退出此次循环
                    break;
                }
                startP = p2 + s.length() + 3;
            }
        }
        return xmlBuf.toString();
    }


    /**
     * 返回Map 字符信息，用于记录日志
     * @param map map对像
     * @return 返回信息
     * 
     * */
    public static String getLogStr(Map map) {
        return getLogStr(map, "");
    }

    /**
     * 返回Map 字符信息，用于记录日志
     * @param map 请求信息
     * @return 返回信息
     * 
     * */
    public static String getLogStr(Map map, String remarkPara) {
        String retVal = "-- Map info:\r\n";

        StringBuffer s = new StringBuffer();
        s.append(",").append(remarkPara.toLowerCase()).append(",");

        Iterator it = map.entrySet().iterator();
        String valueStr = "";
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            Object key = entry.getKey();
            Object value = entry.getValue() == null ? "" : entry.getValue();
            valueStr = value.toString();
            if (s.indexOf("," + key.toString().toLowerCase() + ",") != -1) {
                if (valueStr.length() >= 15) {
                    valueStr = valueStr.substring(0, 6) + DataCheckUtil.repeat("*", valueStr.toString().length() - 10) + valueStr.substring(valueStr.length() - 4);
                } else {
                    valueStr = DataCheckUtil.repeat("*", value.toString().length());
                }
            }
            retVal += " -" + key + ": " + valueStr + "\r\n";
        }
        return retVal;
    }

    /**
     * 返回request请求信息
     * @param request 请求信息
     * @return 返回信息
     * 
     * */
    public static String getLogStr(HttpServletRequest request) {
        return getLogStr(request, "");
    }

    /**
     * 
     * 返回request请求信息，如果指敏感信息的参数名，则用等长度的“*”代替原来的值
     * @param request 请求信息
     * @param remarkPara 用于掩码的参数名列表，用“,”分隔分个参数
     * @return 返回信息
     * 
     * */
    public static String getLogStr(HttpServletRequest request, String remarkPara) {
        String retVal = "";
        Enumeration keys = request.getParameterNames();
        String paraName = "";
        String paraVal = "";
        retVal = "\r\n## " + DateUtil.toStandar() + " ,Request Info:\r\n";
        retVal += " -IP: " + request.getRemoteAddr() + " \r\n";


        StringBuffer s = new StringBuffer();
        s.append(",").append(remarkPara.toLowerCase()).append(",");

        while (keys.hasMoreElements()) {
            paraName = keys.nextElement().toString();
            paraVal = request.getParameter(paraName) == null ? "" : request.getParameter(paraName);
            if (s.indexOf("," + paraName.toLowerCase() + ",") != -1) {
                if (paraVal.length() >= 15) {
                    paraVal = paraVal.substring(0, 6) + DataCheckUtil.repeat("*", paraVal.toString().length() - 10) + paraVal.substring(paraVal.length() - 4);
                } else {
                    paraVal = DataCheckUtil.repeat("*", paraVal.length());
                }
            }
            retVal += " -" + paraName + ": " + paraVal + "\r\n";
        }
        return retVal;
    }


    /**
     * 检查字符串是否包含特殊字符
     * @param str 待检查字符串
     * @return 是否包含
     * 
     */
    public static boolean haveSpecailChar(String str) {
        boolean haveChar = false;
        if (str != null) {
            if (str.indexOf(",") > -1 || str.indexOf("<") > -1 || str.indexOf(">") > -1 || str.indexOf("[") > -1 || str.indexOf(" ") > -1 || str.indexOf("]") > -1 || str.indexOf("，") > -1 || str.indexOf("\"") > -1 || str.indexOf(":") > -1 || str.indexOf(";") > -1 || str.indexOf("'") > -1 || str.indexOf("/") > -1 || str.indexOf("|") > -1 || str.indexOf("?") > -1 || str.indexOf("？") > -1 || str.indexOf("{") > -1 || str.indexOf("}") > -1)
                haveChar = true;
        }
        return haveChar;
    }

    /**
     *判断是否为数据字符串
     * @param numStr 待判断的字符串
     * @return 如果字符串都为数字字符组成，则返回true 否则返回false
     * */
    public static boolean isNumberStr(String numStr) {
        boolean retValue = true;
        StringBuffer strBuf = new StringBuffer(numStr);
        for (int i = 0; i < strBuf.length(); i++) {
            if (strBuf.charAt(i) < '0' || strBuf.charAt(i) > '9') {
                retValue = false;
            }
        }
        return retValue;
    }

    /**
     * 对加拿大邮编进行规范化处理
     * @param postCode 输入邮编
     * @return 规范处理后邮编
     * */
    public static String formatCanadaPostCode(String postCode) {
        String outPostCode = postCode;
        if (postCode != null) {
            outPostCode = postCode.trim();
            outPostCode = StringUtils.upperCase(postCode);
            if (outPostCode.length() >= 6 && outPostCode.indexOf(" ") == -1)
                outPostCode = outPostCode.substring(0, 3) + " " + outPostCode.substring(3);
        }
        return outPostCode;
    }

    /**测试方法*/
    public static void main(String[] args) {
        try {
            System.out.println(isNumberStr("01234567890"));
            String a = "CI1001@0000@处理成功@0100@51060615105848002836@64120000@1@116.46@电话:64120000应交:116.46元余额:0.00元(话费:116.46元信息费:0.00元)@f1cf6cc65c5d5ece5a76a0666b5710d4@";
            System.out.println(DataCheckUtil.replace(a, "@@", "@null@"));
            System.out.println(isTimeString("00:00:00"));
            System.out.println(haveSpecailChar("aaa:"));
            System.out.println(DataCheckUtil.replace("31068917|2008-12-23|19.60||1||||", "||", "|null|"));
            System.out.println(DataCheckUtil.HTMLDecode("").toString());
            System.out.println(DataCheckUtil.formatCanadaPostCode("中2A2B22"));
            String str_h="";
            
			if(String.valueOf("1").length()==1){
				str_h=DataCheckUtil.strFillLeft(String.valueOf("1"), 2, "0");
//				str_h="C0";
			}else{
				 str_h=String.valueOf(String.valueOf("1"));
			}    
			System.out.println("1的长度"+str_h);
            System.out.println(DataCheckUtil.strFillLeft("1", 2, "0"));

            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><packet><transName>IQSR</transName><Plain>MercCode=1000000010001000001|SettDate=20111207|AcqSsn=10019763|TermSsn=0000000004|TranAmt=0.01|CompFlag=00|RespCode=00000000</Plain><Signature>aed7fafdf108c3a9c25e925bb2493cfb7bbbad4a18c371e5b95f061f7f4aa4b8cb14cefcd46ae3352cca15abf88f4c56789baf9e19a463b601e53b4f44bc00a50936bbee11390e7762e66b6aa2566b4a0a8ba222a4cf90b7af428a23704a6e08bfd2e2adac443021e80483852cd59094accade7fb4f2a66fe3abd7015bc7cbdf</Signature></packet>";
            String plain = DataCheckUtil.getXMLElement(xml, "Plain");
            System.out.println(plain);

        } catch (Exception expt) {
            expt.printStackTrace();
        }
    }
}
