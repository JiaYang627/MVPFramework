package com.jiayang.mvp.mvpframework.utils

/**
 * @author ：张 奎
 * @date ：2020-04-17 10：32
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */

fun main() {


}

fun studyForList() {
    val mStringList = ArrayList<String>()

    for (i in 0..10) {
        mStringList.add(i.toString())
    }

    for (i in 10 downTo 0) {
        println(mStringList[i])
    }

}

fun studyLam() {

    val listOf = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val map = listOf.map {
        it.toUpperCase()
    }



    listOf.firstOrNull()
    for (fruit in map) {
        println(fruit)
    }

    val mapFilter = listOf.filter { it.length <= 5 }
            .map { it.toUpperCase() }
    for (fruit in mapFilter) {
        println("$fruit 的长度是${fruit.length}")
    }

    val with = with(StringBuilder()) {
        append("测试")
        append("with")
        toString()
    }
    println(with)

    val run = StringBuilder().run {
        append("测试")
        append("run")
        toString()
    }
    println(run)

    val apply = StringBuilder().apply {
        append("测试")
        append("apply")
    }
    println(apply.toString())

}


