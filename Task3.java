// 3(со звездочкой) В калькулятор добавьте возможность отменить последнюю операцию. 
// Калькулятор сделать на основе программы разработанной на семинаре.

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Stack;

public class Task3 {
    static double res = 0;
    static Stack<Double> results = new Stack<>();
    static boolean anyResult = false;
    Task3() throws IOException {
        Scanner in = new Scanner(System.in);
        anyResult = false;
        boolean move = true;
        res = 0;
        double a = 0;
        double b = 0;
        String op = "";
        while (move == true) {
            if (anyResult == false) {
                // Если ранее не было получено результатов, просим ввести выражение
                System.out.println("Enter expression: ");
                String exp = in.nextLine();
                switch (exp) {
                    case "exit":
                        move = false;
                        break;
                    case "cancel":
                        System.out.println();
                        // Отменяем предыдущую операцию
                        if (results.size() > 1) {
                            results.pop();
                            res = results.pop();
                            System.out.println("Previous operation cancelled. Continue calculations: ");
                        } else {
                            System.out.println("Stack is empty. There is no return point.");
                            anyResult = false;
                        }
                        break;
                    default:
                        try {
                            // Разбиваем введенное выражение на составляющие
                            String[] els = exp.split(" ");
                            a = Double.parseDouble(els[0]);
                            op = els[1];
                            b = Double.parseDouble(els[2]);
                            // Вычисляем результат
                            res = calculator(a, op, b);
                            results.push(res);
                            anyResult = true;

                            // Формируем строку с результатом и выводим его
                            String result = stringGenerator(a, b, op, res);
                            System.out.println(result);
                            System.out.println();
                            // Записываем результат в лог-файл
                            logger("logOfCalculator.txt", result);
                        } catch (Exception e) {
                            System.out.println("I can't interpret your command. 1");
                            System.out.println();
                            String toLog = "Command not interpreted";
                            // Записываем ошибку в лог-файл
                            logger("LogOfCalculator.txt", toLog);
                        }
                }
            } else {
                // Если уже есть результат, продолжаем вычисления
                System.out.println("Continue calculations");
                System.out.print(res + " ");
                String exp = in.nextLine();
                switch (exp) {
                    case "exit":
                        move = false;
                        break;
                    case "":
                        anyResult = false;
                        System.out.println();
                        break;
                    case "cancel":
                        System.out.println();
                        // Отменяем предыдущую операцию
                        if (results.size() > 1) {
                            results.pop();
                            res = results.pop();
                            System.out.println("Previous operation cancelled. Continue calculations: ");
                        } else {
                            System.out.println("Stack is empty. There is no return point.");
                            anyResult = false;
                        }
                        break;
                    default:
                        try {
                            String[] els = exp.split(" ");
                            a = res;
                            op = els[0];
                            b = Double.parseDouble(els[1]);
                            // Вычисляем новый результат на основе предыдущего и новых введенных значений
                            res = calculator(a, op, b);
                            anyResult = true;
                            results.push(res);

                            // Формируем строку с новым результатом и выводим его
                            String result = stringGenerator(a, b, op, res);
                            System.out.println(result);
                            System.out.println();
                            // Записываем новый результат в лог-файл
                            logger("logOfCalculator.txt", result);
                        } catch (Exception e) {
                            System.out.println("I can't interpret your command. 3");
                            String toLog = "Command not interpreted";
                            // Записываем ошибку в лог-файл
                            logger("LogOfCalculator.txt", toLog);
                            System.out.println();
                            break;
                        }
                }
            }
        }
        in.close();
    }

    public static double calculator(double a, String op, double b) {
        double res = 0;

        // Выполнение математической операции в зависимости от оператора
        if (op.equals("+")) {
            res = a + b;
        } else if (op.equals("-")) {
            res = a - b;
        } else if (op.equals("*")) {
            res = a * b;
        } else if (op.equals("/")) {
            res = a / b;
        }
        return res;
    }

    public static String stringGenerator(double a, double b, String op, double res) {
        // Генерация строки с результатом вычислений
        String result = a + " " + op + " " + b + " = " + res;
        return result;
    }

    public static void logger(String filename, String message) throws IOException {
        // Запись сообщения в лог-файл
        FileWriter fw = new FileWriter(filename, true);
        String timestamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        String logmessage = timestamp + ":      " + message + "\n\n";
        fw.write(logmessage);
        fw.close();
    }

    public static boolean isDouble(String txt) {
        try {
            Double.parseDouble(txt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
