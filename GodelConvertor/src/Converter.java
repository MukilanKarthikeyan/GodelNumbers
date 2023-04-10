import java.math.BigInteger;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Converter {
     static Map<Character, Integer> map = new HashMap<>();
     public static int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
             73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
             191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307,
             311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433,
             439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571,
             577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701,
             709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853,
             857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997,
             1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109,
             1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223};
    // 0 , ' , +, *, =, (, ), #, ~, @, xi

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        map.put('0', 1);
        map.put('\'',2);
        map.put('+', 3);
        map.put('*',4);
        map.put('=', 5);
        map.put('(',6);
        map.put(')',7);
        map.put('#', 8);
        map.put('~',9);
        map.put('@',10);
        map.put('x', 11);

        // prompt response
        System.out.println("Hello!\n" +
                "Welcome to the Godel Number Converter.\n" +
                "Write a mathematical expression using the valid symbols: 0 , ' , +, *, =, (, ), #, ~, @, xi\n" +
                "note that # is the implication symbol and xi here is x sub i which denotes the i-th variable.\n" +
                "(NOTE: raw numbers inputed are not converted to the standard godel number of |0\'\'...\'| notation.)\n" +
                "keep inputs less than 200 characters\n");
        System.out.print("input: ");
        char[] input = scan.nextLine().replaceAll("\\s", "").toCharArray();
        long[] res = new long[input.length];
        int j = 0;
        for (int i = 0; i < input.length; i++) {
            long num;
            if (input[j] == 'x') {
                num = 11;
                long sub = 0;
                while (Character.isDigit(input[j])) {
                    sub = (sub * 10) + input[j];
                    j++;
                }
                num += sub;
            } else {
                num = map.get(input[j]);
            }
            res[i] = num;
            j++;
        }
        printArr(res);
        System.out.println();
        System.out.println("godel number: " + calcGodel(res));
    }
    public static void printArr(long[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public static BigInteger calcGodel(long[] arr) {
        BigInteger res = new BigInteger("1");
        for (int i = 0; i < arr.length; i++) {
            res = res.multiply(new BigInteger(String.valueOf(primes[i])).pow((int) arr[i]));
        }
        return res;
    }
}
