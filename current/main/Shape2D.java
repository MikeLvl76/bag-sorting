import java.util.Map;

public class Shape2D extends Shape implements AreaCalculator {

    public Shape2D(ShapeType type, Map<String, Double> attrs) {
        this.setType(type);
        if (type.getDimension() != 2)
            throw new IllegalArgumentException("Dimension must be equals to 2.");
        this.updateAttributes(attrs);
        this.calculate();
    }

    private double getSquareArea(double width, double length) {
        return width * length;
    }

    private double getTriangleArea(double base, double height) {
        return 1.0 / 2 * (base * height);
    }

    private double getCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    private double getTrapeziumArea(double a, double b, double height) {
        return 1.0 / 2 * (a + b) * height;
    }

    private double getParallelogramArea(double base, double height) {
        return base * height;
    }

    private double getRhombusArea(double base, double height) {
        return base * height;
    }

    private double getRegularPolygonArea(int sides, double length) {
        if (sides < 3 || length == 0)
            return 0.0;
        return (Math.pow(sides * length, 2) * (1.0 / Math.tan(Math.PI / sides))) / 4;
    }

    public void calculate() {
        double area = 0.0;
        switch (this.type) {
            case SQUARE:
                area = getSquareArea(this.attributes.get("width"), this.attributes.get("length"));
                break;

            case TRIANGLE:
                area = getTriangleArea(this.attributes.get("base"), this.attributes.get("height"));
                break;

            case CIRCLE:
                area = getCircleArea(this.attributes.get("radius"));
                break;

            case TRAPEZIUM:
                area = getTrapeziumArea(this.attributes.get("a"), this.attributes.get("b"),
                        this.attributes.get("height"));
                break;

            case PARALLELOGRAM:
                area = getParallelogramArea(this.attributes.get("base"), this.attributes.get("height"));
                break;

            case RHOMBUS:
                area = getRhombusArea(this.attributes.get("base"), this.attributes.get("height"));
                break;

            case POLYGON:
                area = getRegularPolygonArea(this.attributes.get("sides").intValue(), this.attributes.get("length"));
                break;

            default:
                area = 0.0;
                break;
        }
        this.attributes.put("area", area);
    }
}
