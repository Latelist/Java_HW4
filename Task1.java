import java.util.LinkedList;
import java.util.Random;

// Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.


public class Task1 {
    Task1() {
        LinkedList<Integer> ll = llGenerator(14);
        LinkedList<Integer> rl = reverser(ll);
        System.out.println(ll);
        System.out.println(rl);

    }

    public static LinkedList<Integer> llGenerator(int num) {
        LinkedList<Integer> ll = new LinkedList<>();
        Random rnd = new Random();
        for (int i = 0; i < num; ++i) {
            ll.add(rnd.nextInt(100));
        }
        return ll;
    }

    private static LinkedList<Integer> reverser(LinkedList<Integer> ll) {
        LinkedList<Integer> reversed = new LinkedList<>();
        for (Integer integer : ll) {
            reversed.addFirst(integer);
        }
        return reversed;
    }
}
