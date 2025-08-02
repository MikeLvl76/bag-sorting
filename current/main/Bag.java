import java.util.ArrayList;
import java.util.Collections;

public class Bag {
    private ArrayList<Shape> content;
    private int stackCount;

    public Bag(int size, int stackCount) {
        if (size % stackCount != 0 || size <= stackCount) 
            throw new IllegalArgumentException(String.format("Incorrect values: %d and %d. Size must be a multiple of stack count.", size, stackCount));

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

    public void updateContent(int index, Shape shape) {
        this.content.set(index, shape);
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

    private Boolean hasColumnFull(int colIndex) {
        ArrayList<Shape> column = this.getColumn(colIndex);
        return column.stream().allMatch(shape -> shape != null);
    }

    private Boolean isFull() {
        Boolean isFull = true;
        for (int i = 0; i < this.stackCount; i++) {
            isFull = this.hasColumnFull(i);
            if (!isFull) return isFull;
        }
        return isFull;
    }

    public ArrayList<Shape> insertIntoColumn(int colIndex, Shape shape) {
        if (this.hasColumnFull(colIndex) || this.isFull()) return null;
        ArrayList<Shape> column = this.getColumn(colIndex);
        for (int i = column.size() - 1; i >= 0; i--) {
            Shape current = column.get(i);
            // Place is not taken by another shape
            if (current == null) {
                column.set(i, shape);
                break;
            }

            if (i == 0) break;
            int nextIndex = i - 1;

            // Place is taken
            // Compare dimension between current shape and the new
            if (current.getDimension() < shape.getDimension()) {
                // We consider that a 3D shape cannot stack with a 2D shape if the second one is below
                continue;
            } else if (current.getDimension() > shape.getDimension()) {
                // The opposite is true only if the volume of the 3D shape is twice greater than the area of the 2D shape
                double volume = current.getAttributeValue("volume");
                double area = shape.getAttributeValue("area");
                if (volume > 2 * area) {
                    column.set(nextIndex, shape);
                    break;
                }
            } else if (current.getDimension() == shape.getDimension()) {
                // If both are 2D shapes then only if the shape below has a greater area than the other
                if (current.getDimension() == 2) {
                    double currentArea = current.getAttributeValue("area");
                    double shapeArea = shape.getAttributeValue("area");
                    if (currentArea > shapeArea) {
                        column.set(nextIndex, shape);
                        break;
                    }
                }
                // Same for 3D shapes
                double currentVolume = current.getAttributeValue("volume");
                double shapeVolume = shape.getAttributeValue("volume");
                if (currentVolume > shapeVolume) {
                    column.set(nextIndex, shape);
                    break;
                }
            }
        }
        return column;
    }

    public void displayColumn(int colIndex) {
        ArrayList<Shape> column = this.getColumn(colIndex);
        System.out.println(column.stream().map(shape -> shape == null ? "x" : shape.minimalDisplay()).toList());
    }

    // public void insert(Shape ...shapes) {
    //     if (shapes.length == 0) return;
    //     if (this.isFull()) return;

    //     int index = this.content.size() - 1;
    //     for (Shape shape : shapes) {
    //         if (this.content.isEmpty()) {
    //             this.content.set(index, shape);
    //             continue;
    //         }


    //     }
    // }

    public void insertInto(Shape shape) {
        if (this.content.isEmpty()) {
            this.content.set(this.content.size() - 1, shape);
            return;
        }

        int index = this.content.size() - 1;
        while(index > 0) {
            if (this.content.get(index) != null) {
                index--;
            } else {
                this.content.set(index, shape);
                break;
            }
        }
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
    
        return builder.toString();
    }
    
}
