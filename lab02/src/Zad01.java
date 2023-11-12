import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type Zad 01.
 */
public class Zad01 {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String[] inputArray = getInputData();
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        insertInputDataToSet(inputArray, hashSet);
        insertInputDataToSet(inputArray, treeSet);

        System.out.println(hashSet);
        System.out.println(treeSet);

        /*
        W HashSet kolejność elementów jest dowolna, natomaist w
        TreeSet elementy są posortowane rosnąco
         */
    }

    /**
    Funkcja przyjmuje ciąg liczb wprowadzanych z klawiatury po spacji,
    następnie zwraca tablicę wprowadzonych elementów
    @return String[] inputArray
     **/
    private static String[] getInputData() {
        Scanner scanner = new Scanner(System.in);

        try {

        } catch (Exception e) {
            System.out.println("Nieprawidlowe dane");
        }
        System.out.println("Wprowadz ciag liczb oddzielonych spacja: ");
        String input = scanner.nextLine();

        return input.split(" ");
    }

    /**
     Funkcja przyjmuje jako argument tablicę z inputem oraz set, następnie
     dodaje każdy element z tej tablicy do kolekcji.
     @param inputArray tablica zawierająca dane z inputu
     @param set set do którego zostaną dodane dane z inputa
     **/
    private static void insertInputDataToSet(String[] inputArray, Set<Integer> set) {
        for (String num : inputArray) {
            set.add(Integer.parseInt(num));
        }
    }

}
