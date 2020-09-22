package bova.interview.test1.erbis.logistics.dniper;

import java.math.BigInteger;
import java.util.*;

/*
Input:
Alice Jennifer Charlie Alice Charlie Jennifer Jennifer Jennifer

Output:
Jennifer 4
Alice 2
Charlie 2

// 1 parse string

// iterate collection of names

// fill map (Sorted Map)
// counter -> key, Sorted Set (with names) -> value

// iterate map by key
// store previous result
// is key is the same -> fill sorted set
// is keys are different -> iterate sorted set
 */
public class SortNames {

    public static void main(String[] args) {
        System.out.println("------------------------------");

        String input = "Alice Jennifer Charlie Alice Charlie Jennifer Jennifer Jennifer";
        Map<BigInteger, Set<String>> mainMap = new TreeMap<>();
        Map<String, BigInteger> tempMap = new TreeMap<>();

        String[] tokens = input.split(" ");
        int currentNameCounter;

        for (String t : tokens) {
            if(tempMap.get(t) == null) {
                currentNameCounter = 0;
            } else {
                currentNameCounter = tempMap.get(t).intValue();

                BigInteger currentNameCounter1 = tempMap.get(t);
            }

            currentNameCounter++;
            tempMap.put(t, BigInteger.valueOf(currentNameCounter));
        }

        Iterator iterator = tempMap.keySet().iterator();
        Set<String> tempSet;

        while(iterator.hasNext()) {
            String name   = (String) iterator.next();
            BigInteger value = (BigInteger) tempMap.get(name);

            System.out.print(name);
            System.out.println("");
            System.out.println(value);

            if(mainMap.get(value) == null) {
                tempSet = new TreeSet<>();
            } else {
                tempSet = mainMap.get(value);
            }
            tempSet.add(name);

            mainMap.put(value, tempSet);
        }

        Iterator iteratorMain = mainMap.keySet().iterator();

        System.out.println("------------------------------");
        while(iteratorMain.hasNext()) {
            BigInteger counter   = (BigInteger) iteratorMain.next();
            tempSet = (Set) mainMap.get(counter);

            System.out.print("counter=" + counter);
            System.out.println("");
            System.out.println("set size=" + tempSet.size());
        }

        // store previous result
        // is key is the same -> fill sorted set
        // is keys are different -> iterate sorted set
    }
}
