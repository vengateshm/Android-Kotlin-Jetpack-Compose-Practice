package dev.vengateshm.java_practice.reactive_java;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ZipExample {
    public static void main(String[] args) {
        // If any of the observable source completes,
        // then the combined observable source also completes
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(10, 20);

        Observable<String> combinedObservable = Observable.zip(
                observable1,
                observable2,
                (value1, value2) -> "Combined: " + value1 + " and " + value2
        );

        Disposable disposable = combinedObservable.subscribe(
                value -> System.out.println("onNext: " + value),
                Throwable::printStackTrace,
                () -> System.out.println("onComplete")
        );
        disposable.dispose();
    }
}
