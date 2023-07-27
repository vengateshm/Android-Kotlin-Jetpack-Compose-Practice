package dev.vengateshm.java_practice.reactive_java.transform;

import java.util.ArrayList;
import java.util.List;

import dev.vengateshm.java_practice.pojos.Address;
import dev.vengateshm.java_practice.pojos.Person;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class FlatMapExample {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Jon", "Snow", new Address("90 Grovers St", "New York", "3210")));
        personList.add(new Person("Monica", "Santiago", new Address("50 Oakland Avenue", "Florida", "4480")));
        Observable<Person> observable = Observable.fromIterable(personList);
        Disposable disposable = observable.flatMap(new Function<Person, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Person person) throws Throwable {
                return Observable.just(person.getAddress().formatAddress());
            }
        }).subscribe(address -> System.out.println("onNext : " + address), Throwable::printStackTrace, () -> System.out.println("onComplete"));
        disposable.dispose();
    }
}

