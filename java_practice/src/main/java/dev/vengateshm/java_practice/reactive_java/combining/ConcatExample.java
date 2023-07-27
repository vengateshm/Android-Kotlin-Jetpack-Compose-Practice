package dev.vengateshm.java_practice.reactive_java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ConcatExample {
    public static void main(String[] args) {
        // Create two Observables that emit strings
        Observable<String> observable1 = Observable.just("Hello");
        Observable<String> observable2 = Observable.just("RxJava");

        // Concatenate the two Observables
        Disposable disposable = Observable.concat(observable1, observable2)
                .subscribe(
                        value -> System.out.println("onNext: " + value),
                        Throwable::printStackTrace,
                        () -> System.out.println("onComplete")
                );
        disposable.dispose();
    }
}
