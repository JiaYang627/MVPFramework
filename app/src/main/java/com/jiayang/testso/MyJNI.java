package com.jiayang.testso;

/**
 * @author ：张 奎
 * @date ：2019-09-11 10：01
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class MyJNI {

    static {
        System.loadLibrary("native-lib");
    }

    public static native int add(int a, int b);
}
