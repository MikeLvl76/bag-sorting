public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag(16, 4);
        ShapeGenerator generator = new ShapeGenerator();
        int number = 12;
        generator.shapes(number);

        System.out.println(bag);
        bag.insert(generator.getShapes().toArray(new Shape[number]));
        System.out.println(bag);
    }
}