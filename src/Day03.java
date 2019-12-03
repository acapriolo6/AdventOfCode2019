import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Day03 {

    private  static final int size = 10000;

    public static void main(String[] args) throws IOException {

        ReadFile readFile = new ReadFile();
        int i = 0;
        String[][] frontPanel = new String[size][size];
        initPanel(frontPanel);
//        printPanel(frontPanel);
        for (String line : readFile.readFromInputStream()) {
            List<Step> steps = readSteps(line);
            writeSteps(frontPanel, steps);
//            printPanel(frontPanel);
            System.out.println("Test");
        }
        int manhattanDistance = calculateManhattanDistance(frontPanel);
        System.out.println(manhattanDistance);
    }

    private static List<Step> readSteps(String line) {
        List<Step> steps = new LinkedList<>();
        String[] split = line.split(",");
        for (String step: split) {
            steps.add(new Step(step.substring(0,1),Integer.valueOf(step.substring(1))));
        }
        return steps;
    }

    private static void initPanel(String[][] frontPanel){
        for (int i = 0; i < frontPanel.length; i++) {
            for (int j = 0; j < frontPanel[i].length; j++)
                frontPanel[i][j] = ".";
        }
    }


    private static void writeSteps(String[][] frontPanel,List<Step> steps) {
//        int startPointI = size -2 , startPointJ = 1;
//        int pointerI = startPointI;
//        int pointerJ = startPointJ;
        int startPoint = 0;
        int pointerI = startPoint;
        int pointerJ = startPoint;
        for (Step step: steps) {
            int i,j;
            switch (step.direction){
                case "D":
                    i = pointerI + 1;j = pointerJ;
                    pointerI += step.stepNumber;
                    while (i <= pointerI) {
                        if (!frontPanel[i][pointerJ].equals(".")) {
                            frontPanel[i][pointerJ] = "X";
                        } else {
                            frontPanel[i][pointerJ] = "|";
                        }
                        i++;
                    }
                    break;
                case "U":
                    i = pointerI - 1;j = pointerJ;
                    pointerI -= step.stepNumber;
                    while (i >= pointerI) {
                        if (!frontPanel[i][pointerJ].equals(".")) {
                            frontPanel[i][pointerJ] = "X";
                        } else {
                            frontPanel[i][pointerJ] = "|";
                        }
                        i--;
                    }
                    break;
                case "L":
                    i = pointerI;j = pointerJ - 1;
                    pointerJ -= step.stepNumber;
                    while (j >= pointerJ) {
                        if (!frontPanel[pointerI][j].equals(".")) {
                            frontPanel[pointerI][j] = "X";
                        } else {
                            frontPanel[pointerI][j] = "-";
                        }
                        j--;
                    }
                    break;
                case "R":
                    i = pointerI;j = pointerJ + 1;
                    pointerJ += step.stepNumber;
                    while (j <= pointerJ) {
                        if (!frontPanel[pointerI][j].equals(".")) {
                            frontPanel[pointerI][j] = "X";
                        } else {
                            frontPanel[pointerI][j] = "-";
                        }
                        j++;
                    }
                    break;
            }
        }
    }

    private static void printPanel(String[][] frontPanel){
        for (int i = 0; i < frontPanel.length; i++) {
            for (int j = 0; j < frontPanel[i].length; j++)
                System.out.print(frontPanel[i][j] + " ");
            System.out.println("");
        }
    }

    private static int calculateManhattanDistance(String[][] frontPanel) {
        int lessX = size, lessY = size;
        int startPoint = 0;
        for (int i = 0; i < frontPanel.length; i++) {
            for (int j = 0; j < frontPanel[i].length; j++)
                if (frontPanel[i][j].equals("X")) {
                    if (i + j < lessX + lessY) {
                        lessX = i;
                        lessY = j;
                    }
                }
        }
        return lessX + lessY;
    }

    static class Step{
        private String direction;

        private int stepNumber;

        public Step(String direction, int stepNumber) {
            this.direction = direction;
            this.stepNumber = stepNumber;
        }

        public Step() {
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public int getStepNumber() {
            return stepNumber;
        }

        public void setStepNumber(int stepNumber) {
            this.stepNumber = stepNumber;
        }
    }
}
