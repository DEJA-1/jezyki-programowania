import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Zad 02.
 */
public class Zad02 {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        printResults(measureAdditionTime(arrayList), TimeVariant.MILLIS, ListVariant.ARRAY);
        printResults(measureAdditionTime(linkedList), TimeVariant.MILLIS, ListVariant.LINKED);

        printResults(measureRemovalTime(arrayList), TimeVariant.NANO, ListVariant.ARRAY);
        printResults(measureRemovalTime(linkedList), TimeVariant.NANO, ListVariant.LINKED);

        printResults(measureModifyingTime(arrayList), TimeVariant.NANO, ListVariant.ARRAY);
        printResults(measureModifyingTime(linkedList), TimeVariant.NANO, ListVariant.LINKED);
    }

    /**
     * Funkcja mierzy czas dodawania 10000 elementów do danej listy.
     *
     * @param list the list
     * @return execution time (milliseconds)
     */
    public static long measureAdditionTime(List<Integer> list) {
        long startTime = System.currentTimeMillis();
        int n = 10000;

        for(int i = 0; i < n; i++) {
            list.add(i);
        }

        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    /**
     * Funkcja mierzy czas usuwania elementu na danym indexie z danej listy.
     *
     * @param list the list
     * @return execution time (nanoseconds)
     */
    public static long measureRemovalTime(List<Integer> list) {
        int indexToRemove = list.size()/2;

        long startTime = System.nanoTime();

        list.remove(indexToRemove);

        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    /**
     * Funkcja mierzy czas zmodyfifkowania środkowego elementu listy.
     *
     * @param list the list
     * @return execution time (nanoseconds)
     */
    public static long measureModifyingTime(List<Integer> list) {
        int indexToModify = list.size()/2;
        int newValue = 13214214;

        long startTime = System.nanoTime();

        list.set(indexToModify, newValue);

        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    /**
     * Printuje rezultat w zależności od wybranej jednostki czasu i listy.
     *
     * @param executionTime the execution time
     * @param timeUnit      the time unit
     * @param list          the list
     */
    public static void printResults(long executionTime, TimeVariant timeUnit, ListVariant list) {
        String displayTimeWihUnit = switch (timeUnit) {
            case MILLIS -> executionTime + "ms";
            case NANO -> executionTime + "ns";
        };

        String displayText = switch (list) {
            case ARRAY -> "Czas wykonania dla ArrayList wynosi ";
            case LINKED -> "Czas wykonania dla LinkedList wynosi ";
        };

        System.out.println(displayText + displayTimeWihUnit);
    }
}

/**
 * The enum Time variant.
 */
enum TimeVariant {
    /**
     * Millis time variant.
     */
    MILLIS,
    /**
     * Nano time variant.
     */
    NANO
}

/**
 * The enum List variant.
 */
enum ListVariant {
    /**
     * Array list variant.
     */
    ARRAY,
    /**
     * Linked list variant.
     */
    LINKED
}
