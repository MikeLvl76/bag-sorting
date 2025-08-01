import java.util.List;

public enum ShapeName {
    SQUARE("square", 2), TRIANGLE("triangle", 2), CIRCLE("circle", 2), TRAPEZIUM(
            "trapezium", 2), PARALLELOGRAM("parallelogram",
                    2), RHOMBUS("rhombus", 2), POLYGON("polygon", 2),

    CUBE("cube", 3), RECT("rect", 3), CYLINDER("cylinder", 3), CONE("cone",
            3), SPHERE("sphere", 3), PYRAMID("pyramid", 3), PRISM("prism", 3);

    private final String name;
    private final int dimension;

    ShapeName(String name, int dimension) {
        this.name = name;
        this.dimension = dimension;
    }

    public String getName() {
        return this.name;
    }

    public static ShapeName getValue(String name) {
        List<ShapeName> values = List.of(ShapeName.values());
        return values.stream().filter(shape -> shape.name == name).findFirst().get();
    }

    public static ShapeName getValue(int index) {
        List<ShapeName> values = List.of(ShapeName.values());
        return values.get(index);
    }

    public static List<ShapeName> getShapes(int dimension) {
        List<ShapeName> values = List.of(ShapeName.values());
        return values.stream().filter(shape -> shape.dimension == dimension).toList();
    }

    public static int getTotalSize() {
        return ShapeName.values().length;
    }

    public static int getSize(int dimension) {
        return getShapes(dimension).size();
    }

}
