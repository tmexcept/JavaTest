package com.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class CreateOperate {
    public static <T> T getSome() {
        return null;
    }

    public static void main(String[] args) {
        Function function = new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) throws Exception {
                return aLong + 1000;
            }
        };

        Observable.timer(3, TimeUnit.SECONDS, Schedulers.io())
                .map(function)
                .subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe--->");
            }

            @Override
            public void onNext(Long s) {
                System.out.println("onNext--->"+s);
                System.out.println("当前线程--->"+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError--->"+e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete--->");
            }
        });

        System.out.println("当前线程1--->"+Thread.currentThread().getName());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程2--->"+Thread.currentThread().getName());
    }
}
