import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }

    public static boolean isInteger(String s) {

        try {
            int iVal = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = str.split(" ");

        if (arr.length < 2) {
            throw new Exception("Строка не является математической операцией");
        }
        if (arr.length > 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if ((isInteger(arr[0]) && !isInteger(arr[2])) || (!isInteger(arr[0]) && isInteger(arr[2]))) {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        if (isInteger(arr[0]) && isInteger(arr[2])) {
            int result;
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[2]);

            if ((a <= 0 || a > 10) || (b <= 0 || b > 10)) {
                throw new Exception("Значения должны быть от 1 до 10 включительно");
            }

            result = getResult(arr, a, b);
            System.out.println(result);
        }

        if (!isInteger(arr[0]) && !isInteger(arr[2])) {
            switch (arr[0]) {
                case "I" -> arr[0] = "1";
                case "II" -> arr[0] = "2";
                case "III" -> arr[0] = "3";
                case "IV" -> arr[0] = "4";
                case "V" -> arr[0] = "5";
                case "VI" -> arr[0] = "6";
                case "VII" -> arr[0] = "7";
                case "VIII" -> arr[0] = "8";
                case "IX" -> arr[0] = "9";
                case "X" -> arr[0] = "10";
                default -> throw new Exception("Это значение меньше 0 или больше 10: " + arr[0]);
            }
            switch (arr[2]) {
                case "I" -> arr[2] = "1";
                case "II" -> arr[2] = "2";
                case "III" -> arr[2] = "3";
                case "IV" -> arr[2] = "4";
                case "V" -> arr[2] = "5";
                case "VI" -> arr[2] = "6";
                case "VII" -> arr[2] = "7";
                case "VIII" -> arr[2] = "8";
                case "IX" -> arr[2] = "9";
                case "X" -> arr[2] = "10";
                default -> throw new Exception("Это значение меньше 0 или больше 10: " + arr[2]);
            }
            int result;
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[2]);

            result = getResult(arr, a, b);
            if (result < 1) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            System.out.println(toRoman(result));
        }
    }

    private static int getResult(String[] arr, int a, int b) {
        int result;
        switch (arr[1]) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
            default -> throw new IllegalStateException("Недопустимый оператор: " + arr[1]);
        }
        return result;
    }
}