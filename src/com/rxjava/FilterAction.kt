package com.rxjava

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import sun.rmi.runtime.Log
import java.util.function.Consumer


fun main(){
    take()
}

val ints = listOf(1,  2, 3, 4, 5)
private fun take(){
    Observable.just(ints)
            .take(2)
            .subscribe{
                println("take $it")
            }

    Observable.fromArray(ints)
            .take(2)
            .flatMap {
                Observable.just(it)
            }
            .map {
                println("take map $it")
                it.toString()
            }
            .subscribe{
                println("take map String $it")
            }

    Observable.fromArray(1, 2, 3, 4)
            .take(2)
//            .flatMap {
//                Observable.just(it)
//            }
            .map {
                println("take map $it")
                it.toString()
            }
            .subscribe{
                println("take map String $it")
            }
//    Observable.fromArray(ints)
//            .take(2)
//            .map {
//                println("take map $it")
//                it
//            }
//            .subscribe{
//                println("take map Int $it")
//                println(it)
//            }
}

private fun takeLast(){
    //takeLast：获取后两个小区名
    Observable.just(1,  2, 3, 4, 5)
            .takeLast(2)
            .subscribe(object : Observer<Int> {
                override fun onError(e: Throwable) {
                    println("takeLast：onError：${e.message}")
                }

                override fun onComplete() {
                    println("takeLast：onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    println("takeLast：onSubscribe")
                }

                override fun onNext(t: Int) {
                    println("takeLast：onNext：$t")
                }
            })

}