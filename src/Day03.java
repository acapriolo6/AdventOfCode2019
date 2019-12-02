import java.io.IOException;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class Day03 {

    public static void main(String[] args) throws IOException {

        ReadFile readFile = new ReadFile();
        int total = 0;

        for (String line : readFile.readFromInputStream()) {
            String[] split = line.split(",");
            Integer[] originalInput = new Integer[split.length];
            originalInput = Arrays.stream(line.split(",")).map(Integer::valueOf).collect(toList()).toArray(originalInput);

        }
    }
}
