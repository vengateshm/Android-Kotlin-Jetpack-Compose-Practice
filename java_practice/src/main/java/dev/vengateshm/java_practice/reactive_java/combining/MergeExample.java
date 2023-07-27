package dev.vengateshm.java_practice.reactive_java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MergeExample {
    public static void main(String[] args) {
        Disposable disposable = Observable.merge(
                Observable.fromArray("Hello", "World"),
                Observable.fromArray("Java", "RxJava")
        ).subscribe(
                integer -> System.out.println("onNext " + integer),
                Throwable::printStackTrace,
                () -> System.out.println("onComplete"));
        disposable.dispose();
    }
}
