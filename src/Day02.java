import java.io.IOException;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;


public class Day02 {

    private static final int END_PROGRAM = 99;
    private static final int ADD = 1;
    private static final int MULTIPLY = 2;


    public static void main(String[] args) throws IOException {

        ReadFile readFile = new ReadFile();
        int total = 0;

        for (String line : readFile.readFromInputStream()) {
            String[] split = line.split(",");
            Integer[] originalInput = new Integer[split.length];
            originalInput = Arrays.stream(line.split(",")).map(Integer::valueOf).collect(toList()).toArray(originalInput);

            runProgram(originalInput.clone(), 12, 2);

            step2(originalInput.clone());
        }
    }

    private static void runProgram(Integer[] originalInput, int noun, int verb) {
        Integer[] elements = originalInput.clone();
        elements[1] = noun;
        elements[2] = verb;
        System.out.println(intCodeProgram(elements));
    }

    private static void step2(Integer[] originalInput) {
        a1:
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                Integer[] deepCopy = originalInput.clone();
                deepCopy[1] = noun;
                deepCopy[2] = verb;
                if (intCodeProgram(deepCopy) == 19690720) {
                    System.out.println(100 * noun + verb);
                    break a1;
                }
            }
        }
    }

    private static Integer intCodeProgram(Integer[] elements) {
        int i = 0;
        while (i < elements.length - 4) {
            Integer code = elements[i];
            Integer noun = elements[elements[i + 1]];
            Integer verb = elements[elements[i + 2]];
            Integer index = elements[i + 3];
            if (END_PROGRAM == code) {
                i = elements.length;
            } else {
                Integer value = calculateResult(code, noun, verb);
                elements[index] = value;
                i += 4;
            }
        }
        return elements[0];
    }

    private static Integer calculateResult(Integer code, Integer a, Integer b) {
        switch (code) {
            case ADD:
                return a + b;
            case MULTIPLY:
                return a * b;
            default:
                return null;
        }
    }
}
