import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Map<String, Double> map = Map.of("width", 2.0, "length", 6.0);
        // Shape2D square = new Shape2D("square", map);
        // System.out.println(square.getAttributes());
        // square.calculateArea();
        // System.out.println("Area of square: " + square.getArea());

        // Map<String, Double> map3D = Map.of("width", 2.0, "length", 6.0, "depth", 2.4);
        // Shape3D rect = new Shape3D("rect", map3D);
        // System.out.println(rect.getAttributes());
        // rect.calculateVolume();
        // System.out.println("Volume of rect: " + rect.getVolume());

        Bag bag = new Bag(16, 4);
        ShapeGenerator generator = new ShapeGenerator();
        generator.shapes(1);

        System.out.println(bag);
        // System.out.println(generator.getShapes());

        for (Shape shape : generator.getShapes()) {
            //bag.insertInto(shape);
            ArrayList<Shape> column = bag.insertIntoColumn(0, shape);
            System.out.println(column);
        }

        for (int i = 0; i < bag.stackCount(); i++){
            bag.displayColumn(i);
        }

        System.out.println(bag);
    }
}