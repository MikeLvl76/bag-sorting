import java.util.List;

public enum ShapeName {
    SQUARE("square", "2D"), TRIANGLE("triangle", "2D"), CIRCLE("circle", "2D"), TRAPEZIUM(
            "trapezium", "2D"), PARALLELOGRAM("parallelogram",
                    "2D"), RHOMBUS("rhombus", "2D"), POLYGON("polygon", "2D"),

    CUBE("cube", "3D"), RECT("rect", "3D"), CYLINDER("cylinder", "3D"), CONE("cone",
            "3D"), SPHERE("sphere", "3D"), PYRAMID("pyramid", "3D"), PRISM("prism", "3D");

    private final String name, dimension;

    ShapeName(String name, String dimension) {
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

    public static List<ShapeName> getShapes(String dimension) {
        List<ShapeName> values = List.of(ShapeName.values());
        return values.stream().filter(shape -> shape.dimension == dimension).toList();
    }

    public static int getTotalSize() {
        return ShapeName.values().length;
    }

    public static int getSize(String dimension) {
        return getShapes(dimension).size();
    }

}
