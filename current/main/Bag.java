import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Bag {
    private ArrayList<Shape> content;
    private int stackCount;
    private final int MIN_BAG_SIZE = 12, MAX_BAG_SIZE = 36;
    private final int MIN_STACK_COUNT = this.MIN_BAG_SIZE / 3, MAX_STACK_COUNT = this.MAX_BAG_SIZE / 3;

    public Bag(int size, int stackCount) {
        if (size < this.MIN_BAG_SIZE) {
            throw new IllegalArgumentException(String.format("Size must be at least equals to %d", this.MIN_BAG_SIZE));
        }

        if (size > this.MAX_BAG_SIZE) {
            throw new IllegalArgumentException(String.format("Size must not exceed %d", this.MAX_BAG_SIZE));
        }

        if (stackCount < this.MIN_STACK_COUNT) {
            throw new IllegalArgumentException(String.format("Stack count must be at least equals to %d", this.MIN_STACK_COUNT));
        }

        if (stackCount > this.MAX_STACK_COUNT) {
            throw new IllegalArgumentException(String.format("Stack count must not exceed %d", this.MAX_STACK_COUNT));
        }

        if (size % stackCount != 0 || size <= stackCount) 
            throw new IllegalArgumentException(
                String.format("Incorrect values for size (%d) and stack count (%d). Size must be a multiple of stack count and cannot be similar.", size, stackCount)
            );

        this.stackCount = stackCount;
        this.content = new ArrayList<>(Collections.nCopies(size, null));
    }

    public ArrayList<Shape> getContent() {
        return this.content;
    }

    public Shape getItem(int index) {
        try {
            return this.content.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int stackCount() {
        return this.stackCount;
    }

    public ArrayList<Shape> getColumn(int colIndex) {
        ArrayList<Shape> column = new ArrayList<>();

        int rowCount = this.content.size() / this.stackCount;

        for (int row = 0; row < rowCount; row++) {
            int index = row * this.stackCount + colIndex;
            if (index < this.content.size()) {
                column.add(this.getItem(index));
            }
        }

        return column;
    }

    public long count() {
        return this.content.stream().filter(shape -> shape != null).count();
    }

    private double measureSize(int dimension, String measure) {
        if (dimension == 2) {
            if (!measure.equals("area")) {
                return 0.0;
            }
        } else if (dimension == 3) {
            if (!measure.equals("volume")) {
                return 0.0;
            }
        }

        return this.content.stream()
                    .filter(shape -> shape != null && shape.getDimension() == dimension)
                    .reduce(0.0, (result, shape) -> result + shape.getAttributeValue(measure), Double::sum);
    }

    private boolean hasColumnFull(int colIndex) {
        ArrayList<Shape> column = this.getColumn(colIndex);
        return column.stream().allMatch(shape -> shape != null);
    }

    private boolean isFull() {
        return this.content.stream().allMatch(shape -> shape != null);
    }

    private void updateColumn(int colIndex, ArrayList<Shape> column) {
        if (colIndex < 0 || colIndex >= this.stackCount) 
            throw new IllegalArgumentException(String.format("Index must be within range [%d, %d]", 0, this.stackCount - 1));

        
        for (int i = 0; i < column.size(); i++) {
            int eltIndex = i * this.stackCount + colIndex;
            this.content.set(eltIndex, column.get(i));
        }
    }

    // TODO: return boolean value if shape is indeed inserted
    public boolean insert(int colIndex, Shape shape) {
        if (colIndex < 0 || colIndex >= this.stackCount) return false;
        if (this.isFull() || shape == null) return false;
        if (this.hasColumnFull(colIndex)) return false;

        ArrayList<Shape> column = this.getColumn(colIndex);
        int index = column.size() - 2;

        while(index > - 1) {
            int previousIndex = index + 1;

            Shape current = column.get(index);
            Shape previous = column.get(previousIndex);

            // Case 1: empty column
            if (previous == null) {
                column.set(previousIndex, shape);
                this.updateColumn(colIndex, column);
                return true;
            }

            // Case 2: column contains at least one shape and
            // we check if a shape can be inserted by comparing 
            // their dimension and their area and/or volume
            if (current == null) {
                // The shape contained in column has a lower dimension than the new,
                // then we had the new to the list of non-inserted shapes and go to
                // next shape
                if (previous.getDimension() < shape.getDimension()) {
                    return false;
                }

                // The shape contained in column has a greater dimension than the new,
                // if the volume of the previous one is twice bigger than the area of the
                // new, then we can insert it and go to next shape and go to next empty place
                if (previous.getDimension() > shape.getDimension()) {
                    double volume = previous.getAttributeValue("volume");
                    double area = shape.getAttributeValue("area");
                    if (volume > 2 * area) {
                        column.set(index, shape);
                        this.updateColumn(colIndex, column);
                        return true;
                    }
                    return false;
                }

                // The shape contained in column and the new have the same dimension,
                // if the area or volume of the previous one is bigger than the area or the volume of the
                // new, then we can insert it and go to next shape and go to next empty place
                if (previous.getDimension() == shape.getDimension()) {
                    int dim = previous.getDimension();
                    if (dim == 2) {
                        double previousArea = previous.getAttributeValue("area");
                        double shapeArea = shape.getAttributeValue("area");
                        if (previousArea > shapeArea) {
                            column.set(index, shape);
                            this.updateColumn(colIndex, column);
                            return true;
                        }
                    } else if (dim == 3) {
                        double previousVolume = previous.getAttributeValue("volume");
                        double shapeVolume = shape.getAttributeValue("volume");
                        if (previousVolume > shapeVolume) {
                            column.set(index, shape);
                            this.updateColumn(colIndex, column);
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    private Shape[] insertIntoColumn(int colIndex, Shape ...shapes) {
        if (colIndex < 0 || colIndex >= this.stackCount) return shapes;
        if (this.isFull() || shapes.length == 0) return shapes;
        if (this.hasColumnFull(colIndex)) return this.insertIntoColumn(colIndex - 1, shapes);

        ArrayList<Shape> notInserted = new ArrayList<>();
        ArrayList<Shape> column = this.getColumn(colIndex);

        int index = column.size() - 2;
        int shapeIndex = 0;

        while(index > -1) {
            if (shapeIndex >= shapes.length) break;
            int previousIndex = index + 1;

            Shape shape = shapes[shapeIndex];
            Shape current = column.get(index);
            Shape previous = column.get(previousIndex);

            // Case 1: empty column
            if (previous == null) {
                column.set(previousIndex, shape);
                shapeIndex++;
                continue;
            }

            // Case 2: column contains at least one shape and
            // we check if a shape can be inserted by comparing 
            // their dimension and their area and/or volume
            if (current == null) {
                // The shape contained in column has a lower dimension than the new,
                // then we had the new to the list of non-inserted shapes and go to
                // next shape
                if (previous.getDimension() < shape.getDimension()) {
                    notInserted.add(shape);
                    shapeIndex++;
                    continue;
                }

                // The shape contained in column has a greater dimension than the new,
                // if the volume of the previous one is twice bigger than the area of the
                // new, then we can insert it and go to next shape and go to next empty place
                if (previous.getDimension() > shape.getDimension()) {
                    double volume = previous.getAttributeValue("volume");
                    double area = shape.getAttributeValue("area");
                    if (volume > 2 * area) {
                        column.set(index, shape);
                        index--;
                        shapeIndex++;
                        continue;
                    }
                    notInserted.add(shape);
                    shapeIndex++;
                    continue;
                }

                // The shape contained in column and the new have the same dimension,
                // if the area or volume of the previous one is bigger than the area or the volume of the
                // new, then we can insert it and go to next shape and go to next empty place
                if (previous.getDimension() == shape.getDimension()) {
                    int dim = previous.getDimension();
                    if (dim == 2) {
                        double previousArea = previous.getAttributeValue("area");
                        double shapeArea = shape.getAttributeValue("area");
                        if (previousArea > shapeArea) {
                            column.set(index, shape);
                            index--;
                            shapeIndex++;
                            continue;
                        }
                    } else if (dim == 3) {
                        double previousVolume = previous.getAttributeValue("volume");
                        double shapeVolume = shape.getAttributeValue("volume");
                        if (previousVolume > shapeVolume) {
                            column.set(index, shape);
                            index--;
                            shapeIndex++;
                            continue;
                        }
                    }
                    notInserted.add(shape);
                    shapeIndex++;
                    continue;
                }
            }
            index--;
            continue;
        }
        this.updateColumn(colIndex, column);
        Shape[] notInsertedArray = notInserted.toArray(new Shape[notInserted.size()]);
        if (notInserted.size() > 0) {
            return this.insertIntoColumn(colIndex - 1, notInsertedArray);
        }
        return notInsertedArray;
    }

    public void insert(Shape ...shapes) {
        int colIndex = this.stackCount() - 1;
        Shape[] notInserted = this.insertIntoColumn(colIndex, shapes);
        System.out.println(String.format("Remaining shapes (%d): %s\n", notInserted.length, Arrays.toString(notInserted)));
    }

    public void displayColumn(int colIndex) {
        ArrayList<Shape> column = this.getColumn(colIndex);
        System.out.println(column.stream().map(shape -> shape == null ? "x" : shape.minimalDisplay()).toList());
    }

    public String toString() {
        final int cellWidth = 7;
        StringBuilder builder = new StringBuilder();
    
        for (int i = 0; i < this.content.size(); i++) {
            String cellContent;
    
            if (this.content.isEmpty() || this.getItem(i) == null) {
                cellContent = "X";
            } else {
                cellContent = this.getItem(i).minimalDisplay();
            }
    
            String centered = String.format("%" + ((cellWidth + cellContent.length()) / 2) + "s", cellContent);
            centered = String.format("%-" + cellWidth + "s", centered);
    
            builder.append(centered);

            if ((i + 1) % this.stackCount == 0 && i != this.content.size() - 1) {
                builder.append("\n");
            }
        }
    
        builder.append(String.format("\n\nCount: %d\n", this.count()));
        builder.append(String.format("Total content area: %.2f\n", this.measureSize(2, "area")));
        builder.append(String.format("Total content volume: %.2f\n", this.measureSize(3, "volume")));
        return builder.toString();
    }
    
}
