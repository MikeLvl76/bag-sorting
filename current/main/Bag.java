import java.util.ArrayList;

public class Bag {
    private ArrayList<Shape> content;
    private int maxSize;
    private int bagWidth;

    public Bag() {
        this.maxSize = 16;
        this.bagWidth = (int) this.maxSize / 4;
        this.content = new ArrayList<>(this.maxSize);
        int n = this.maxSize;
        while(n-- > 0) this.content.add(null);
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

    public int getMaxSize() {
        return this.maxSize;
    }

    public void updateContent(int index, Shape shape) {
        this.content.set(index, shape);
    }

    public void changeMaxSize(int max) {
        this.maxSize = max;
    }

    public ArrayList<Shape> getColumn(int colIndex) {
        ArrayList<Shape> column = new ArrayList<>();

        for (int i = 0; i < this.maxSize; i++) {
            if ((i + colIndex) % bagWidth == 0) {
                column.add(this.getItem(i));
            }
        }

        return column;
    }

    public void displayColumn(int colIndex) {
        ArrayList<Shape> column = this.getColumn(colIndex);
        System.out.println(column.stream().map(shape -> shape == null ? "x" : shape.minimalDisplay()).toList());
    }

    public void insertInto(Shape shape) {
        if (this.content.isEmpty()) {
            this.content.set(maxSize - 1, shape);
            return;
        }

        int index = maxSize - 1;
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
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.maxSize; i++) {
            if (this.content.isEmpty()) {
                builder.append("[x]");
            } else {
                Shape current = this.getItem(i);
                if (current == null) {
                    builder.append("[x]");
                } else {
                    builder.append(String.format("[%s]", current.minimalDisplay()));
                }
            }
            if ((i + 1) % this.bagWidth == 0 && i != this.maxSize - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
