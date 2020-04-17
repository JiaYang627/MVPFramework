package com.jiayang.mvp.mvpframework.utils

/**
 * @author ：张 奎
 * @date ：2020-04-17 10：32
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */

fun main() {


    studyFor()
}

fun studyFor() {
    val mStringList = ArrayList<String>()

    for (i in 0..10) {
        mStringList.add(i.toString())
    }

    for (i in 10 downTo 0) {
        println(mStringList[i])
    }

}


