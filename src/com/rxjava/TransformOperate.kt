package com.rxjava

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit
import sun.rmi.runtime.Log


object TransformOperate {
    internal var count = 0

    /**
     * 下面的事件的发送过程，如果不设置 buffer 则需要发送四次，如果使用如下 buffer 进行转换，则只需发送两次
     *
     * 第1次接收的数据...size=2
     * ---Event1  ---Event2
     * 第2次接收的数据...size=2
     * ---Event3  ---Event4
     */
    private fun testBuffer1() {
        count = 0
        Observable.just("Event1", "Event2", "Event3", "Event4")
                .buffer(2)
                .subscribe { strings ->
                    count++
                    println("第" + count + "次接收的数据...size=" + strings.size)
                    for (str in strings) {
                        print("---$str  ")
                    }
                    println()
                }

    }

    /**
     * 第1次接收...size=3
    ---Event1   ---Event2   ---Event3
    第2次接收...size=3
    ---Event3   ---Event4   ---Event5
    第3次接收...size=1
    ---Event5
     */
    private fun testBuffer2() {
        println()
        println("************************")
        println("testBuffer2()")
        count = 0
        Observable.just("Event1", "Event2", "Event3", "Event4", "Event5")
                .buffer(3, 2)
                .subscribe { strings ->
                    count++
                    println("第${count}次接收...size=${strings.size}")
                    for (str in strings) {
                        print("---$str   ")
                    }
                }

    }

    /**
    accept--->this is Event1
    accept--->this is Event2
    accept--->this is Event3
    accept--->this is Event4
    map()解决的是一对一的变换，flatMap()解决的是一对多的变换。
     */
    private fun testMap() {
        Observable.just("Event1", "Event2", "Event3", "Event4")
                .map { s ->
                    "this is $s"
//                }.subscribe(Consumer<String> { s -> println("accept--->$s") })//带类型参数
//                }.subscribe({ s -> println("accept--->$s") })
                }.subscribe { s -> println("accept--->$s") }

    }

    /**
     * 当源 Observable 发出事件会相应的转换为可以发送多个事件的 Observable，
     * 这些 Observable 最终汇入同一个 Observable，然后这个 Observable 将这些事件统一发送出去
     *
     * flatMap能够保证事件接收的顺序
     */
    private fun testFlatMap() {
        println("************************")
        println("testFlatMap()")
        val observable = Observable.just("Event5", "Event6")
        Observable.just("Event1", "Event2", "Event3", "Event4")
                .flatMap { s -> observable }
                .subscribe { s -> println("accept--->$s") }

        println("************************")
        Observable.just("Event1", "Event2", "Event3", "Event4")
                .flatMap { s -> Observable.just(s) }
                .subscribe { s -> println("accept--->$s") }
    }


    /**
     * todo 运行有问题，没有打印出想要的结果
     ************************
    testConcatMap()
    intervalRange 1--->1
     ************************
     */
    private fun testConcatMap() {
        println("************************")
        println("testConcatMap()")
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                .subscribe { aLong -> println("intervalRange 1--->" + aLong!!) }
        sleep(10)//无效

        println("************************")
        Observable.intervalRange(1, 2, 0, 1, TimeUnit.SECONDS)
                .concatMap { it ->
                    var delay = 0
                    if (it.toInt() == 1) {
                        delay = 3
                    }
                    Observable.intervalRange(4, 4, delay.toLong(), 1, TimeUnit.SECONDS)
                }
                .subscribe { aLong -> println("intervalRange 2--->" + aLong!!) }

        sleep(10)
    }

    /**
     * todo 运行有问题，没有打印出想要的结果
     ************************
    todo 未添加sleep(10)
    testSwitchMap()

    todo 添加sleep(10)
    testSwitchMap()
    aLong-->1
    accept--->4
     ************************
     */
    private fun testSwitchMap(){
        println("************************")
        println("testSwitchMap()")
        Observable.intervalRange(1, 2, 0, 2, TimeUnit.SECONDS)
                .switchMap{aLong ->
                        println("aLong-->$aLong")
                        Observable.intervalRange(4, 4, 0, 1, TimeUnit.SECONDS)
                    }
                .subscribe { aLong -> println("accept--->" + aLong!!) }

        sleep(10)//无效

    }

    @JvmStatic
    fun main(args: Array<String>) {
//        testBuffer1()
//        testBuffer2()
//        testMap()
        testFlatMap()
//        testConcatMap()
//        testSwitchMap()
    }
}
