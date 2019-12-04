import java.io.IOException;
import java.util.*;

import static java.lang.Math.abs;

public class Day03 {

    private  static final int size = 10000;

    public static void main(String[] args) throws IOException {

        ReadFile readFile = new ReadFile();
        int i = 0;
        List<Set<Point>> segments = new ArrayList<>();
        for (String line : readFile.readFromInputStream()) {
            List<Step> steps = readSteps(line);
            Set<Point> path = new HashSet<>();
            writeSteps(path, steps);
            segments.add(path);
            System.out.println("Test");
        }
        segments.get(0).retainAll(segments.get(1));
        Point point = segments.get(0).stream().filter(it -> !it.equals(new Point(0,0))).sorted((a, b) -> comparePoint(a, b)).findFirst().orElse(new Point(0, 0));
        System.out.println(calculateDistance(point));
    }
    private static int calculateDistance(Point a) {
        return abs(a.x) + abs(a.y);
    }

    private static int comparePoint(Point a, Point b) {
        if (calculateDistance(a) > calculateDistance(b) ) return 1;
        else if (calculateDistance(a) < calculateDistance(b) ) return -1;
        return 0;
    }

    private static List<Step> readSteps(String line) {
        List<Step> steps = new LinkedList<>();
        String[] split = line.split(",");
        for (String step: split) {
            steps.add(new Step(step.substring(0,1),Integer.valueOf(step.substring(1))));
        }
        return steps;
    }

    private static void writeSteps(Set<Point> path, List<Step> steps) {
        int startPoint = 0;
        int pointerI = startPoint;
        int pointerJ = startPoint;
        for (Step step: steps) {
            int startY = pointerJ;
            int startX = pointerI;
            switch (step.direction){
                case "U":
                    pointerJ += step.stepNumber;
                    while (startY <= pointerJ) {
                        path.add(new Point(startX, startY++));
                    }
                    break;
                case "D":
                    pointerJ -= step.stepNumber;
                    while (startY >= pointerJ) {
                        path.add(new Point(startX, startY--));
                    }
                    break;
                case "L":
                    pointerI -= step.stepNumber;
                    while (startX >= pointerI) {
                        path.add(new Point(startX--, startY));
                    }
                    break;
                case "R":
                    pointerI += step.stepNumber;
                    while (startX <= pointerI) {
                        path.add(new Point(startX++, startY));
                    }
                    break;
            }
        }
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

    static class Segment {
        Point startPoint;
        Point endPoint;

        public Segment(int startX, int startY, int endX, int endY) {
            this.startPoint = new Point(startX, startY);
            this.endPoint = new Point(endX, endY);
        }
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
