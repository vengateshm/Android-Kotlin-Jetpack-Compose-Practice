package dev.vengateshm.java_practice.reactive_java.creation;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class ObservableExample {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.range(1, 5);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        observable.subscribe(observer);
    }
}
