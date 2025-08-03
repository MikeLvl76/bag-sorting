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
        generator.shapes(4);

        System.out.println(bag);
        // System.out.println(generator.getShapes());

        bag.insert(generator.getShapes().toArray(new Shape[4]));

        // for (Shape shape : generator.getShapes()) {
        //     //bag.insertInto(shape);
        //     bag.insertIntoColumn(0, shape);
        // }

        // for (int i = 0; i < bag.stackCount(); i++){
        //     bag.displayColumn(i);
        // }

        System.out.println(bag);
    }
}