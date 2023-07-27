package dev.vengateshm.java_practice.reactive_java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MergeDelayErrorExample {
    public static void main(String[] args) {
        Disposable disposable = Observable.mergeDelayError(
                Observable.fromArray("1", "1"),
                Observable.error(new RuntimeException("Some error")),
                Observable.fromArray("3", "4")
        ).subscribe(
                integer -> System.out.println("onNext " + integer),
                Throwable::printStackTrace,
                () -> System.out.println("onComplete"));
        disposable.dispose();
        System.out.println("Done");
    }
}
