package dev.vengateshm.java_practice.reactive_java;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ZipWithExceptionHandling {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> observable2 = Observable.just(10, 20, 30, 40, 50);

        Observable<Integer> observableWithError = Observable.error(new RuntimeException("Something went wrong!"));

        Observable<Integer> backupObservable = Observable.just(-1, -2, -3);
        Observable<Integer> recoveredObservable = observableWithError.onErrorResumeNext(throwable -> backupObservable);

        Observable<String> combinedObservable = Observable.zip(
                observable1,
                observable2,
                recoveredObservable,
                (value1, value2, valueWithError) -> "Combined: " + value1 + ", " + value2 + ", " + valueWithError
        );

        // Subscribe to the combined Observable
        Disposable disposable = combinedObservable.subscribe(
                value -> System.out.println("onNext: " + value),
                throwable -> System.err.println("onError: " + throwable.getMessage()),
                () -> System.out.println("onComplete")
        );
        disposable.dispose();
    }
}
