import java.util.ArrayList;

public class Bag {
    private ArrayList<Shape> content;
    private int maxSize;

    public Bag() {
        this.content = new ArrayList<>();
        this.maxSize = 12;
    }

    public ArrayList<Shape> getContent() {
        return this.content;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void addToContent(Shape ...shapes) {
        for (Shape s : shapes) {
            this.content.add(s);
        }
    }

    public void updateContent(int index, Shape shape) {
        this.content.set(index, shape);
    }

    public void changeMaxSize(int max) {
        this.maxSize = max;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.maxSize; i++) {
            if (this.content.isEmpty()) {
                builder.append("[x]");
            } else {
                Shape current = this.content.get(i);
                if (current == null) {
                    builder.append("[x]");
                } else {
                    builder.append("[" + current.getName().charAt(0) + "]");
                }
            }
            if ((i + 1) % 4 == 0 && i != this.maxSize - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
