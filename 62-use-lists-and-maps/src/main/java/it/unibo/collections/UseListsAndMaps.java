package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    static final int ELEMENTS_TO_ADD = 100_000;
    static final int ELEMENTS_TO_READ = 1_000;

    private UseListsAndMaps() {
    }

    static public long timeCalucaltor(long time){
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        return millis;
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> listOfIntegers = new ArrayList<Integer>();
        for (int i = 1000; i < 2000; i++) {
            listOfIntegers.add(i);
        }
        // NOTA : Usare final, finchè non vedi che devi modificarla
        // NOTA : Nella dichiarazione usare l'interfaccia più generica 
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkedListOfIntegers = new LinkedList<>(listOfIntegers);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int last = linkedListOfIntegers.get(linkedListOfIntegers.size()-1);
        linkedListOfIntegers.set(linkedListOfIntegers.size()-1, linkedListOfIntegers.getFirst());
        linkedListOfIntegers.set(0, last);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (int i : listOfIntegers) {
            System.out.println(i);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        for (int i = 0; i < ELEMENTS_TO_ADD; i++ ) {
            listOfIntegers.add(0, i);
        }
        System.out.println("Time for add " + ELEMENTS_TO_ADD + " to ArrayList : " + timeCalucaltor(time));

        time = System.nanoTime();
        for (int i = 0; i < ELEMENTS_TO_ADD; i++ ) {
            linkedListOfIntegers.add(0, i);
        }
        System.out.println("Time for add " + ELEMENTS_TO_ADD + " to LinkedArrayList : " + timeCalucaltor(time));
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime();
        for (int i = 0; i < ELEMENTS_TO_READ; i++ ) {
            listOfIntegers.get(listOfIntegers.size()/2);
        }
        System.out.println("Time for read " + ELEMENTS_TO_READ + " to ArrayList : " + timeCalucaltor(time));

        time = System.nanoTime();
        for (int i = 0; i < ELEMENTS_TO_READ; i++ ) {
            linkedListOfIntegers.get(linkedListOfIntegers.size()/2);
        }
        System.out.println("Time for read " + ELEMENTS_TO_READ + " to LinkedArrayList : " + timeCalucaltor(time));
        
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> continentsPopulation = new HashMap<>();
        continentsPopulation.put("Africa", 1_110_635_000L);
        continentsPopulation.put("Americas", 972_005_000L);
        continentsPopulation.put("Antarctica", 0L);
        continentsPopulation.put("Asia", 4_298_723_000L);
        continentsPopulation.put("Europe", 742_452_000L);
        continentsPopulation.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the continents
         */
        long worldPopulation = 0;
        for (final long population : continentsPopulation.values()) {
            worldPopulation = worldPopulation + population;
        }
        System.out.println("Total mondial population : " + worldPopulation);
    }
}
