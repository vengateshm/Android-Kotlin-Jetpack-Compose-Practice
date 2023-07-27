package dev.vengateshm.java_practice.reactive_java.subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.UnicastSubject;

// Only single observer allowed, caches all emissions and emits once
// a subscriber is available
public class UnicastSubjectExample {
    public static void main(String[] args) {
        UnicastSubject<Integer> unicastSubject = UnicastSubject.create();

        unicastSubject.onNext(1);
        unicastSubject.onNext(2);
        unicastSubject.onNext(3);

        unicastSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("First subscriber onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("First subscriber onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("First subscriber onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("First subscriber onComplete");
            }
        });

        unicastSubject.onNext(4);
        unicastSubject.onComplete();
    }
}
