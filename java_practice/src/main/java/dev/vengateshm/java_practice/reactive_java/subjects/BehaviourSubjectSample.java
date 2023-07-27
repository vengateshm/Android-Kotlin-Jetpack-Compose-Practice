package dev.vengateshm.java_practice.reactive_java.subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

// Latest emitted value for new subscriber
public class BehaviourSubjectSample {
    public static void main(String[] args) {
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(new Observer<Integer>() {
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

        behaviorSubject.onNext(1);
        behaviorSubject.onNext(2);
        behaviorSubject.onNext(3);

        behaviorSubject.subscribe(new Observer<Integer>() {
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

        behaviorSubject.onComplete();
    }
}
