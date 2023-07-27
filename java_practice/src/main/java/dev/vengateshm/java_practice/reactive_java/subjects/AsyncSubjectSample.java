package dev.vengateshm.java_practice.reactive_java.subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.AsyncSubject;

// Emits only last item when onComplete called or on error
public class AsyncSubjectSample {
    public static void main(String[] args) {
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(new Observer<Integer>() {
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

        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);

        asyncSubject.subscribe(new Observer<Integer>() {
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

        asyncSubject.onComplete();
    }
}
