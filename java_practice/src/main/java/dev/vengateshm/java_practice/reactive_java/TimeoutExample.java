package dev.vengateshm.java_practice.reactive_java;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class TimeoutExample {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> observable = Observable
                .just(1, 2, 3, 4, 5)
                .delay(1, TimeUnit.SECONDS);

        Disposable disposable = observable
                .timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(
                        value -> System.out.println("onNext: " + value),
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                throwable.getMessage();
                            }
                        },
                        () -> System.out.println("onComplete")
                );
        disposable.dispose();
    }
}
