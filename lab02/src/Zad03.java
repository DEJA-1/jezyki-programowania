import java.util.*;

/**
 * The type Zad 03.
 */
public class Zad03 {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        Map<Integer, Integer> treeMap = new TreeMap<>();

        fillMapsWithInputData(hashMap, treeMap);

        System.out.println(hashMap);
        System.out.println(treeMap);

        /*
        W HashMap kolejnosc par klucz-wartosc jest losowa, natomiast w przypadku
        TreeMapy pary sa posortowane rosnaco wzgledem kluczy.
         */
    }

    /**
     * Funkcja wypelnia mapy danymi wprowadzonymi przez uzytkownika z klawiatury
     * @param map1
     * @param map2
     */
    private static void fillMapsWithInputData(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aby zakonczyc, wprowadz klucz 0\n-----");
        while (true) {
            try {
                System.out.println("Podaj klucz: ");
                int key = scanner.nextInt();

                if (key == 0) {
                    break;
                }

                System.out.println("Podaj wartosc: ");
                Integer value = scanner.nextInt();

                map1.put(key, value);
                map2.put(key, value);
            } catch (Exception e) {
                System.out.println("Nieprawidlowe dane.");
                break;
            }
        }
    }

}
