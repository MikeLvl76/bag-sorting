import java.util.Map;

public class Shape2D extends Shape implements AreaCalculator {

    public Shape2D(ShapeType type, Map<String, Double> attrs) {
        this.setType(type);
        if (type.getDimension() != 2) throw new IllegalArgumentException("Dimension must be equals to 2.");
        this.setCanStack(false);
        attrs.put("area", this.calculateArea(attrs));
        this.updateAttributes(attrs);
    }

    private double calculateArea(Map<String, Double> attrs) {
        switch (this.type) {
            case SQUARE:
                return getSquareArea(attrs.get("width"), attrs.get("length"));

            case TRIANGLE:
                return getTriangleArea(attrs.get("base"), attrs.get("height"));

            case CIRCLE:
                return getCircleArea(attrs.get("radius"));

            case TRAPEZIUM:
                return getTrapeziumArea(attrs.get("a"), attrs.get("b"), attrs.get("height"));

            case PARALLELOGRAM:
                return getParallelogramArea(attrs.get("base"), attrs.get("height"));

            case RHOMBUS:
                return getRhombusArea(attrs.get("base"), attrs.get("height"));

            case POLYGON:
                return getRegularPolygonArea(attrs.get("sides").intValue(), attrs.get("length"));
        
            default:
                return 0.0;
        }
    }
}
