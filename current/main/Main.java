import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Double> map = Map.of("width", 2.0, "length", 6.0);
        Shape2D square = new Shape2D("square", map);
        System.out.println(square.getAttributes());
        square.calculateArea();
        System.out.println("Area of square: " + square.getArea());
        System.out.println("TODO: Main class");
    }
}