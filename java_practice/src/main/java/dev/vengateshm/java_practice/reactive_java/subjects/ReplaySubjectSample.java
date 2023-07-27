package dev.vengateshm.java_practice.reactive_java.subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class ReplaySubjectSample {
    public static void main(String[] args) {
//        ReplaySubject<Integer> replaySubject = ReplaySubject.create();
        ReplaySubject<Integer> replaySubject = ReplaySubject.createWithSize(1); // Last n elements replayed
        replaySubject.subscribe(new Observer<Integer>() {
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

        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);

        replaySubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Second subscriber onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("Second subscriber onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Second subscriber onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Second subscriber onComplete");
            }
        });

        replaySubject.onNext(4);
        replaySubject.onNext(5);

        replaySubject.onComplete();
    }
}
