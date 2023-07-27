package dev.vengateshm.java_practice.reactive_java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class TakeExample {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.range(1, 10);
        Observable<Integer> limitedObservable = observable.take(5);

        Disposable disposable = limitedObservable.subscribe(
                item -> System.out.println("Received Item: " + item),
                Throwable::printStackTrace,
                () -> System.out.println("Subscription Completed!")
        );
        disposable.dispose();
    }
}
