package dev.vengateshm.java_practice.reactive_java.creation.flowable;

import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;

public class FlowableBackpressureExample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.create(emitter -> {
            for (int i = 1; i <= 10; i++) {
                // Emit the value only if the subscriber is ready
                if (emitter.requested() > 0) {
                    emitter.onNext(i);
                }
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);

        // Create a normal subscriber that will only accept 5 values
        // Create a backpressure-aware subscriber that will only accept 5 values
        FlowableSubscriber<Integer> subscriber = new FlowableSubscriber<Integer>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                this.subscription = s;
                // Request 5 values
                s.request(5); // No onComplete event emitted if not called
            }

            @Override
            public void onNext(Integer value) {
                // Only print the value if the subscriber is ready
                System.out.println("Next: " + value);
                // Request more values
                if (value == 5) {
                    this.subscription.request(5);
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        };

        flowable.subscribe(subscriber);
    }
}
