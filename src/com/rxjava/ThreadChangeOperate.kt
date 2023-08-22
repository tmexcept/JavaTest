package com.rxjava

import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.lang.Thread.sleep

fun main() {
    /**
     * Schedule的几个线程调用函数（5个）：
    Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的schedule。
    Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
    Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
    行为模式和newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，
    可以重用空闲的线程,因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
    Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，
    即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，
    大小为 CPU 核数.不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
    AndroidSchedulers.mainThread()，Android 专用的,它指定的操作将在 Android 主线程运行。
    subscribeOn() 和 observeOn() 都做了线程切换的工作（图中的 “schedule…” 部位）。不同的是， subscribeOn() 的线程切换发生在 OnSubscribe 中，即在它通知上一级 OnSubscribe 时，这时事件还没有开始发送，因此 subscribeOn() 的线程控制可以从事件发出的开端就造成影响；而 observeOn() 的线程切换则发生在它内建的 Subscriber 中，即发生在它即将给下一级 Subscriber 发送事件时，因此 observeOn()控制的是它后面的线程。

    上游事件是怎么给弄到子线程里去的?
    就是直接把订阅方法放在了一个Runnable中去执行，这样就一旦这个Runnable在某个子线程执行，那么上游所有事件只能在这个子线程中执行了。

    线程间传递消息也是使用 Handler来实现。

    延伸：doOnSubscribe()
    虽然超过一个的 subscribeOn() 对事件处理的流程没有影响，但在流程之前却是可以利用的。
    onStart() 由于在 subscribe() 发生时就被调用了，因此不能指定线程，而是只能执行在 subscribe()
    被调用时的线程。这就导致如果 onStart() 中含有对线程有要求的代码（例如在界面上显示一个 ProgressBar，这必须在主线程执行），
    将会有线程非法的风险，因为有时你无法预测 subscribe() 将会在什么线程执行。
    而与 Subscriber.onStart() 相对应的方法 Observable.doOnSubscribe() 。
    它和Subscriber.onStart() 同样是在 subscribe() 调用后而且在事件发送前执行，
    但区别在于它可以指定线程。默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；
    而如果在 doOnSubscribe() 之后有subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程
    ————————————————
    版权声明：本文为CSDN博主「Vinson武」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    原文链接：https://blog.csdn.net/Wengwuhua/article/details/105893242
     */

    //todo  observeOn() 的多次调用，程序实现了线程的多次切换。不过，不同于 observeOn() ，
    //todo  subscribeOn() 的位置放在哪里都可以，但它是只能调用一次的。(因为只有第一次的SubscribeOn()是有效的)
    //todo  subscribeOn只对 observeOn之前的调用有效
    //todo  subscribeOn() 只有第一次切换有效


//    println("************************")
//    Observable.create(ObservableOnSubscribe<Int> { emitter ->
//        emitter.onNext(1)
//        emitter.onNext(2)
//        emitter.onNext(3)
//    }).map {
//        println("map ${Thread.currentThread().name}")
//        it.toString()
//    }.flatMap {
//        println("flatMap ${Thread.currentThread().name}")
//        Observable.just(it)
//    }.subscribe {
//        println(it)
//        println(Thread.currentThread().name)
//    }
    println("************************")
//    Observable.create(ObservableOnSubscribe<Int> { emitter ->
//        emitter.onNext(1)
//        emitter.onNext(2)
//        emitter.onNext(3)
//    }).subscribeOn(Schedulers.io())
//            .map {
//                println("map ${Thread.currentThread().name}")
//                it.toString()
//            }//.subscribeOn(Schedulers.newThread())
//            .flatMap {
//                println("flatMap ${Thread.currentThread().name}")
//                Observable.just(it)
//            }.observeOn(Schedulers.single())
//            .subscribe {
//                println(it)
//                println(Thread.currentThread().name)
//            }
    testSubscribeOn2()
    sleep(50)

}

fun testSubscribeOn(){
    /**
    map0 1 --> RxCachedThreadScheduler-1
    map1 11 --> RxCachedThreadScheduler-1
    map2 111 --> RxCachedThreadScheduler-1
    map0 2 --> RxCachedThreadScheduler-1
    map1 12 --> RxCachedThreadScheduler-1
    map2 112 --> RxCachedThreadScheduler-1
    result 1111 --> RxSingleScheduler-1
    result 1112 --> RxSingleScheduler-1
     */
    Observable.just(1, 2)
            .map(justOperator)
            .subscribeOn(Schedulers.io())//todo 此处设置会修改 observeOn 之前的线程
            .map(mapOperator)
            .observeOn(Schedulers.newThread())
            .map(mapOperator2) // IO 线程，由 observeOn() 指定
            .observeOn(Schedulers.single())
            .subscribe {
                println("result $it --> ${Thread.currentThread().name}")
            }  // Single 线程，由 observeOn() 指定

}

fun testSubscribeOn4(){
    /**
    map0 1 --> RxCachedThreadScheduler-1
    map0 2 --> RxCachedThreadScheduler-1
    map1 11 --> RxNewThreadScheduler-1
    map2 111 --> RxNewThreadScheduler-1
    map1 12 --> RxNewThreadScheduler-1
    map2 112 --> RxNewThreadScheduler-1
    result 1111 --> RxSingleScheduler-1
    result 1112 --> RxSingleScheduler-1
     */
    Observable.just(1, 2)
            .map(justOperator)
            .observeOn(Schedulers.newThread())
            .map(mapOperator)
            .subscribeOn(Schedulers.io())//todo 此处设置会修改 observeOn 之前的线程
            .map(mapOperator2) // IO 线程，由 observeOn() 指定
            .observeOn(Schedulers.single())
            .subscribe {
                println("result $it --> ${Thread.currentThread().name}")
            }  // Single 线程，由 observeOn() 指定

}

fun testSubscribeOn3(){
    /**
    map0 1 --> RxCachedThreadScheduler-1
    map1 11 --> RxCachedThreadScheduler-1
    map2 111 --> RxCachedThreadScheduler-1
    map0 2 --> RxCachedThreadScheduler-1
    map1 12 --> RxCachedThreadScheduler-1
    map2 112 --> RxCachedThreadScheduler-1
    result 1111 --> RxSingleScheduler-1
    result 1112 --> RxSingleScheduler-1
     */
    Observable.just(1, 2)
            .map(justOperator)
            .map(mapOperator)
            .observeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.io())//todo 此处设置会修改 observeOn 之前的线程
            .map(mapOperator2) // IO 线程，由 observeOn() 指定
            .observeOn(Schedulers.single())
            .subscribe {
                println("result $it --> ${Thread.currentThread().name}")
            }  // Single 线程，由 observeOn() 指定

}

fun testSubscribeOn2(){
    /**
    map0 1 --> RxCachedThreadScheduler-1
    map1 11 --> RxCachedThreadScheduler-1
    map2 111 --> RxCachedThreadScheduler-1
    map0 2 --> RxCachedThreadScheduler-1
    map1 12 --> RxCachedThreadScheduler-1
    map2 112 --> RxCachedThreadScheduler-1
    result 1111 --> RxSingleScheduler-1
    result 1112 --> RxSingleScheduler-1
     */
    Observable.just(1, 2)
            .map(justOperator)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .map(mapOperator)
            .subscribeOn(Schedulers.io())//todo 此处设置无效了
            .map(mapOperator2) // IO 线程，由 observeOn() 指定
            .observeOn(Schedulers.single())
            .subscribe {
                println("result $it --> ${Thread.currentThread().name}")
            }  // Single 线程，由 observeOn() 指定

}

fun testSubscribeOn1(){
    /**
    map0 1 --> RxCachedThreadScheduler-1
    map1 11 --> RxCachedThreadScheduler-1
    map2 111 --> RxCachedThreadScheduler-1
    map0 2 --> RxCachedThreadScheduler-1
    map1 12 --> RxCachedThreadScheduler-1
    map2 112 --> RxCachedThreadScheduler-1
    result 1111 --> RxSingleScheduler-1
    result 1112 --> RxSingleScheduler-1
     */
    Observable.just(1, 2)
            .map(justOperator) // 新线程，由 Schedulers.io() 指定
            .subscribeOn(Schedulers.io())
            .subscribeOn(Schedulers.newThread())//无效
            .map(mapOperator)
            .subscribeOn(Schedulers.single())//无效
            .map(mapOperator2) // 由 observeOn() 指定
            .observeOn(Schedulers.single())
            .subscribe {
                println("result $it --> ${Thread.currentThread().name}")
            }  // Single 线程，由 observeOn() 指定

}

fun testObserverOn(){
    /**
    map0 1 --> RxCachedThreadScheduler-1
    map0 2 --> RxCachedThreadScheduler-1
    map1 11 --> RxNewThreadScheduler-1
    map1 12 --> RxNewThreadScheduler-1
    map2 111 --> RxCachedThreadScheduler-2
    map2 112 --> RxCachedThreadScheduler-2
    result 1111 --> RxSingleScheduler-1
    result 1112 --> RxSingleScheduler-1
     */
    Observable.just(1, 2)
            .map(justOperator) // 新线程，由 subscribeOn() 指定
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .map(mapOperator) // 新线程，由 observeOn() 指定
            .observeOn(Schedulers.io())
            .map(mapOperator2) // IO 线程，由 observeOn() 指定
            .observeOn(Schedulers.single())
            .subscribe {
                println("result $it --> ${Thread.currentThread().name}")
            }  // Single 线程，由 observeOn() 指定

}

val justOperator = Function<Int, Int> {aLong ->
    println("map0 $aLong --> ${Thread.currentThread().name}")
    aLong + 10
}

val mapOperator = Function<Int, Int> {aLong ->
    println("map1 $aLong --> ${Thread.currentThread().name}")
    aLong + 100
}

val mapOperator2 = object : Function<Int, Int> {
    @Throws(Exception::class)
    override fun apply(aLong: Int): Int {
        println("map2 $aLong --> ${Thread.currentThread().name}")
        return aLong + 1000
    }
}