package bova.coursera.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic1 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(123);

        int expectedValue = 123;
        int newValue      = 234;
        boolean result = atomicInteger.compareAndSet(expectedValue, newValue);
        System.out.println(result);
    }
}
