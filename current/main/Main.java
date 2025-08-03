public class Main {

    private static void random() throws InterruptedException {
        System.out.println("Running in random mode...\n\n");

        Thread.sleep(1000);

        Bag bag = new Bag(16, 4);
        ShapeGenerator generator = new ShapeGenerator();
        int number = 12;
        generator.shapes(number);

        System.out.println(bag);
        bag.insert(generator.getShapes().toArray(new Shape[number]));
        System.out.println(bag);
    }

    public static void main(String[] args) throws InterruptedException {
        boolean random = args[0].equals("-r") || args[0].equals("--random");

        if (random) {
            random();
        }
        
    }
}