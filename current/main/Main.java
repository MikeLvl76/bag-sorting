import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Double> map = Map.of("width", 2.0, "length", 6.0);
        Shape2D square = new Shape2D("square", map);
        System.out.println(square.getAttributes());
        square.calculateArea();
        System.out.println("Area of square: " + square.getArea());

        Map<String, Double> map3D = Map.of("width", 2.0, "length", 6.0, "depth", 2.4);
        Shape3D rect = new Shape3D("rect", map3D);
        System.out.println(rect.getAttributes());
        rect.calculateVolume();
        System.out.println("Volume of rect: " + rect.getVolume());
        System.out.println("TODO: Main class");
    }
}