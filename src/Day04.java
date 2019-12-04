import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Day04 {

    private  static final int size = 10000;

    public static void main(String[] args) throws IOException {

        int min = 284639;
        int max = 748759;

//        int[] password = new int[] {2,2};
        int password = min;
//        int[] password = new int[] {2,8,4,6,3,9};
//        int[] password = new int[] {7,4,8,7,5,9};

        int counter1 = 0;
        int counter2 = 0;
        while (password < max) {
            int[] intPassword = transformToIntArray(password);
            if (isInOrderAscOrEqual(intPassword)) {
                if (matchSecond(intPassword)) {
//                    System.out.println(password);
                    counter2++;
                }
                if (hasConsecutive(intPassword)) {
//                    System.out.println(password);
                    counter1++;
                }
                password++;
            } else {
                for (int i = 0 ; i < intPassword.length -1; i++) {
                    if (intPassword[i] > intPassword[i+1]){
                        int j = i;
                        while (j < intPassword.length) {
                            intPassword[j++] = intPassword[i];
                        }
                        break;
                    }
                }
                password = Integer.valueOf(printString(intPassword));
                System.out.println("Reset to the first number correct");
            }
        }
        System.out.println(counter1);
        System.out.println(counter2);


    }

    private static boolean hasConsecutive(int[] password) {
        for (int i = 0; i < password.length -1; i++) {
            if (password[i] == password[i+1]) return true;
        }
        return false;
    }

    private static boolean matchSecond(int[] password) {
        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < password.length -1; i++) {
            int counter = 1;
            int j = i+1;
            while (i + counter < password.length && password[i] == password[i + counter]) {
                counter++;
            }
            if (!values.containsKey(password[i]) || values.get(password[i]) < counter) {
                values.put(password[i], counter);
            }
        }
        List<Integer> collect = values.keySet().stream().filter(key -> values.get(key) == 2).collect(Collectors.toList());
        return collect.size() > 0;
    }

    private static int[] transformToIntArray(int password){
        char[] charArray = ("" + password).toCharArray();
        int[] array = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            array[i] = Integer.valueOf("" + charArray[i]);
        }
        return array;
    }

    private static boolean isInOrderAscOrEqual(int[] password) {
        int index = 0;
        while (index < password.length -1) {
            if (password[index] > password[index+1])
                return false;
            index++;
        }
        return true;
    }

    private static String printString(int[] password){
        StringBuilder stringBuilder = new StringBuilder();

        for(int character: password) {
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    private static boolean isLess(int[] password, int max){
        String s = printString(password);
        return s.compareTo(String.valueOf(max)) <= 0;
    }

    private static boolean isGrater(int[] password, int max){
        String s = printString(password);
        return s.compareTo(String.valueOf(max)) > 0;
    }
}
