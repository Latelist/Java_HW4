import java.util.Random;
import java.util.Scanner;

// 4(со звездочкой) Реализовать стэк с помощью массива. 
// Нужно реализовать методы: size(), empty(), push(), peek(), pop().

public class Task4 {
    Task4() {
        int[] array = arrayGenerator(11);
        printer(array);
        Scanner in = new Scanner(System.in);
        boolean move = true;
        while(move == true) {
            System.out.println("Enter command: ");
            String command = in.nextLine();
            String[] splitted = command.split(" ");
            switch (splitted[0]) {
                case "size":
                    System.out.println(size(array));
                    break;
                case "empty":
                    if (empty(array)) {
                        System.out.println("Array is empty.");
                    } else {
                        System.out.println("Array isn't empty.");
                    }

                    break;
                case "push":
                    try {
                        int n = Integer.parseInt(splitted[1]);
                        array = push(array, n);
                    } catch (Exception e) {
                        System.out.println("I can't do this command.");
                    }
                    break;
                case "peek":
                    System.out.println(peek(array));
                    break;
                case "pop":
                    if (!empty(array)) {
                        System.out.println("There's nothing to pop, array is empty.");
                    } else {
                        array = pop(array);
                    }
                    break;
                case "exit":
                    move = false;
                    break;
                default: 
                    System.out.println("I can't do this command.");
                    break;
            }
            System.out.println();
        } 
        in.close();
    }
    

    public static int[] arrayGenerator(int n) {
        int[] array = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; ++i) {
            array[i] = rnd.nextInt(100);
        }
        return array;
    }

    public static String size(int[] array) {
        String res = "Currently array has " + array.length + " elements.";
        return res;
    }

    public static boolean empty(int[] array) {
        if (array.length > 0) return true;
        else return false;
    }

    public static int[] push(int[] array, int n) {
        int[] newArray = new int[array.length + 1];
        newArray[0] = n;
        for (int i = 1; i < newArray.length; ++i) {
            newArray[i] = array[i-1];
        }
        printer(newArray);
        return newArray;
    }

    public static int peek(int[] array) {
        return array[0];
    }

    public static int[] pop(int[] array) {
        int[] newArray = new int[array.length - 1];
        int popped = array[0];
        for (int i = 0; i < newArray.length; ++i) {
            newArray[i] = array[i+1];
        }
        System.out.println(popped);
        printer(newArray);
        return newArray;
    }

    public static void printer(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
