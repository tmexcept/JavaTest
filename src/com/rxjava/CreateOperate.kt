package com.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import sun.rmi.runtime.Log
import io.reactivex.schedulers.Schedulers
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit




private fun testCreate() {
    println("************************")
    println("testCreate()")
    Observable.create(ObservableOnSubscribe<String> { emitter ->
        emitter.onNext("Event1")
        emitter.onNext("Event2")
        emitter.onComplete()
        emitter.onNext("Event3")
    }).subscribe(object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe--->")
        }

        override fun onNext(s: String) {
            println("onNext--->$s")
        }

        override fun onError(e: Throwable) {
            println("onError--->")
        }

        override fun onComplete() {
            println("onComplete--->")
        }
    })

}

internal var lock = Any()
fun testTimer(){
    //timer
    Observable.timer(3, TimeUnit.SECONDS, Schedulers.io()).subscribe(object : Observer<Long> {

        override fun onSubscribe(d: Disposable) {
            println("onSubscribe--->")
        }

        override fun onNext(s: Long) {
            println("onNext--->$s")
            println("当前线程--->" + Thread.currentThread().name)
        }

        override fun onError(e: Throwable) {
            println("onError--->$e")
        }

        override fun onComplete() {
            println("onComplete--->")
        }
    })

    synchronized(lock) {
        try {
            sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    println("主线程--->" + Thread.currentThread().name)
}

fun main() {
//    testCreate()
    testTimer()
    sleep(10)

}
