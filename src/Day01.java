import java.io.IOException;

public class Day01 {

    public static void main(String[] args) throws IOException {

        ReadFile readFile = new ReadFile();
        int total = 0 ;

        for(String s: readFile.readFromInputStream()) {
            Integer mass = Integer.valueOf(s);
            total += calculateFuel(mass);
        }
        System.out.println(total);
    }

    private static int calculateFuel(int mass) {
        int total = 0;
        while ((mass / 3) - 2 > 0) {
            mass = (mass / 3) - 2;
            if (mass < 0 ) mass = 0;
            total += mass;

        }
        return total;
    }
}