package dev.vengateshm.java_practice.reactive_java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ZipWithExceptionExample {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> observable2 = Observable.just(10, 20, 30, 40, 50);
        Observable<Integer> observableWithError = Observable.error(new RuntimeException("Something went wrong!"));

        Observable<String> combinedObservable = Observable.zip(
                observable1,
                observable2,
                observableWithError,
                (value1, value2, valueWithError) -> "Combined: " + value1 + ", " + value2 + ", " + valueWithError
        );

        Disposable disposable = combinedObservable.subscribe(
                value -> System.out.println("onNext: " + value),
                throwable -> System.err.println("onError: " + throwable.getMessage()),
                () -> System.out.println("onComplete")
        );
        disposable.dispose();
    }
}
