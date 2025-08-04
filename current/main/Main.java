import java.util.ArrayList;
import java.util.Scanner;

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

    private static void commandLine() throws InterruptedException {
        try {
            System.out.println("Running in command line mode...\n");

            Thread.sleep(1000);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter bag size");
            int size = sc.nextInt();

            System.out.println("Enter number for stack count (the number of stacked shapes in bag as if it was piles)");
            int stackCount = sc.nextInt();

            System.out.println("How many shapes do you want to generate?");
            int number = sc.nextInt();

            System.out.println("Processing...");
            Thread.sleep(2000);

            Bag bag = new Bag(size, stackCount);
            ShapeGenerator generator = new ShapeGenerator();
            generator.shapes(number);

            System.out.println(bag);

            ArrayList<Shape> shapes = generator.getShapes();
            ArrayList<Shape> copy = new ArrayList<>(shapes);
            int index = 0;

            System.out.println("Colmuns index increments from 0 to the stack count - 1. Index increments from left to right");
    
            while(!copy.isEmpty()) {
                System.out.println();
                System.out.println(String.format("List of shapes: %s\n", copy.stream().map(shape -> shape.minimalDisplay()).toList()));
                Shape current = copy.get(index);

                System.out.println(String.format("Enter index to place this shape: %s", current.minimalDisplay()));
                int colIndex = sc.nextInt();
                boolean isIndexValid = colIndex >= 0 && colIndex < bag.stackCount();

                while(!isIndexValid) {
                    System.out.println(String.format("Index value must be within this range: [0, %d]", bag.stackCount() - 1));
                    colIndex = sc.nextInt();
                    isIndexValid = colIndex >= 0 && colIndex < bag.stackCount();
                }

                boolean inserted = bag.insert(colIndex, current);
                if (!inserted) {
                    System.out.println("Cannot insert to column! Please retry.");
                    continue;
                }
                copy.remove(index);
                System.out.println(bag);
            }

            sc.close();
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length > 0) {
            boolean random = args[0].equals("-r") || args[0].equals("--random");
            if (random) {
                random();
                return;
            }
        }
 
        commandLine();
        
    }
}