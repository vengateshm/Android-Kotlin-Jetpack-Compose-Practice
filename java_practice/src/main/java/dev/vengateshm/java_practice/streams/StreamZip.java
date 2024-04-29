package dev.vengateshm.java_practice.streams;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamZip {
    public static <T, U, R> Stream<R> zip(Stream<T> s1, Stream<U> s2,
                                          BiFunction<T,U,R> zip) {
        Iterator<T> i1 = s1.iterator();
        Iterator<U> i2 = s2.iterator();

        Iterable<R> r = () -> new Iterator() {
            @Override
            public boolean hasNext() {
                return i1.hasNext() && i2.hasNext();
            }

            @Override
            public Object next() {
                return zip.apply( i1.next(), i2.next());
            }
        };

        return StreamSupport.stream(r.spliterator(), false);
    }
}
