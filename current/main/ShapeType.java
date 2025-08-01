import java.util.List;

public enum ShapeType {
    SQUARE("square", 2),
    TRIANGLE("triangle", 2),
    CIRCLE("circle", 2),
    TRAPEZIUM("trapezium", 2),
    PARALLELOGRAM("parallelogram", 2),
    RHOMBUS("rhombus", 2),
    POLYGON("polygon", 2),

    CUBE("cube", 3),
    RECT("rect", 3),
    CYLINDER("cylinder", 3),
    CONE("cone", 3),
    SPHERE("sphere", 3),
    PYRAMID("pyramid", 3),
    PRISM("prism", 3);

    private final String name;
    private final int dimension;

    ShapeType(String name, int dimension) {
        this.name = name;
        this.dimension = dimension;
    }

    public String getName() {
        return this.name;
    }

    public static ShapeType getValue(String name) {
        List<ShapeType> values = List.of(ShapeType.values());
        return values.stream().filter(shape -> shape.name == name).findFirst().get();
    }

    public static ShapeType getValue(int index) {
        List<ShapeType> values = List.of(ShapeType.values());
        return values.get(index);
    }

    public static List<ShapeType> getShapes(int dimension) {
        List<ShapeType> values = List.of(ShapeType.values());
        return values.stream().filter(shape -> shape.dimension == dimension).toList();
    }

    public static int getTotalSize() {
        return ShapeType.values().length;
    }

    public static int getSize(int dimension) {
        return getShapes(dimension).size();
    }

}
