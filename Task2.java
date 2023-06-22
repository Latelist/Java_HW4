import java.util.LinkedList;

// Реализуйте очередь с помощью LinkedList со следующими методами:enqueue() - помещает элемент в конец очереди,
//  dequeue() - возвращает первый элемент из очереди и удаляет его,
//  first() - возвращает первый элемент из очереди, не удаляя.


public class Task2 {
    static LinkedList<Integer> ll = Task1.llGenerator(13);

    Task2() {
        
        System.out.println(ll);
        enqueue(28);
        dequeue();
        first();
    }

    public static void enqueue(int n) {
        ll.addLast(n);
        System.out.println(ll);
    }

    public static void dequeue() {
        int r = ll.pop();
        System.out.println(r);
        System.out.println(ll);
    }

    public static void first() {
        int r = ll.peek();
        System.out.println(r);
        System.out.println(ll);
    }

}


