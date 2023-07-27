package dev.vengateshm.java_practice.reactive_java.subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class PublishSubjectSample {
    public static void main(String[] args) {
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.subscribe(new Observer<Integer>() {
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

        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);

        publishSubject.subscribe(new Observer<Integer>() {
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

        publishSubject.onNext(4);
        publishSubject.onNext(5);

        publishSubject.onComplete();
    }
}
