package dev.vengateshm.java_practice.reactive_java.transform;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MapFilterExample {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.range(1, 5);
        Disposable disposable = observable
                .map(value -> value * 2)
                .filter(value -> value % 3 == 0)
                .subscribe(
                        integer -> System.out.println("onNext " + integer),
                        Throwable::printStackTrace,
                        () -> System.out.println("onComplete"));
        disposable.dispose();
    }
}
